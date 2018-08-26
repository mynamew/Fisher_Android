package com.szpcqy.fisher.event.pair;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class SocketResonse {
    private int protocol;
    private int code;
    private String msg;
    private JsonElement data;

    public int getProtocol() {
        return protocol;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JsonElement getData() {
        return data;
    }

    public <T> T read(final Class<T> cs, JsonElement obj) {
        T rt = new Gson().fromJson(obj, cs);
        return rt;
    }

    public <T> ArrayList<T> readList(final Class<T> cs, JsonElement obj) {
        ArrayList<T> resultlist = new ArrayList<>();
        try {
            JsonArray jlist = (JsonArray) obj;
            for(JsonElement jitem:jlist){
                T item = new Gson().fromJson(jitem, cs);
                resultlist.add(item);
            }
        } catch (Exception e) {
            //
        }
        return resultlist;
    }


}
