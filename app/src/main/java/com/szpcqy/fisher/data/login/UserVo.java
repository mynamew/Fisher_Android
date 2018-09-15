package com.szpcqy.fisher.data.login;

/**
 * $dsc
 * author: timi
 * create at: 2018-09-08 15:22
 */
public class UserVo {
    /**
     * id : b85a14eb22c548a2be916ec4c05c8b74
     * loginname : user1
     * nickname :
     * password : e10adc3949ba59abbe56e057f20f883e
     * iconimg :
     * gold : 101700
     * adminid : 9b309b547aa042bbb413f5d7bba5a657
     * gender : 0
     * frozen : 0
     * createdate : 2018-08-23 14:02:13.133
     * loginStatus : 0
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
    private int loginStatus;

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

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", loginname='" + loginname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", iconimg='" + iconimg + '\'' +
                ", gold=" + gold +
                ", adminid='" + adminid + '\'' +
                ", gender=" + gender +
                ", frozen=" + frozen +
                ", createdate='" + createdate + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }
}
