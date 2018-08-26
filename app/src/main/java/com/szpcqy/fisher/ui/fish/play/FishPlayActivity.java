package com.szpcqy.fisher.ui.fish.play;

import android.os.Bundle;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.mt.MTMvpActivity;
import com.szpcqy.fisher.net.SocketProtocol;

public class FishPlayActivity extends MTMvpActivity<FishPlayView,FishPlayPresenter> implements FishPlayView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_fish_play;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int[] listenProtocols() {
        return new int[]{
                SocketProtocol.SWITCH_STRENTH_RES,
                SocketProtocol.DIRECTION_FIRE_RES,
                SocketProtocol.COININ_RES,
                SocketProtocol.COINOUT_RES,
                SocketProtocol.QUIT_SLOT_RES
        };
    }

    @Override
    public FishPlayPresenter createPresenter() {
        return new FishPlayPresenter(this);
    }

    @Override
    public FishPlayView createView() {
        return this;
    }

    @Override
    public void showProgressDialog(int protocol) {

    }

    @Override
    public void dismisProgressDialog() {

    }
}
