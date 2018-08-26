package com.szpcqy.fisher.tool;

/**
 * Created by Master on 2017/11/20.
 */

public class ExitTool {

    static private   long lastT = 0;

    static public boolean canExit(int ms) {
        if ((System.currentTimeMillis() - lastT) < ms) {
            return true;
        } else {
            lastT = System.currentTimeMillis();
            return false;
        }
    }

}
