package com.szpcqy.fisher.ui.login;

import android.content.Context;

import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.ui.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 10:41
 */
public class LoginPresenter extends MvpBasePresenter< LoginView> {
    private LoginModel model;

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void initModel() {
        this.model = new LoginModel();
    }

    @Override
    public void onSucess(int protocol, SocketResonse res) {
        switch (protocol) {
            case SocketProtocol.LOGIN_RES:
                getView().loginSuccess(res.read(LoginResponse.class,res.getData()));
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(int protocol, String msg) {
        switch (protocol) {
            case SocketProtocol.LOGIN_RES:
                getView().loginFail(msg);
                break;
            default:
                break;
        }
    }

    /**
     * 登录的请求方法
     * @param request
     */
    public void login(LoginRequest request) {
        getView().showProgressDialog(request.getProtocol());
        model.LoginRequest(new CommonRequest<>(request));
    }
}
