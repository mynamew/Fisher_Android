package com.szpcqy.fisher.data.play;

/**
 * $dsc 退币的请求
 * author: timi
 * create at: 2018-08-30 10:16
 */
public class ReturnCoinRequest {
    public ReturnCoinRequest(int protocol) {
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
