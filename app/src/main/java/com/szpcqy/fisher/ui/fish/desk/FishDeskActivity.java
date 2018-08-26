package com.szpcqy.fisher.ui.fish.desk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.jzk.utilslibrary.LogUitls;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.data.fish.FishGetAllDeskRequest;
import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.fish.FishJoinSlotRequest;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetRequest;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.mt.MTActivity;
import com.szpcqy.fisher.mt.MTApplication;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.net.vo.GameSLotVO;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.tool.Clock;
import com.szpcqy.fisher.ui.fragment.DeskBaseFragment;
import com.szpcqy.fisher.ui.fragment.DeskEightFragment;
import com.szpcqy.fisher.ui.fragment.DeskSixFragment;
import com.szpcqy.fisher.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * 桌位
 *
 * @author jzk
 * create at: 2018/8/26 15:37
 */
public class FishDeskActivity extends MTMvpActivity<FishDeskView, FishDeskPresenter> implements FishDeskView {
    static public final String DESKID = "DESKID";
    static public final String DES_TYPE = "DES_TYPE";

    private DeskBaseFragment fragment;
    private MTDialog.MTDialogContent dia;

    private FishGetAllDeskResponse fishGetAllDeskResponse = null;
    //桌号
    private String desId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_fish_desk;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        desId = getIntent().getStringExtra(DESKID);
        fishGetAllDeskResponse = null;
        List<FishGetAllDeskResponse> fishGetAllDeskResponses = CacheTool.getFishGetAllDeskResponse();
        for (int i = 0; i < fishGetAllDeskResponses.size(); i++) {
            if (desId.equals(fishGetAllDeskResponses.get(i).getId())) {
                fishGetAllDeskResponse = fishGetAllDeskResponses.get(i);
            }
        }
        EventBus.getDefault().post(new WifiRequest(fishGetAllDeskResponse.getDevicessid(), fishGetAllDeskResponse.getDevicesspw()));
        /**
         * 不同的桌子
         */
        int deviceType = getIntent().getIntExtra(DES_TYPE, 1);
        if (deviceType == 1) {
            fragment = new DeskEightFragment();
        } else {
            fragment = new DeskSixFragment();
        }
        FragmentTransaction transaction = super.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fg_desk, fragment);
        transaction.commit();
        fragment.setOnClickListener(new DeskBaseFragment.OnClickListener() {
            @Override
            public void onDeviceClick() {

            }

            @Override
            public void onSlotClick(int pos) {
                if (fishGetAllDeskResponse == null) {
                    Toasty.error(getContext(), "设备维护中").show();
                    return;
                } else {
                    GameSLotVO slotVO = fishGetAllDeskResponse.getSlot(pos);
                    if (slotVO == null || slotVO.getEnable() == 0) {
                        Toasty.error(getContext(), "位置维护中").show();
                        return;
                    }
                    FishJoinSlotRequest request = new FishJoinSlotRequest(SocketProtocol.JOIN_SLOT_REQ,
                            desId, fishGetAllDeskResponse.getSlot(pos).getId());
                    getPresenter().joinSlot(request);
                }
            }
        });
    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.GET_DESK_SINGLE_RES,
                SocketProtocol.JOIN_SLOT_RES,
                SocketProtocol.LOGIN_RES,
                SocketProtocol.DEVICE_UPDATE_RES,
        };
    }

    @Override
    public FishDeskPresenter createPresenter() {
        return new FishDeskPresenter(this);
    }

    @Override
    public FishDeskView createView() {
        return this;
    }

    @Override
    public void showProgressDialog(int protocol) {
        switch (protocol) {
            case SocketProtocol.LOGIN_REQ:
                dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "自动登录中", false);

                break;
            case SocketProtocol.JOIN_SLOT_REQ:
                dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "加入座位中", false);
                break;
            case SocketProtocol.GET_DESK_SINGLE_REQ:
                dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "自动获取桌位", false);
                break;
            default:
                break;
        }

    }

    @Override
    public void dismisProgressDialog() {
        dia.close();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetResponse(NetResponse res) {
        if (res.getIsConnected() && res.getIp().equals(fishGetAllDeskResponse.getServerip())) {
            //socket连接成功-自动登录
            getPresenter().login(new LoginRequest(SocketProtocol.LOGIN_REQ,
                    CacheTool.getCurentUsername(),
                    CacheTool.getPsw()));
        }
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        LogUitls.d("登录成功！");
        CacheTool.setCurrentLoginResponse(response);
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "登录成功");
        dia.close();

        FishGetAllDeskRequest request = new FishGetAllDeskRequest(SocketProtocol.GET_DESK_SINGLE_REQ, desId);
        getPresenter().getAllDesk(request);
    }

    @Override
    public void loginFail(String msg) {
        final MTActivity atx = MTApplication.getInstance().getCurrentActivity();
        AlertDialog.Builder bd = new AlertDialog.Builder(atx);
        bd.setTitle("错误");
        bd.setMessage("自动登录失败，是否重新登录?");
        bd.setCancelable(false);
        bd.setNegativeButton("退出登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MTApplication.getInstance().onFinishAllActivities();
                atx.startActivity(new Intent(atx, LoginActivity.class));
            }
        });
        bd.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPresenter().login(
                        new LoginRequest(SocketProtocol.LOGIN_RES,
                                CacheTool.getCurentUsername(),
                                CacheTool.getPsw()));
            }
        });
        bd.show();
    }

    @Override
    public void getSingleDeskSuccess(FishGetAllDeskResponse response) {
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "获取桌位成功", 1000);
        fishGetAllDeskResponse = response;
        fragment.setDeskVO(response);
    }

    @Override
    public void deviceUpdate(FishGetAllDeskResponse response) {
        /**
         * 更新信息
         */
        if (response.getId().equals(fishGetAllDeskResponse.getId())) {
            fishGetAllDeskResponse = response;
            fragment.setDeskVO(fishGetAllDeskResponse);
        }
    }

    @Override
    public void getSingleDeskFail(String mesg) {
        LogUitls.d("获取桌位失败！");
    }

    @Override
    public void userKickDown(String msg) {

    }

    @Override
    public void coinOutSuccess(LoginResponse response) {

    }

    @Override
    public void coinOutFail(String msg) {

    }

    @Override
    public void joinSlotFail(String msg) {
        Toasty.warning(getContext(), msg).show();
    }

    @Override
    public void joinSlotSuccess(SocketResonse res) {
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "加入座位成功", 1000);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiResponse(WifiResponse res) {
        if (res.getIsConnected() && res.getSsid().equals(fishGetAllDeskResponse.getDevicessid())) {
            //连上机器wifi-连接socket
            Clock.create(this).interval(500).count(1).onCompleteOnUI(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new NetRequest(NetRequest.RequestType.CONNECT, fishGetAllDeskResponse.getServerip()));
                }
            }).start();
        }
    }
}