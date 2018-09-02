package com.linkcard.media;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class LinkVideoCore  implements Renderer
{
	private static final String TAG = "DBG";

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
	
	public native int sysinit(String pIP);
	public native int sysinit2(String pIP);
	public native int sysuninit();
	
	public native static int nativeInitRenderer();
    public native static int nativeChangeView(int w, int h);
    public native static int nativeDecodeFrame();
    public native static int nativeDraw(int fit);
	public native static int nativeAspect(int aspect); 
	public native static int nativeRefreshFrame(int fit);
	
    public native int snapshot(String filename);
	
	public native int getVideoWidth();   //获取u视频宽度
	public native int getVideoHeight();  //获取视频高度
	
	public native int startPlayback();   //暂停显示
	public native int stopPlayback();	 //开启显示

	public native int startRecord(String filename);  //开始录像
	public native int stopRecord();                  //停止录像
	
	int bbbb = 0;
	private Handler nativeHandler;

	public void setNativeHandler(Handler nativeHandler) {
		this.nativeHandler = nativeHandler;
	}
	
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
		nativeRefreshFrame(0);

		/*
		 *  这个才是合理的方案
		 *  
		 * */
//		if(0 == nativeDecodeFrame())
//		{
//			nativeDraw(1);
//		}
	}
	
	/**
	 * 当画面大小改变的时候，会调用此函数
	 * When SurfaceView change size, to implement this function
	 */
	public boolean isFirstChange = true;
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		//
		gl.glRotatef(180, 0f, 0.2f, 0f);
		nativeChangeView(width, height);
	}
	
	/**
	 * 当画面第一次创建的时候，会调用此函数
	 * When SurfaceView when first created , to achieve this function
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		nativeInitRenderer();
	}
}
