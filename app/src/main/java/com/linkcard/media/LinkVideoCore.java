package com.linkcard.media;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LinkVideoCore  implements Renderer
{
	private static final String TAG = "DBG";
	
	public int handle;
	public int duration;
    public long time = 0;
    public short framerate = 0;
    public long fpsTime = 0;
    public long frameTime = 0;
    public float avgFPS = 0;

	static 
	{
		System.loadLibrary("linkcardplayer");
		Log.d(TAG, "liblinkcardplayer.so");
	}
	
	public static final int  E_SYS_NOERR  = (0);
	public static final int  E_SYS_NOTINIT =  (-2);  //û�г�ʼ��ok
	public static final int  E_SYS_NOFRMDATA =  (-3);  //buffer����û��֡��
	public static final int  E_SYS_BADFRAME = (-4);  //���������ͼ���Ǵ����
	public static final int  E_SYS_NOFRAME = (-5);  //û�н������ȷ����Ƶ֡
	public static final int  E_SYS_UNKOWNERR = (-6);
	public static final int  E_SYS_ERRBITMAP = (-7);
	
	public static final int  SCALE_ASPECT_FIT = 0;
	public static final int  SCALE_ASPECT_FILL = 1;
	public static final int  SCALE_ASPECT_BALANCED =2;

	public int gethandle()
	{
		return handle;
	}
	
    public int linkcardplayer(String pUrl)
    {
    	handle = createplayer(pUrl,1,1,1,1);
    	return handle;
    }	
	
    /*
     * 
     * netmode: 0 udp;   1  tcp;  2 multicast; 
     * decmode: 0 software decoder;  1 hardware decoder;
     * enablevideo:  0 no;  1 yes; 
     * enableaudio:  0 no;  1 yes; 
     * 
     * */
	public native int createplayer(String pUrl,int netmode,int decmode,int enablevideo,int enableaudio);
	public native int destoryplayer(int handle);
	
	public native static int nativeInitRenderer(int handle);
    public native static int nativeChangeView(int handle,int w, int h);

    /*
     *  fit: 1 填充屏幕，0，按比例显示
     * */
	public native static int nativeRefreshFrame(int handle,int fit);
	
    public native int snapshot(int handle,String filename);
	
	public native int getVideoWidth(int handle);   //获取u视频宽度
	public native int getVideoHeight(int handle);  //获取视频高度
	
	public native int startPlayback(int handle);   //暂停显示
	public native int stopPlayback(int handle);	 //开启显示

	public native int startRecord(int handle,String filename);  //开始录像
	public native int stopRecord(int handle);                  //停止录像
	
	int bbbb = 0;
	private Handler nativeHandler;

	public void setNativeHandler(Handler nativeHandler) {
		this.nativeHandler = nativeHandler;
	}
	
	/**
	 * FPS固定化用クラス.
	 */
	private FPSManager fps = new FPSManager(50);
	
	/**
	 * 経過時間計測用
	 */
	private long oldTime = SystemClock.uptimeMillis();


	/**
	 * FPS固定で描画.
	 * @param gl
	 * @param dt 経過時間
	 */
	
	/**
	 * 实现渲染画面的函数
	 * When you call render, will implement this function
	 */
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		/*
		 *  有些手机，特别是小米系统的，如果刷新一屏数据，而又没有去真实填充的话，会导致闪频现象。所以这里增加等待数据的死循环，不知道有没有危险
		 *  
		 *  很多手机是不需要这个死循环等待
		 * */
        nativeRefreshFrame(handle,1);
	}
	
	/**
	 * 当画面大小改变的时候，会调用此函数
	 * When SurfaceView change size, to implement this function
	 */
	public boolean isFirstChange = true;
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		nativeChangeView(handle,width, height);
	}
	
	/**
	 * 当画面第一次创建的时候，会调用此函数
	 * When SurfaceView when first created , to achieve this function
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		nativeInitRenderer(handle);
	}
	
	/**
	 * FPSを設定します.
	 * @param fps FPS
	 */
	public void setFPS(int fps) {
		this.fps.setFPS(fps);
	}
	
	/**
	 * FPS固定化用クラス
	 */
	private static class FPSManager {
		/** フレームあたりミリ秒 */
		private long frameDuration;
		/** 1フレーム前の時間 */
		private long oldTime = 0;
		
		/**
		 * FPS指定でオブジェクトを生成します.
		 * @param FPS FPSの設定
		 */
		public FPSManager(int FPS) {
			frameDuration = 1000 / FPS;
		}
		
		/**
		 * FPSを設定します.
		 * @param FPS FPSの設定
		 */
		public void setFPS(int FPS) 
		{
			frameDuration = 1000 / FPS;
		}
		
		/**
		 * 1フレーム分スリープします.
		 */
		public void sleep() {
			long nowTime = SystemClock.uptimeMillis();
			long sleepTime = oldTime + frameDuration;
			oldTime = nowTime;
			while (true) {
				nowTime = SystemClock.uptimeMillis();
				if (nowTime < sleepTime) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// do nothing.
					}
				} else {
					oldTime = nowTime;
					break;
				}
			}
		}
	}
}
