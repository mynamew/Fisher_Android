package com.szpcqy.fisher.data.play;

/**
 * $dsc 切换大炮的请求
 * author: timi
 * create at: 2018-08-30 10:34
 */
public class SwitchStrengthRequest {
    public SwitchStrengthRequest(int protocol) {
        this.protocol = protocol;
    }

    private int protocol;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}
