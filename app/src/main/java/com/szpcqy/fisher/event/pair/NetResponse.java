package com.szpcqy.fisher.event.pair;

public class NetResponse {

    private String ip;
    private boolean isConnected;
    private boolean isRemote;

    public NetResponse(String ip, boolean iscon, boolean isRemote) {
        this.ip = ip;
        this.isConnected = iscon;
        this.isRemote = isRemote;
    }

    public String getIp() {
        return ip;
    }

    public boolean getIsConnected() {
        return isConnected;
    }

    public boolean getIsRemote(){
        return isRemote;
    }
}
