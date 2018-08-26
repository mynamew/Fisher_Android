package com.szpcqy.fisher.net;

/**
 * Created by Master on 2017/12/1.
 */

public class Gateway {

    public static final boolean API_RETRY_AFTER_FALTURE = false;
    public static final int API_TIMEOUT = 10;

    //总台WIFI信息
//    static public final String APP_NAME = "TreasureMachine";
//    static public final String APP_PORT = "8080";
//    static public final String SERVER_SSID = "ShangtongTech";
//    static public final String SERVER_SSPW = "15001455127";
//    static public final String SERVER_IP = "192.168.0.112";

    static public final String APP_NAME = "TreasureMachine";
    static public final String APP_PORT = "8080";
    static public final String SERVER_SSID = "VGA-30BE";
    static public final String SERVER_SSPW = "12345678";
    static public final String SERVER_IP = "192.168.11.235";


    //游戏机1
//    static public final String MACHINE_1_SSID = "VGA-30BE";
//    static public final String MACHINE_1_SSPW = "12345678";
//    static public final String MACHINE_1_IP = "192.168.11.235";
//    static public final String MACHINE_1_COM = "COM7";




    static public final String MD_LOGIN = "game/login";
    static public final String MD_SWITCHLEVEL = "game/switchlevel";
    static public final String MD_DIRECTIONANDFIRE = "game/directionandfire";
    static public final String MD_COININ = "game/coinin";
    static public final String MD_COINOUT = "game/coinout";



    static public final String getWSURL(String app, String ip, String port){
        return "ws://"+ip+":"+port+"/"+app+"/game";
    }



}
