package com.szpcqy.fisher.ui.fish.desk;

import android.content.Context;

import com.szpcqy.fisher.data.fish.FishGetAllDeskRequest;
import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.fish.FishJoinSlotRequest;
import com.szpcqy.fisher.data.login.LoginRequest;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.net.SocketProtocol;
import com.szpcqy.fisher.ui.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 15:38
 */
public class FishDeskPresenter extends MvpBasePresenter<FishDeskView> {
    private FishDeskModel model;

    public FishDeskPresenter(Context context) {
        super(context);
    }

    @Override
    public void initModel() {
        model = new FishDeskModel();
    }

    @Override
    public void onSucess(int protocol, SocketResonse res) {
        switch (res.getProtocol()) {
            //登录成功
            case SocketProtocol.LOGIN_RES:
                getView().loginSuccess(res.read(LoginResponse.class, res.getData()));
                break;
            //获取所有的桌位成功
            case SocketProtocol.GET_DESK_SINGLE_RES:
                getView().getSingleDeskSuccess(res.read(FishGetAllDeskResponse.class, res.getData()));
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
            //加入作为成功
            case SocketProtocol.JOIN_SLOT_RES:
                getView().joinSlotSuccess(res);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(int protocol, String msg) {
        getView().dismisProgressDialog();
        switch (protocol) {
            //自动登录失败
            case SocketProtocol.LOGIN_RES:
                getView().loginFail(msg);
                break;
            //获取桌位失败
            case SocketProtocol.GET_DESK_SINGLE_RES:
                getView().getSingleDeskFail(msg);
                break;
            //退币
            case SocketProtocol.COINOUT_RES:
                getView().coinOutFail(msg);
                break;
            //加入座位失败
            case SocketProtocol.JOIN_SLOT_RES:
                getView().joinSlotFail(msg);
                break;
            default:
                break;
        }
    }

    /**
     * 登录的请求方法
     *
     * @param request
     */
    public void login(LoginRequest request) {
        getView().showProgressDialog(request.getProtocol());
        model.LoginRequest(request);
    }

    /**
     * 获取桌位的信息
     */
    public void getAllDesk(FishGetAllDeskRequest allDeskRequest) {
        getView().showProgressDialog(SocketProtocol.GET_DESK_SINGLE_REQ);
        model.getAllDesk(allDeskRequest);
    }

    /**
     * 加入座位
     */
    public void joinSlot(FishJoinSlotRequest request) {
        getView().showProgressDialog(request.getProtocol());
        model.joinSlot(request);
    }
}
