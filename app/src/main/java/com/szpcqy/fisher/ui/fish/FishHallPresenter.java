package com.szpcqy.fisher.ui.fish;

import android.content.Context;

import com.szpcqy.fisher.data.fish.FishGetAllDeskRequest;
import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.ui.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 13:38
 */
public class FishHallPresenter extends MvpBasePresenter<FishHallView> {
    FishHallModel model;

    public FishHallPresenter(Context context) {
        super(context);
    }

    @Override
    public void initModel() {
        model = new FishHallModel();
    }

    @Override
    public void onSucess(int protocol, SocketResonse res) {
        switch (res.getProtocol()) {
            //获取所有的桌位成功
            case SocketProtocol.GET_DESK_ALL_RES:
                getView().getAllDeskSuccess(res.readList(FishGetAllDeskResponse.class, res.getData()));
                break;
            //设备信息更新
            case SocketProtocol.DEVICE_UPDATE_RES:
                getView().deviceUpdate(res.read(FishGetAllDeskResponse.class, res.getData()));
                break;
            //退币
            case SocketProtocol.COINOUT_RES:
                getView().coinOutSuccess(res.read(LoginResponse.class, res.getData()));
                break;
            //账号强迫下线
            case SocketProtocol.KICK_OUT_RES:
                getView().userKickDown(res.getMsg());
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(int protocol, String msg) {
        getView().dismisProgressDialog();
        switch (protocol) {
            case SocketProtocol.GET_DESK_ALL_RES:
                getView().getAllDeskFail(msg);
                break;
            //退币
            case SocketProtocol.COINOUT_RES:
                getView().coinOutFail(msg);
                break;
            default:
                break;
        }
    }

    public void getAllDesk() {
        getView().showProgressDialog(SocketProtocol.GET_DESK_ALL_REQ);
        model.getAllDesk(new FishGetAllDeskRequest(SocketProtocol.GET_DESK_ALL_REQ));
    }
}
