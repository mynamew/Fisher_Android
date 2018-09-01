package com.szpcqy.fisher.ui.fish.play;

import com.szpcqy.fisher.data.play.AddCoinRequest;
import com.szpcqy.fisher.data.play.PlayFishRequest;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.event.pair.SocketRequest;
import com.szpcqy.fisher.net.SocketProtocol;
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

        EventBus.getDefault().post(new CommonRequest<>(request));
    }

    /**
     * 退币的请求
     *
     */
    public void returnCoin() {
        EventBus.getDefault().post(new SocketRequest(SocketProtocol.COINOUT_REQ));
    }

    /**
     * 退出控制位的请求
     *
     */
    public void quitSlot() {
        EventBus.getDefault().post(new SocketRequest(SocketProtocol.QUIT_SLOT_REQ));
    }

    /**
     * 玩
     *
     * @param request
     */
    public void play(PlayFishRequest request) {
        EventBus.getDefault().post(new CommonRequest<>(request));
    }

    /**
     * 切换大炮
     *
     */
    public void switchStrength( ) {
        EventBus.getDefault().post(new SocketRequest(SocketProtocol.SWITCH_STRENTH_REQ));
    }
}
