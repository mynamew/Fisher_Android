package com.szpcqy.fisher.data.login;

import com.szpcqy.fisher.data.fish.FishGetAllDeskResponse;
import com.szpcqy.fisher.net.vo.GameSLotVO;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 10:38
 */
public class LoginResponse {

    /**
     * deviceVO : {"id":"b9cec0d07b9f404c8d83e0ede84b98be","devicename":"捕鱼小能手","devicetype":1,"enable":1,"remark":"这是捕鱼机1","createdate":"2018-08-23 14:02:27.057","ratiocoinscore":100,"ratiodevicescore":1000,"devicessid":"VGA-0276","devicesspw":"12345678","videoip":"192.168.11.123","serverip":"192.168.11.235","slot1":"a2e2bd7a383c480492809baa7fdf818d","slot2":"","slot3":"15bcb93cfdbb47bfa83014e309c007c4","slot4":"","slot5":"00afaf3f2c3649408078da03f3c35b5d","slot6":"","slot7":"bb01bbb4e52b43588477c4958d69ce47","slot8":"","slotVO1":{"createdate":"2018-08-23 14:02:38.62","id":"a2e2bd7a383c480492809baa7fdf818d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM6","enable":1,"positionNumber":0},"slotVO3":{"createdate":"2018-08-23 14:02:38.62","id":"15bcb93cfdbb47bfa83014e309c007c4","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM3","enable":1,"userVO":{"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0},"positionNumber":0},"slotVO5":{"createdate":"2018-08-23 14:02:38.62","id":"00afaf3f2c3649408078da03f3c35b5d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM4","enable":1,"positionNumber":0},"slotVO7":{"createdate":"2018-08-23 14:02:38.62","id":"bb01bbb4e52b43588477c4958d69ce47","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM5","enable":1,"positionNumber":0}}
     * slotVO : {"createdate":"2018-08-23 14:02:38.62","id":"15bcb93cfdbb47bfa83014e309c007c4","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM3","enable":1,"userVO":{"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0},"positionNumber":0}
     * userVO : {"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0}
     */

    private FishGetAllDeskResponse deviceVO;
    private GameSLotVO slotVO;
    private UserVOBeanXX userVO;

    public UserVOBeanXX getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVOBeanXX userVO) {
        this.userVO = userVO;
    }


    public static class UserVOBeanXX {
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
    }

    public FishGetAllDeskResponse getDeviceVO() {
        return deviceVO;
    }

    public void setDeviceVO(FishGetAllDeskResponse deviceVO) {
        this.deviceVO = deviceVO;
    }

    public GameSLotVO getSlotVO() {
        return slotVO;
    }

    public void setSlotVO(GameSLotVO slotVO) {
        this.slotVO = slotVO;
    }
}