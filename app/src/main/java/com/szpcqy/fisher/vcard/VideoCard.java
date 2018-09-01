package com.szpcqy.fisher.vcard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

import com.linkcard.media.LinkVideoCore;
import com.linkcard.media.VideoSurfaceView;


public class VideoCard {

    static private final String IP = "192.168.11.123";

    static private LinkVideoCore videoStream;


    static {
        videoStream = new com.linkcard.media.LinkVideoCore();
        videoStream.sysinit2(IP);
    }

    static public boolean isOpenGLESSupport(Context ctx){
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }

    static public void playVideo(VideoSurfaceView view){
        view.setRenderer(videoStream);
        videoStream.startPlayback();

//        int wid = videoStream.getVideoWidth();
//        int hei = videoStream.getVideoHeight();
//
//        ViewGroup.LayoutParams lp = view.getLayoutParams();
//        lp.width = wid;
//        lp.height = hei;
//
//        view.setLayoutParams(lp);
    }

    static public void stopVideo(){
        videoStream.stopPlayback();
    }

    static public void destroy(){
        videoStream.stopPlayback();
        videoStream.sysuninit();
    }

}
