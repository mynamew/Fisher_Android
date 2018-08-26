package com.szpcqy.fisher.data.login;

import com.szpcqy.fisher.event.pair.CommonRequest;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-25 20:44
 */
public class LoginRequest {

    public LoginRequest(int protocol, String loginname, String password) {
        this.protocol = protocol;
        this.loginname = loginname;
        this.password = password;
    }

    /**
     * protocol : 0X100001
     * loginname : user2
     * password : 123456
     */

    private int protocol;
    private String loginname;
    private String password;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
