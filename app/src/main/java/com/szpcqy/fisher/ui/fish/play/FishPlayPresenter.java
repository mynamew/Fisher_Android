package com.szpcqy.fisher.ui.fish.play;

import android.content.Context;

import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.ui.base.presenter.impl.MvpBasePresenter;

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

    }

    @Override
    public void onFail(int protocol, String msg) {

    }
}
