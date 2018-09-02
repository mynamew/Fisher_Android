package com.szpcqy.fisher.ui.login;

import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 11:10
 */
public interface LoginView extends MvpBaseView {
    /**
     * 登录成功
     */
    void loginSuccess(LoginResponse response);

    void loginFail(String msg);
    /**
     * 加入座位失败
     * @param msg
     */
    void joinSlotFail(String msg);

    /**
     * 加入座位成功
     * @param res
     */
    void joinSlotSuccess(SocketResonse res);
}
