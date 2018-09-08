package com.szpcqy.fisher.ui.fish.play;

import android.content.Context;

import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.data.login.UserVo;
import com.szpcqy.fisher.data.play.AddCoinRequest;
import com.szpcqy.fisher.data.play.PlayFishRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.ui.base.presenter.impl.MvpBasePresenter;

import es.dmoral.toasty.Toasty;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 20:59
 */
public class FishPlayPresenter extends MvpBasePresenter<FishPlayView> {
    FishPlayModel model;

    public FishPlayPresenter(Context context) {
        super(context);
    }

    @Override
    public void initModel() {
        model = new FishPlayModel();
    }

    @Override
    public void onSucess(int protocol, SocketResonse res) {
        switch (protocol) {
            case SocketProtocol.SWITCH_STRENTH_RES:
                //切换大炮
                getView().switchStrengthSuccess();
                break;
            case SocketProtocol.DIRECTION_FIRE_RES:
                getView().fireSuccess();
                break;
            case SocketProtocol.COINOUT_RES:
                getView().returnCoinSuccess();
                break;
            case SocketProtocol.COININ_RES:
                UserVo userVo = res.read(UserVo.class, res.getData());
                getView().addCoinSuccess(userVo);
                break;
            case SocketProtocol.QUIT_SLOT_RES:
                getView().quitSlotSuccess();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(int protocol, String msg) {
        switch (protocol) {
            case SocketProtocol.SWITCH_STRENTH_RES:
            case SocketProtocol.DIRECTION_FIRE_RES:
            case SocketProtocol.COINOUT_RES:
            case SocketProtocol.COININ_RES:
            case SocketProtocol.QUIT_SLOT_RES:
                Toasty.warning(getContext(), msg).show();
                break;
            default:
                break;
        }
    }

    /**
     * 加币
     *
     * @param score
     */
    public void addCoin(int score) {
        getView().showProgressDialog(SocketProtocol.COININ_REQ);
        model.addCoin(new AddCoinRequest(SocketProtocol.COININ_REQ,score));
    }

    /**
     * 退币
     */
    public void returnCoin() {
        model.returnCoin();
    }

    /**
     * 玩
     *
     * @param request
     */
    public void play(PlayFishRequest request) {
        model.play(request);
    }

    /**
     * 退出控制位
     */
    public void quitSlot() {
        model.quitSlot();
    }
    /**
     * 退出控制位
     */
    public void switchStrength() {
        model.switchStrength();
    }
}
