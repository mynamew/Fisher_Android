package com.szpcqy.fisher.data.play;

/**
 * $dsc 加币的请求
 * author: timi
 * create at: 2018-08-30 10:13
 */
public class AddCoinRequest {
    public AddCoinRequest(int coin, int protocol) {
        this.coin = coin;
        this.protocol = protocol;
    }

    private int coin;
    private int protocol;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}
