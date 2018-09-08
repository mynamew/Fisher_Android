package com.szpcqy.fisher.vcard;

import com.linkcard.media.LinkVideoCore;
import com.linkcard.media.VideoSurfaceView;

public class VideoCard {

    static private LinkVideoCore videoStream;

    static public void playVideo(VideoSurfaceView view, String url){
        videoStream = new LinkVideoCore();
        videoStream.linkcardplayer(url);
        view.setRenderer(videoStream);
        videoStream.startPlayback(videoStream.gethandle());
    }

    static public void stopVideo(){
        videoStream.stopPlayback(videoStream.gethandle());
        videoStream.destoryplayer(videoStream.gethandle());
    }

}
