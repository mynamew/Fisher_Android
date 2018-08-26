package com.szpcqy.fisher.ui.fish.desk;

import com.szpcqy.fisher.data.fish.FishGetAllDeskRequest;
import com.szpcqy.fisher.data.fish.FishJoinSlotRequest;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.ui.base.model.impl.MvpBaseModel;

import org.greenrobot.eventbus.EventBus;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 15:38
 */
public class FishDeskModel extends MvpBaseModel {
    /**
     * 登录的请求
     * @param request
     */
    public void LoginRequest(LoginRequest request) {
        EventBus.getDefault().post(new CommonRequest<LoginRequest>(request));
    }
    /**
     * 获取所有的桌位
     * @param request
     */
    public void getAllDesk(FishGetAllDeskRequest request) {
        EventBus.getDefault().post(new CommonRequest<FishGetAllDeskRequest>(request));
    }
    /**
     * 加入座位
     * @param request
     */
    public void joinSlot(FishJoinSlotRequest request) {
        EventBus.getDefault().post(new CommonRequest<FishJoinSlotRequest>(request));
    }
}
