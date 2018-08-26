package com.szpcqy.fisher.data.fish;

import com.szpcqy.fisher.net.vo.GameSLotVO;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 13:40
 */
public class FishGetAllDeskResponse {

    /**
     * id : b9cec0d07b9f404c8d83e0ede84b98be
     * devicename : 捕鱼小能手
     * devicetype : 1
     * enable : 1
     * remark : 这是捕鱼机1
     * createdate : 2018-08-23 14:02:27.057
     * ratiocoinscore : 100.0
     * ratiodevicescore : 1000.0
     * devicessid : VGA-30BE
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
     * slotVO1 : {"createdate":"2018-08-23 14:02:38.62","id":"a2e2bd7a383c480492809baa7fdf818d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM6","enable":1}
     * slotVO3 : {"createdate":"2018-08-23 14:02:38.62","id":"15bcb93cfdbb47bfa83014e309c007c4","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM3","enable":1}
     * slotVO5 : {"createdate":"2018-08-23 14:02:38.62","id":"00afaf3f2c3649408078da03f3c35b5d","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM4","enable":1}
     * slotVO7 : {"createdate":"2018-08-23 14:02:38.62","id":"bb01bbb4e52b43588477c4958d69ce47","deviceid":"b9cec0d07b9f404c8d83e0ede84b98be","com":"COM5","enable":1}
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
    private GameSLotVO slotVO1;
    private GameSLotVO slotVO2;
    private GameSLotVO slotVO3;
    private GameSLotVO slotVO4;
    private GameSLotVO slotVO5;
    private GameSLotVO slotVO6;
    private GameSLotVO slotVO7;
    private GameSLotVO slotVO8;

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

    public GameSLotVO getSlotVO1() {
        return slotVO1;
    }

    public void setSlotVO1(GameSLotVO slotVO1) {
        this.slotVO1 = slotVO1;
    }

    public GameSLotVO getSlotVO3() {
        return slotVO3;
    }

    public void setSlotVO3(GameSLotVO slotVO3) {
        this.slotVO3 = slotVO3;
    }

    public GameSLotVO getSlotVO5() {
        return slotVO5;
    }

    public void setSlotVO5(GameSLotVO slotVO5) {
        this.slotVO5 = slotVO5;
    }

    public GameSLotVO getSlotVO7() {
        return slotVO7;
    }

    public void setSlotVO7(GameSLotVO slotVO7) {
        this.slotVO7 = slotVO7;
    }

    public GameSLotVO getSlotVO2() {
        return slotVO2;
    }

    public void setSlotVO2(GameSLotVO slotVO2) {
        this.slotVO2 = slotVO2;
    }

    public GameSLotVO getSlotVO4() {
        return slotVO4;
    }

    public void setSlotVO4(GameSLotVO slotVO4) {
        this.slotVO4 = slotVO4;
    }

    public GameSLotVO getSlotVO6() {
        return slotVO6;
    }

    public void setSlotVO6(GameSLotVO slotVO6) {
        this.slotVO6 = slotVO6;
    }

    public GameSLotVO getSlotVO8() {
        return slotVO8;
    }

    public void setSlotVO8(GameSLotVO slotVO8) {
        this.slotVO8 = slotVO8;
    }
    /**
     *
     * @param pos 从1开始
     * @return
     */
    public GameSLotVO getSlot(int pos){
        if(pos==1) return slotVO1;
        if(pos==2) return slotVO2;
        if(pos==3) return slotVO3;
        if(pos==4) return slotVO4;
        if(pos==5) return slotVO5;
        if(pos==6) return slotVO6;
        if(pos==7) return slotVO7;
        if(pos==8) return slotVO8;
        return null;
    }
}
