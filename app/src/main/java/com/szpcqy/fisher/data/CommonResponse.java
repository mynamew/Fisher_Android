package com.szpcqy.fisher.data;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 10:36
 */
public class CommonResponse<T> {

    /**
     * protocol : 1048578
     * code : 1000
     * msg : 操作成功
     * data : {"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":100000,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133"}
     */

    private int protocol;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
