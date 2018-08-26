package com.szpcqy.fisher.ui.fish.desk;

import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 15:38
 */
public interface FishDeskView extends MvpBaseView {
    /**
     * 登录成功
     */
    void loginSuccess(LoginResponse response);

    void loginFail(String msg);
    /**
     * 获取桌位成功
     *
     * @param response
     */
    void getSingleDeskSuccess(FishGetAllDeskResponse response);

    /**
     * 设备更新
     *
     * @param mDatas
     */
    void deviceUpdate(FishGetAllDeskResponse mDatas);

    /**
     * 获取桌位失败
     */
    void getSingleDeskFail(String mesg);

    /**
     * 账号被迫下线
     * @param msg
     */
    void userKickDown(String msg);

    /**
     * 退币成功
     *
     * @param response
     */
    void coinOutSuccess(LoginResponse response);

    /**
     * 退币失败
     */
    void coinOutFail(String msg);

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
