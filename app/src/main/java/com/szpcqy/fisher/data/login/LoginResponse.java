package com.szpcqy.fisher.data.login;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 10:38
 */
public class LoginResponse {

    /**
     * id : b85a14eb22c548a2be916ec4c05c8b74
     * loginname : user1
     * nickname :
     * password : e10adc3949ba59abbe56e057f20f883e
     * iconimg :
     * gold : 100000
     * adminid : 9b309b547aa042bbb413f5d7bba5a657
     * gender : 0
     * frozen : 0
     * createdate : 2018-08-23 14:02:13.133
     */

    private String id;
    private String loginname;
    private String nickname;
    private String password;
    private String iconimg;
    private int gold;
    private String adminid;
    private int gender;
    private int frozen;
    private String createdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIconimg() {
        return iconimg;
    }

    public void setIconimg(String iconimg) {
        this.iconimg = iconimg;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
