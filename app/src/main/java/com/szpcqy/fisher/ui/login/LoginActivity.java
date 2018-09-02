package com.szpcqy.fisher.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.utilslibrary.LogUitls;
import com.szpcqy.fisher.R;
import com.szpcqy.fisher.data.fish.FishJoinSlotRequest;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.Gateway;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.ui.fish.play.FishPlayActivity;
import com.szpcqy.fisher.ui.game.GameSelectActivity;
import com.szpcqy.fisher.utils.ToastUtils;
import com.szpcqy.fisher.utils.WifiUtils;
import com.szpcqy.fisher.view.MTImageView;
import com.szpcqy.fisher.view.RegistDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 登录的界面
 *
 * @author jzk
 * create at: 2018/8/25 14:10
 */
public class LoginActivity extends MTMvpActivity<LoginView, LoginPresenter> implements LoginView {

    MTDialog.MTDialogContent dia;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.centerCon)
    LinearLayout centerCon;
    @BindView(R.id.confirmBtn)
    MTImageView confirmBtn;
    @BindView(R.id.tv_forget_psw)
    TextView tvForgetPsw;
    @BindView(R.id.tv_regist)
    TextView tvRegist;


    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.LOGIN_RES,
                SocketProtocol.JOIN_SLOT_RES
        };
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etUsername.setSelection(etUsername.getText().toString().trim().length());
    }

    @Override
    public void initData() {
        LogUitls.e("当前的ip--->",WifiUtils.getWifiRouteIPAddress(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        //重连wifi
        EventBus.getDefault().post(new WifiRequest(Gateway.SERVER_SSID, Gateway.SERVER_SSPW));
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dia != null) {
            dia.close();
        }
    }

    @OnClick({R.id.iv_return, R.id.iv_set, R.id.confirmBtn, R.id.tv_forget_psw, R.id.tv_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                onBackPressed();
                break;
            case R.id.iv_set:
                break;
            case R.id.confirmBtn:
                String userName = etUsername.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.showShort(LoginActivity.this, "请输入登录名称");
                    return;
                }
                String passWord = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(passWord)) {
                    ToastUtils.showShort(LoginActivity.this, "请输入登录密码");
                    return;
                }
                /**
                 * 登录的请求
                 */
                getPresenter().login(new LoginRequest(SocketProtocol.LOGIN_REQ, userName, passWord));
                break;
            case R.id.tv_forget_psw:
                break;
            case R.id.tv_regist:
                new RegistDialog(this, new RegistDialog.RegistListener() {
                    @Override
                    public void commitRegist(String user, String psw, String repeatPsw, String tel, String question, String answer) {
                        // TODO: 2018/9/1 注册的事件
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        LogUitls.d("登录的信息--->", response.toString());
        /**
         * 设置当前登录的信息
         */
        CacheTool.setCurrentLoginResponse(response);
        CacheTool.setPsw(etPassword.getText().toString().trim());
        /**
         * 显示登录成功
         */
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.SUCCESS, "登录成功", 1000, new MTDialog.OnCallback() {
            @Override
            public void onShow(MTDialog.MTDialogContent dialog) {
                //
            }

            @Override
            public void onDismiss(MTDialog.MTDialogContent dialog) {
                /**
                 * 是否是断线重连的状态
                 */
                if (null != response.getDeviceVO() && null != response.getSlotVO()) {
                    /**
                     * 如果当前ip和socket的ip地址一样则直接加入控制位
                     */
                    if (WifiUtils.getWifiRouteIPAddress(LoginActivity.this).equals(response.getDeviceVO().getServerip())) {
//                        MTLightbox.update(LoginActivity.this,dia, MTLightbox.IconType.PROGRESS,"加入座位中");
                        //自动跳转游戏机并且 加入控制位
                        LoginResponse currentLoginResponse = CacheTool.getCurrentLoginResponse();
                        FishJoinSlotRequest request = new FishJoinSlotRequest(SocketProtocol.JOIN_SLOT_REQ
                                , currentLoginResponse.getDeviceVO().getId()
                                , currentLoginResponse.getSlotVO().getId());
                        getPresenter().joinSlot(request);
                        return;
                    }
                    if (null != CacheTool.getCurrentLoginResponse().getDeviceVO() && null != CacheTool.getCurrentLoginResponse().getSlotVO()) {
//                        MTLightbox.update(LoginActivity.this,dia, MTLightbox.IconType.PROGRESS,"加入座位中");
                        //自动跳转游戏机并且 加入控制位
                        LoginResponse currentLoginResponse = CacheTool.getCurrentLoginResponse();
                        FishJoinSlotRequest request = new FishJoinSlotRequest(SocketProtocol.JOIN_SLOT_REQ
                                , currentLoginResponse.getDeviceVO().getId()
                                , currentLoginResponse.getSlotVO().getId());
                        getPresenter().joinSlot(request);
                    } else {
                        LoginResponse.DeviceVOBean deviceVO = response.getDeviceVO();
                        autoConnectWifi(deviceVO.getDevicessid(), deviceVO.getDevicesspw());
                    }
                } else {
                    Intent it = new Intent(getContext(), GameSelectActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });
    }

    @Override
    public void loginFail(String msg) {
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.ERROR, msg, 1000, null);

    }

    @Override
    public void joinSlotFail(String msg) {
        dia.close();
        Toasty.warning(getContext(), msg).show();
    }

    @Override
    public void joinSlotSuccess(SocketResonse res) {
        dia.close();
        Intent it = new Intent(getContext(), FishPlayActivity.class);
        LoginResponse currentLoginResponse = CacheTool.getCurrentLoginResponse();
        LoginResponse.DeviceVOBean deviceVO = currentLoginResponse.getDeviceVO();
        int currentSlotSelectPosition = 1;
        String userId = CacheTool.getCurrentId();
        if (null != deviceVO && deviceVO.getSlot1().equals(userId)) {
            currentSlotSelectPosition = 1;
        }
        if (null != deviceVO && deviceVO.getSlot2().equals(userId)) {
            currentSlotSelectPosition = 2;
        }
        if (null != deviceVO && deviceVO.getSlot3().equals(userId)) {
            currentSlotSelectPosition = 3;
        }
        if (null != deviceVO && deviceVO.getSlot4().equals(userId)) {
            currentSlotSelectPosition = 4;
        }
        if (null != deviceVO && deviceVO.getSlot5().equals(userId)) {
            currentSlotSelectPosition = 5;
        }
        if (null != deviceVO && deviceVO.getSlot6().equals(userId)) {
            currentSlotSelectPosition = 6;
        }
        if (null != deviceVO && deviceVO.getSlot7().equals(userId)) {
            currentSlotSelectPosition = 7;
        }
        if (null != deviceVO && deviceVO.getSlot8().equals(userId)) {
            currentSlotSelectPosition = 8;
        }
        it.putExtra(FishPlayActivity.RATIO_COIN_MULTIPLY, deviceVO.getRatiocoinscore());
        it.putExtra(FishPlayActivity.SLOT_POSITION, currentSlotSelectPosition);
        it.putExtra(FishPlayActivity.DESK_TYPE, deviceVO.getDevicetype());
        /**
         * 清空重连信息
         */
        CacheTool.getCurrentLoginResponse().setDeviceVO(null);
        CacheTool.getCurrentLoginResponse().setSlotVO(null);
        startActivity(it);
    }

    @Override
    public void showProgressDialog(int pro) {
        if (pro == SocketProtocol.JOIN_SLOT_REQ) {
        } else {
            dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "登录中", false);
        }
    }

    @Override
    public void dismisProgressDialog() {
    }

    @Override
    protected void connectWifiSuccess(WifiResponse res) {
        super.connectWifiSuccess(res);
        /**
         *  1、当缓存的用户信息不为空
         *  2、当缓存的用户信息中设备信息不为空
         *  3、用户缓存的设备信息中的wifi名称和现在连接wifi名称一致
         *    即为断线重连。需要连接缓存中socket的地址
         */
        if (null != CacheTool.getCurrentLoginResponse()
                && null != CacheTool.getCurrentLoginResponse().getDeviceVO()
                && res.getSsid().equals(CacheTool.getCurrentLoginResponse().getDeviceVO().getDevicessid())) {
            autoConnectSocket(CacheTool.getCurrentLoginResponse().getDeviceVO().getServerip());
        } else {
            autoConnectSocket(Gateway.SERVER_IP);
        }
    }

    @Override
    protected void connectSocketSuccess(NetResponse res) {
        super.connectSocketSuccess(res);
        if (res.getIsConnected()) {
            if (res.getIp().equals(Gateway.SERVER_IP) && null == CacheTool.getCurrentLoginResponse()) {
                confirmBtn.setEnabled(true);
            } else {
                /**
                 * 登录的请求
                 */
                getPresenter().login(new LoginRequest(SocketProtocol.LOGIN_REQ, etUsername.getText().toString().trim()
                        , etPassword.getText().toString().trim()));
            }
        }
    }
}
