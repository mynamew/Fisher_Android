package com.szpcqy.fisher.data.fish;

/**
 * $dsc
 * author: timi
 * create at: 2018-08-26 19:31
 */
public class FishJoinSlotRequest {


    /**
     * protocol : 1
     * deskid :
     * slotid :
     */
    public FishJoinSlotRequest(int protocol, String deskid, String slotid) {
        this.protocol = protocol;
        this.deskid = deskid;
        this.slotid = slotid;
    }
    private int protocol;
    private String deskid;
    private String slotid;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getDeskid() {
        return deskid;
    }

    public void setDeskid(String deskid) {
        this.deskid = deskid;
    }

    public String getSlotid() {
        return slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }
}
