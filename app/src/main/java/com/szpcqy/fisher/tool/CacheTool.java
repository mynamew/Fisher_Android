package com.szpcqy.fisher.tool;


import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CacheTool {
    static public LoginResponse response = null;
    static public List<FishGetAllDeskResponse> responseFishDesks = null;
    static public String psw="";
    /**
     * 设置登录的信息 缓存
     *
     * @param res
     */
    static public void setCurrentLoginResponse(LoginResponse res) {
        response = res;
        if (response != null) {
            EventBus.getDefault().post(new LoginResponse());
        }
    }

    static public LoginResponse getCurrentLoginResponse() {
        return response;
    }

    /**
     * 桌位的信息 缓存
     *
     * @param res
     */
    static public void setFishGetAllDeskResponse(List<FishGetAllDeskResponse> res) {
        responseFishDesks = res;
        if (responseFishDesks != null) {
        }
    }

    /**
     * 更新桌位的信息
     *
     * @param res
     */
    static public void updateFishGetAllDeskResponse(FishGetAllDeskResponse res) {
        if (responseFishDesks != null) {
            for (int i = 0; i < responseFishDesks.size(); i++) {
                if (responseFishDesks.get(i).getId().equals(res.getId())) {
                    responseFishDesks.set(i, res);
                }
            }
        }
    }

    static public List<FishGetAllDeskResponse> getFishGetAllDeskResponse() {
        return responseFishDesks;
    }

    /**
     * 获取当前金币数
     *
     * @return
     */
    static public int getCurentGold() {
        return response.getGold();
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    static public String getCurentUsername() {
        return response.getLoginname();
    }

    /**
     * 获取密码
     * @return
     */
    public static String getPsw() {
        return psw;
    }

    /**
     *  设置密码
     * @param psw
     */
    public static void setPsw(String psw) {
        CacheTool.psw = psw;
    }
}
