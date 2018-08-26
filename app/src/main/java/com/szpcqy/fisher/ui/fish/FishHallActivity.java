package com.szpcqy.fisher.ui.fish;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jzk.utilslibrary.LogUitls;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.adapter.MyAdapter;
import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.mt.MTApplication;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.ui.fragment.DeskBaseFragment;
import com.szpcqy.fisher.ui.fragment.DeskEightFragment;
import com.szpcqy.fisher.ui.fragment.DeskSixFragment;
import com.szpcqy.fisher.ui.login.LoginActivity;
import com.szpcqy.fisher.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 捕鱼大厅
 *
 * @author jzk
 * create at: 2018/8/25 18:21
 */
public class FishHallActivity extends MTMvpActivity<FishHallView, FishHallPresenter> implements FishHallView {
    MTDialog.MTDialogContent dia;
    @BindView(R.id.vp_fish_hall)
    ViewPager vpFishHall;


    private ArrayList<DeskBaseFragment> mDeskFragments;
    private MyAdapter mAdapter;


    private ArrayList<FishGetAllDeskResponse> mFishDesks;
    @Override
    public FishHallPresenter createPresenter() {
        return new FishHallPresenter(this);
    }

    @Override
    public FishHallView createView() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dia != null) {
            dia.close();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserResponse(LoginResponse res) {
        // TODO: 2018/8/26 设置当前金币数
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetResponse(NetResponse res) {
        if (res.getIsConnected() == false && res.getIsRemote()) {
            //被远程关闭，退出登录
            final AppCompatActivity atx = MTApplication.getInstance().getCurrentActivity();
            AlertDialog.Builder bd = new AlertDialog.Builder(atx);
            bd.setTitle("警告");
            bd.setMessage("您与设备之间已失联，将退出到登录界面!");
            bd.setCancelable(false);
            bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MTApplication.getInstance().onFinishAllActivities();
                    atx.startActivity(new Intent(atx, LoginActivity.class));
                }
            });
            bd.show();
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_hall;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getPresenter().getAllDesk();

        mFishDesks = new ArrayList<>();
        mDeskFragments = new ArrayList<>();

        mAdapter = new MyAdapter(getSupportFragmentManager(),mDeskFragments,this);
        vpFishHall.setAdapter(mAdapter);
        vpFishHall.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //
            }

            @Override
            public void onPageSelected(int position) {
//                m_curallTxt.setText((position + 1) + " / " + mDeskFragments.size());
                // TODO: 2018/8/26  设置当前桌号
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //
            }
        });
    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.GET_DESK_ALL_RES,
                SocketProtocol.KICK_OUT_RES,
                SocketProtocol.DEVICE_UPDATE_RES,
                SocketProtocol.COINOUT_RES,
        };
    }

    @Override
    public void showProgressDialog(int protocol) {
        dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "获取桌位信息", false);
    }

    @Override
    public void dismisProgressDialog() {
        dia.close();
    }

    @Override
    public void getAllDeskSuccess(List<FishGetAllDeskResponse> mDatas) {
        CacheTool.setFishGetAllDeskResponse(mDatas);
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "获取桌位成功", 1000);
        LogUitls.d("获取到的桌位信息 FishHall-->" + mDatas);
        mFishDesks.clear();
        mFishDesks.addAll(mDatas);
        updateDesks(null);
    }

    @Override
    public void deviceUpdate(FishGetAllDeskResponse res) {
        // TODO: 2018/8/26 桌位更新
        CacheTool.updateFishGetAllDeskResponse(res);
        updateDesks(res);
    }

    @Override
    public void getAllDeskFail(String mesg) {
        AlertDialog.Builder bd = new AlertDialog.Builder(getContext());
        bd.setTitle("警告");
        bd.setMessage(mesg + ",是否重新加载");
        bd.setCancelable(false);
        bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPresenter().getAllDesk();
            }
        });
        bd.show();
    }

    @Override
    public void userKickDown(String msg) {
        AlertDialog.Builder bd = new AlertDialog.Builder(getContext());
        bd.setTitle("警告");
        bd.setMessage(msg + ",您将返回登录页面!");
        bd.setCancelable(false);
        bd.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MTApplication.getInstance().onFinishAllActivities();
                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        bd.show();
    }

    @Override
    public void coinOutSuccess(LoginResponse response) {
        CacheTool.setCurrentLoginResponse(response);
    }

    @Override
    public void coinOutFail(String msg) {
        ToastUtils.showShort(this, msg);
    }


    private void updateDesks(FishGetAllDeskResponse response) {
        if (response != null) {
            boolean isnew = true;
            for (FishGetAllDeskResponse vo : mFishDesks) {
                if (vo.getId().equals(response.getId())) {
                    int index = mFishDesks.indexOf(vo);
                    mFishDesks.remove(vo);
                    if (index >= mFishDesks.size()) {
                        mFishDesks.add(response);
                    } else {
                        mFishDesks.add(index, response);
                    }
                    isnew = false;
                    break;
                }
            }
            if (isnew) {
                mFishDesks.add(response);
            }
        }

        mDeskFragments.clear();
        for (FishGetAllDeskResponse vo : mFishDesks) {
            DeskBaseFragment desk = null;
            if (vo.getDevicetype() == 0) {
                desk = new DeskSixFragment();
            } else if (vo.getDevicetype() == 1) {
                desk = new DeskEightFragment();
            }
            desk.setDeskVO(vo);
            mDeskFragments.add(desk);
        }

        if (mFishDesks.size() > 0) {
//            m_machineNameTxt.setText(mFishDesks.get(0).getDevicename());
            // TODO: 2018/8/26 设置当前桌号
        }
        mAdapter.notifyDataSetChanged();
    }
}
