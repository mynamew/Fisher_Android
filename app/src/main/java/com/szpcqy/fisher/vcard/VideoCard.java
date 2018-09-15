package com.szpcqy.fisher.vcard;

import android.opengl.GLSurfaceView;

import com.linkcard.media.LinkVideoCore;
import com.linkcard.media.VideoSurfaceView;

public class VideoCard {

    static private LinkVideoCore videoStream;

    static public void playVideo(VideoSurfaceView view, String url) {
        videoStream = new LinkVideoCore();
        videoStream.linkcardplayer(url);
        view.setRenderer(videoStream);
        view.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        videoStream.startPlayback(videoStream.gethandle());
    }

    static public void stopVideo() {
        videoStream.stopPlayback(videoStream.gethandle());
        videoStream.destoryplayer(videoStream.gethandle());
    }

    static public void reStartPlay(VideoSurfaceView view,String url) {
        videoStream.linkcardplayer(url);
        videoStream.startPlayback(videoStream.gethandle());
    }
}
