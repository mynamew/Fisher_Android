package com.szpcqy.fisher.event.pair;

import com.google.gson.Gson;

import java.util.HashMap;

public class SocketRequest {

    private int protocol;
    private HashMap<String, Object> map;

    public SocketRequest(int protocol){
            map = new HashMap<>();
            map.put("protocol", protocol);
    }

    public void add(String key , Object value){
        map.put(key, value);
    }

    @Override
    public String toString() {
        return new Gson().toJson(map);
    }
}
