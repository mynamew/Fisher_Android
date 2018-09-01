package com.szpcqy.fisher.data.play;

/**
 * $dsc  玩的请求
 * author: timi
 * create at: 2018-08-30 10:36
 */
public class PlayFishRequest {
    public PlayFishRequest(int protocol, int isLeftTouch, int isRightTouch, int isUpTouch, int isDownTouch, int isFireTouch) {
        this.protocol = protocol;
        this.isLeftTouch = isLeftTouch;
        this.isRightTouch = isRightTouch;
        this.isUpTouch = isUpTouch;
        this.isDownTouch = isDownTouch;
        this.isFireTouch = isFireTouch;
    }

    private int protocol;
    private int isLeftTouch;
    private int isRightTouch ;
    private int isUpTouch ;
    private int isDownTouch ;
    private int isFireTouch ;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getIsLeftTouch() {
        return isLeftTouch;
    }

    public void setIsLeftTouch(int isLeftTouch) {
        this.isLeftTouch = isLeftTouch;
    }

    public int getIsRightTouch() {
        return isRightTouch;
    }

    public void setIsRightTouch(int isRightTouch) {
        this.isRightTouch = isRightTouch;
    }

    public int getIsUpTouch() {
        return isUpTouch;
    }

    public void setIsUpTouch(int isUpTouch) {
        this.isUpTouch = isUpTouch;
    }

    public int getIsDownTouch() {
        return isDownTouch;
    }

    public void setIsDownTouch(int isDownTouch) {
        this.isDownTouch = isDownTouch;
    }

    public int getIsFireTouch() {
        return isFireTouch;
    }

    public void setIsFireTouch(int isFireTouch) {
        this.isFireTouch = isFireTouch;
    }

    @Override
    public String toString() {
        return "PlayFishRequest{" +
                "protocol=" + protocol +
                ", isLeftTouch=" + isLeftTouch +
                ", isRightTouch=" + isRightTouch +
                ", isUpTouch=" + isUpTouch +
                ", isDownTouch=" + isDownTouch +
                ", isFireTouch=" + isFireTouch +
                '}';
    }
}
