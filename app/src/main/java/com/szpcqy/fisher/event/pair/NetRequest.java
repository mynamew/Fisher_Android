package com.szpcqy.fisher.event.pair;

public class NetRequest {

    private String ip;

    public enum RequestType{
        CONNECT, DISCONNECT
    }

    private RequestType type;

    public NetRequest(RequestType type, String ip){
        this.type = type;
        this.ip = ip;
    }

    public RequestType getType() {
        return type;
    }

    public String getIp() {
        return ip;
    }
}
