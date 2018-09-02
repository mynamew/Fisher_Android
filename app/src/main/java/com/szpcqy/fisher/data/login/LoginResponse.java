package com.szpcqy.fisher.data.login;

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

    private DeviceVOBean deviceVO;
    private SlotVOBean slotVO;
    private UserVOBeanXX userVO;

    public DeviceVOBean getDeviceVO() {
        return deviceVO;
    }

    public void setDeviceVO(DeviceVOBean deviceVO) {
        this.deviceVO = deviceVO;
    }

    public SlotVOBean getSlotVO() {
        return slotVO;
    }

    public void setSlotVO(SlotVOBean slotVO) {
        this.slotVO = slotVO;
    }

    public UserVOBeanXX getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVOBeanXX userVO) {
        this.userVO = userVO;
    }

    public static class DeviceVOBean {
        /**
         * id : b9cec0d07b9f404c8d83e0ede84b98be
         * devicename : 捕鱼小能手
         * devicetype : 1
         * enable : 1
         * remark : 这是捕鱼机1
         * createdate : 2018-08-23 14:02:27.057
         * ratiocoinscore : 100.0
         * ratiodevicescore : 1000.0
         * devicessid : VGA-0276
         * devicesspw : 12345678
         * videoip : 192.168.11.123
         * serverip : 192.168.11.235
         * slot1 : a2e2bd7a383c480492809baa7fdf818d
         * slot2 :
         * slot3 : 15bcb93cfdbb47bfa83014e309c007c4
         * slot4 :
         * slot5 : 00afaf3f2c3649408078da03f3c35b5d
         * slot6 :
         * slot7 : bb01bbb4e52b43588477c4958d69ce47
         * slot8 :
         * slotVO1 : {"createdate":"2018-08-23 14:02:38.62","id":"a2e2bd7a383c480492809baa7fdf818d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM6","enable":1,"positionNumber":0}
         * slotVO3 : {"createdate":"2018-08-23 14:02:38.62","id":"15bcb93cfdbb47bfa83014e309c007c4","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM3","enable":1,"userVO":{"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0},"positionNumber":0}
         * slotVO5 : {"createdate":"2018-08-23 14:02:38.62","id":"00afaf3f2c3649408078da03f3c35b5d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM4","enable":1,"positionNumber":0}
         * slotVO7 : {"createdate":"2018-08-23 14:02:38.62","id":"bb01bbb4e52b43588477c4958d69ce47","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM5","enable":1,"positionNumber":0}
         */

        private String id;
        private String devicename;
        private int devicetype;
        private int enable;
        private String remark;
        private String createdate;
        private double ratiocoinscore;
        private double ratiodevicescore;
        private String devicessid;
        private String devicesspw;
        private String videoip;
        private String serverip;
        private String slot1;
        private String slot2;
        private String slot3;
        private String slot4;
        private String slot5;
        private String slot6;
        private String slot7;
        private String slot8;
        private SlotVO1Bean slotVO1;
        private SlotVO3Bean slotVO3;
        private SlotVO5Bean slotVO5;
        private SlotVO7Bean slotVO7;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public int getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(int devicetype) {
            this.devicetype = devicetype;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public double getRatiocoinscore() {
            return ratiocoinscore;
        }

        public void setRatiocoinscore(double ratiocoinscore) {
            this.ratiocoinscore = ratiocoinscore;
        }

        public double getRatiodevicescore() {
            return ratiodevicescore;
        }

        public void setRatiodevicescore(double ratiodevicescore) {
            this.ratiodevicescore = ratiodevicescore;
        }

        public String getDevicessid() {
            return devicessid;
        }

        public void setDevicessid(String devicessid) {
            this.devicessid = devicessid;
        }

        public String getDevicesspw() {
            return devicesspw;
        }

        public void setDevicesspw(String devicesspw) {
            this.devicesspw = devicesspw;
        }

        public String getVideoip() {
            return videoip;
        }

        public void setVideoip(String videoip) {
            this.videoip = videoip;
        }

        public String getServerip() {
            return serverip;
        }

        public void setServerip(String serverip) {
            this.serverip = serverip;
        }

        public String getSlot1() {
            return slot1;
        }

        public void setSlot1(String slot1) {
            this.slot1 = slot1;
        }

        public String getSlot2() {
            return slot2;
        }

        public void setSlot2(String slot2) {
            this.slot2 = slot2;
        }

        public String getSlot3() {
            return slot3;
        }

        public void setSlot3(String slot3) {
            this.slot3 = slot3;
        }

        public String getSlot4() {
            return slot4;
        }

        public void setSlot4(String slot4) {
            this.slot4 = slot4;
        }

        public String getSlot5() {
            return slot5;
        }

        public void setSlot5(String slot5) {
            this.slot5 = slot5;
        }

        public String getSlot6() {
            return slot6;
        }

        public void setSlot6(String slot6) {
            this.slot6 = slot6;
        }

        public String getSlot7() {
            return slot7;
        }

        public void setSlot7(String slot7) {
            this.slot7 = slot7;
        }

        public String getSlot8() {
            return slot8;
        }

        public void setSlot8(String slot8) {
            this.slot8 = slot8;
        }

        public SlotVO1Bean getSlotVO1() {
            return slotVO1;
        }

        public void setSlotVO1(SlotVO1Bean slotVO1) {
            this.slotVO1 = slotVO1;
        }

        public SlotVO3Bean getSlotVO3() {
            return slotVO3;
        }

        public void setSlotVO3(SlotVO3Bean slotVO3) {
            this.slotVO3 = slotVO3;
        }

        public SlotVO5Bean getSlotVO5() {
            return slotVO5;
        }

        public void setSlotVO5(SlotVO5Bean slotVO5) {
            this.slotVO5 = slotVO5;
        }

        public SlotVO7Bean getSlotVO7() {
            return slotVO7;
        }

        public void setSlotVO7(SlotVO7Bean slotVO7) {
            this.slotVO7 = slotVO7;
        }

        public static class SlotVO1Bean {
            /**
             * createdate : 2018-08-23 14:02:38.62
             * id : a2e2bd7a383c480492809baa7fdf818d
             * deviceid : b9cec0d07b9f404c8d83e0ede84b98be
             * com : COM6
             * enable : 1
             * positionNumber : 0
             */

            private String createdate;
            private String id;
            private String deviceid;
            private String com;
            private int enable;
            private int positionNumber;

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeviceid() {
                return deviceid;
            }

            public void setDeviceid(String deviceid) {
                this.deviceid = deviceid;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(int positionNumber) {
                this.positionNumber = positionNumber;
            }
        }

        public static class SlotVO3Bean {
            /**
             * createdate : 2018-08-23 14:02:38.62
             * id : 15bcb93cfdbb47bfa83014e309c007c4
             * deviceid : b9cec0d07b9f404c8d83e0ede84b98be
             * com : COM3
             * enable : 1
             * userVO : {"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0}
             * positionNumber : 0
             */

            private String createdate;
            private String id;
            private String deviceid;
            private String com;
            private int enable;
            private UserVOBean userVO;
            private int positionNumber;

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeviceid() {
                return deviceid;
            }

            public void setDeviceid(String deviceid) {
                this.deviceid = deviceid;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public UserVOBean getUserVO() {
                return userVO;
            }

            public void setUserVO(UserVOBean userVO) {
                this.userVO = userVO;
            }

            public int getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(int positionNumber) {
                this.positionNumber = positionNumber;
            }

            public static class UserVOBean {
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
        }

        public static class SlotVO5Bean {
            /**
             * createdate : 2018-08-23 14:02:38.62
             * id : 00afaf3f2c3649408078da03f3c35b5d
             * deviceid : b9cec0d07b9f404c8d83e0ede84b98be
             * com : COM4
             * enable : 1
             * positionNumber : 0
             */

            private String createdate;
            private String id;
            private String deviceid;
            private String com;
            private int enable;
            private int positionNumber;

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeviceid() {
                return deviceid;
            }

            public void setDeviceid(String deviceid) {
                this.deviceid = deviceid;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(int positionNumber) {
                this.positionNumber = positionNumber;
            }
        }

        public static class SlotVO7Bean {
            /**
             * createdate : 2018-08-23 14:02:38.62
             * id : bb01bbb4e52b43588477c4958d69ce47
             * deviceid : b9cec0d07b9f404c8d83e0ede84b98be
             * com : COM5
             * enable : 1
             * positionNumber : 0
             */

            private String createdate;
            private String id;
            private String deviceid;
            private String com;
            private int enable;
            private int positionNumber;

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDeviceid() {
                return deviceid;
            }

            public void setDeviceid(String deviceid) {
                this.deviceid = deviceid;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(int positionNumber) {
                this.positionNumber = positionNumber;
            }
        }
    }

    public static class SlotVOBean {
        /**
         * createdate : 2018-08-23 14:02:38.62
         * id : 15bcb93cfdbb47bfa83014e309c007c4
         * deviceid : b9cec0d07b9f404c8d83e0ede84b98be
         * com : COM3
         * enable : 1
         * userVO : {"id":"b85a14eb22c548a2be916ec4c05c8b74","loginname":"user1","nickname":"","password":"e10adc3949ba59abbe56e057f20f883e","iconimg":"","gold":101700,"adminid":"9b309b547aa042bbb413f5d7bba5a657","gender":0,"frozen":0,"createdate":"2018-08-23 14:02:13.133","loginStatus":0}
         * positionNumber : 0
         */

        private String createdate;
        private String id;
        private String deviceid;
        private String com;
        private int enable;
        private UserVOBeanX userVO;
        private int positionNumber;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public UserVOBeanX getUserVO() {
            return userVO;
        }

        public void setUserVO(UserVOBeanX userVO) {
            this.userVO = userVO;
        }

        public int getPositionNumber() {
            return positionNumber;
        }

        public void setPositionNumber(int positionNumber) {
            this.positionNumber = positionNumber;
        }

        public static class UserVOBeanX {
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
}
