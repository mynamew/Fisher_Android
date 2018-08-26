package com.szpcqy.fisher.ui.fish;

import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.ui.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 13:38
 */
public interface FishHallView extends MvpBaseView {
    /**
     * 获取桌位成功
     *
     * @param mDatas
     */
    void getAllDeskSuccess(List<FishGetAllDeskResponse> mDatas);

    /**
     * 设备更新
     *
     * @param mDatas
     */
    void deviceUpdate(FishGetAllDeskResponse mDatas);

    /**
     * 获取桌位失败
     */
    void getAllDeskFail(String mesg);

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

}
