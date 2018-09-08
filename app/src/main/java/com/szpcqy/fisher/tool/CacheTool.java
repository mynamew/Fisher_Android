package com.szpcqy.fisher.tool;


import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.data.login.UserVo;

import org.greenrobot.eventbus.EventBus;

public class CacheTool {
    static public LoginResponse response = null;
    static public UserVo userVo = null;
    static public FishGetAllDeskResponse currentFishDesk = null;
    static public String psw = "";

    /**
     * 设置登录的信息 缓存
     *
     * @param res
     */
    static public void setCurrentLoginResponse(LoginResponse res) {
        response = res;
        if (response != null) {
            EventBus.getDefault().post(res);
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
    static public void setCurrentFishDesk(FishGetAllDeskResponse res) {
        currentFishDesk = res;
    }

    static public FishGetAllDeskResponse getCurrentFishDesk() {
        return currentFishDesk;
    }

    /**
     * 获取当前金币数
     *
     * @return
     */
    static public int getCurentGold() {
        return response.getUserVO().getGold();
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    static public String getCurentUsername() {
        return response.getUserVO().getLoginname();
    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    static public String getCurrentId() {
        return response.getUserVO().getId();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public static String getPsw() {
        return psw;
    }

    /**
     * 设置密码
     *
     * @param psw
     */
    public static void setPsw(String psw) {
        CacheTool.psw = psw;
    }
}
