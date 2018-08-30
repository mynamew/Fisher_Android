package com.szpcqy.fisher.ui.fish.play;

import com.szpcqy.fisher.data.login.LoginResponse;
import com.szpcqy.fisher.ui.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 20:59
 */
public interface FishPlayView extends MvpBaseView {
    /**
     * 加币成功
     * @param loginResponse
     */
    void addCoinSuccess(LoginResponse loginResponse);

    /**
     * 退出控制位成功
     */
    void quitSlotSuccess();

    /**
     * 退币成功
     */
    void returnCoinSuccess();

    /**
     * 切换方向/发射成功
     */
    void fireSuccess();

    /**
     * 切换成功
     */
    void switchStrengthSuccess();
}
