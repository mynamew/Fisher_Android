package com.szpcqy.fisher.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.NetRequest;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.Gateway;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.tool.CacheTool;
import com.szpcqy.fisher.tool.Clock;
import com.szpcqy.fisher.ui.game.GameSelectActivity;
import com.szpcqy.fisher.utils.ToastUtils;
import com.szpcqy.fisher.view.MTImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录的界面
 *
 * @author jzk
 * create at: 2018/8/25 14:10
 */
public class LoginActivity extends MTMvpActivity<LoginView,LoginPresenter> implements LoginView {

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



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetResponse(NetResponse res) {
        if (res.getIsConnected() && res.getIp().equals(Gateway.SERVER_IP)) {
            confirmBtn.setEnabled(true);
        }
    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.LOGIN_RES
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
                getPresenter().login(new LoginRequest(SocketProtocol.LOGIN_REQ,userName,passWord));
                break;
            case R.id.tv_forget_psw:
                break;
            case R.id.tv_regist:
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(LoginResponse response) {
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
                Intent it = new Intent(getContext(), GameSelectActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    @Override
    public void loginFail(String msg) {
        MTLightbox.update(getContext(), dia, MTLightbox.IconType.ERROR,msg, 1000, null);

    }

    @Override
    public void showProgressDialog(int pro) {
        dia = MTLightbox.show(getContext(), MTLightbox.IconType.PROGRESS, "登录中", false);
    }

    @Override
    public void dismisProgressDialog() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiResponse(WifiResponse res) {
        if (res.getIsConnected() && res.getSsid().equals(Gateway.SERVER_SSID)) {
            Clock.create(this).interval(500).count(1).onCompleteOnUI(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new NetRequest(NetRequest.RequestType.CONNECT, Gateway.SERVER_IP));
                }
            }).start();
        }
    }
}
