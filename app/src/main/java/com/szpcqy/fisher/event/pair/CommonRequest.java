package com.szpcqy.fisher.event.pair;

import com.google.gson.Gson;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-25 20:41
 */
public class CommonRequest<T> {
    T t;

    public CommonRequest(T t) {
        this.t = t;
    }

    public String _tostring() {
        return new Gson().toJson(t);
    }
}
