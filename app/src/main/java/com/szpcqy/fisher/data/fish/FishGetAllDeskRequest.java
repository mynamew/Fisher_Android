package com.szpcqy.fisher.data.fish;

/**
 * $dsc  捕鱼获取所有的桌位信息
 * author: timi
 * create at: 2018-08-26 13:39
 */
public class FishGetAllDeskRequest {
    public FishGetAllDeskRequest(int protocol) {
        this.protocol = protocol;
    }

    public FishGetAllDeskRequest(int protocol, String deskid) {
        this.protocol = protocol;
        this.deskid = deskid;
    }

    /**
     * protocol : 1
     * deskid :
     */

    private int protocol;
    private String deskid;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getDeskid() {
        return deskid;
    }

    public void setDeskid(String deskid) {
        this.deskid = deskid;
    }
}
