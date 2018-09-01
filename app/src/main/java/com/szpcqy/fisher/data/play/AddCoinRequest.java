package com.szpcqy.fisher.data.play;

/**
 * $dsc 加币的请求
 * author: timi
 * create at: 2018-08-30 10:13
 */
public class AddCoinRequest {

    public AddCoinRequest(int protocol, int coin) {
        this.protocol = protocol;
        this.coin = coin;
    }

    /**
     * protocol : 0X100013
     * coin : 2
     */

    private int protocol;
    private int coin;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
