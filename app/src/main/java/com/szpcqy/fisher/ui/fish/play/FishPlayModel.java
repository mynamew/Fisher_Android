package com.szpcqy.fisher.ui.fish.play;

import com.szpcqy.fisher.data.play.AddCoinRequest;
import com.szpcqy.fisher.data.play.PlayFishRequest;
import com.szpcqy.fisher.data.play.QuitSlotRequest;
import com.szpcqy.fisher.data.play.ReturnCoinRequest;
import com.szpcqy.fisher.data.play.SwitchStrengthRequest;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.ui.base.model.impl.MvpBaseModel;

import org.greenrobot.eventbus.EventBus;

/**
 * $dsc play的model
 * author: timi
 * create at: 2018-08-26 20:59
 */
public class FishPlayModel extends MvpBaseModel {
    /**
     * 加币的请求
     *
     * @param request
     */
    public void addCoin(AddCoinRequest request) {
        EventBus.getDefault().post(new CommonRequest<AddCoinRequest>(request));
    }

    /**
     * 退币的请求
     *
     * @param request
     */
    public void returnCoin(ReturnCoinRequest request) {
        EventBus.getDefault().post(new CommonRequest<ReturnCoinRequest>(request));
    }

    /**
     * 退出控制位的请求
     *
     * @param request
     */
    public void quitSlot(QuitSlotRequest request) {
        EventBus.getDefault().post(new CommonRequest<QuitSlotRequest>(request));
    }

    /**
     * 玩
     *
     * @param request
     */
    public void play(PlayFishRequest request) {
        EventBus.getDefault().post(new CommonRequest<PlayFishRequest>(request));
    }

    /**
     * 切换大炮
     *
     * @param request
     */
    public void switchStrength(SwitchStrengthRequest request) {
        EventBus.getDefault().post(new CommonRequest<SwitchStrengthRequest>(request));
    }
}
