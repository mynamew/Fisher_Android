package com.szpcqy.fisher.ui.fish;

import com.szpcqy.fisher.data.fish.FishGetAllDeskRequest;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.ui.base.model.impl.MvpBaseModel;

import org.greenrobot.eventbus.EventBus;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 13:38
 */
public class FishHallModel extends MvpBaseModel {
    /**
     * 获取所有的桌位
     * @param request
     */
    public void getAllDesk(FishGetAllDeskRequest request) {
        EventBus.getDefault().post(new CommonRequest<>(request));
    }
}
