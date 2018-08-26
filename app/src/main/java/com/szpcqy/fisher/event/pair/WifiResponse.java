package com.szpcqy.fisher.event.pair;

public class WifiResponse {

    private String ssid;
    private boolean isConnected;

    public WifiResponse(String id, boolean con){
        ssid = id;
        isConnected = con;
    }


    public String getSsid() {
        return ssid;
    }

    public boolean getIsConnected() {
        return isConnected;
    }
}
