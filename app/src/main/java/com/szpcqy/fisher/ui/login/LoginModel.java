package com.szpcqy.fisher.ui.login;

import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.event.pair.CommonRequest;

import org.greenrobot.eventbus.EventBus;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-25 20:32
 */
public class LoginModel {
    /**
     * 登录的请求
     * @param t
     */
    public void LoginRequest(CommonRequest<LoginRequest> t) {
        EventBus.getDefault().post(t);
    }
}
