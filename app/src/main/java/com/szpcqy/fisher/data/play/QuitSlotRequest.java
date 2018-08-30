package com.szpcqy.fisher.data.play;

/**
 * $dsc 退出控制位的请求
 * author: timi
 * create at: 2018-08-30 10:34
 */
public class QuitSlotRequest {
    public QuitSlotRequest(int protocol) {
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
