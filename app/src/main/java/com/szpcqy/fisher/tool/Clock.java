package com.szpcqy.fisher.tool;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {

    private enum RunType {
        UI, IO
    }

    private Activity activity;
    private int delayms = 0;
    private int intervalms = 1;
    private int countnum = 0;
    private RunType countType;
    private RunType completeType;
    private Runnable comRunnable;
    private Runnable countRunnable;


    private Timer timer;
    private int curCount = -1;//因为刚开始就会产生，所以要-1；


    static public Clock create(Activity activity) {
        return new Clock(activity);
    }

    public Clock start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    curCount++;

                    // 刚启动的第一次所以跳过
                    if(curCount==0) return;

                    //count
                    if (countRunnable != null) {
                        if (countType == RunType.IO) {
                            new Thread(countRunnable).start();
                        } else if (countType == RunType.UI) {
                            if(activity!=null) activity.runOnUiThread(countRunnable);
                        }
                    }

                    //com
                    if (countnum > 0) {
                        if (curCount >= countnum) {
                            if (comRunnable != null) {
                                if (completeType == RunType.IO) {
                                    new Thread(comRunnable).start();
                                } else if (completeType == RunType.UI) {
                                    if(activity!=null) activity.runOnUiThread(comRunnable);
                                }
                            }
                            timer.cancel();
                        }
                    }

                }
            }, delayms, intervalms);
        }
        return this;
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }


    private Clock(Activity activity) {
        this.activity = activity;
    }

    public Clock delay(int ms) {
        if(ms<0) ms=0;
        this.delayms = ms;
        return this;
    }

    public Clock interval(int ms) {
        if (ms == 0) ms = 1;
        this.intervalms = ms;
        return this;
    }

    public Clock count(int ct) {
        if (ct <0) ct = 0;
        this.countnum = ct;
        return this;
    }

    public Clock onCountOnUI(Runnable runnable) {
        countType = RunType.UI;
        countRunnable = runnable;
        return this;
    }

    public Clock onCountOnIO(Runnable runnable) {
        countType = RunType.IO;
        countRunnable = runnable;
        return this;
    }

    public Clock onCompleteOnUI(Runnable runnable) {
        completeType = RunType.UI;
        comRunnable = runnable;
        return this;
    }

    public Clock onCompleteOnIO(Runnable runnable) {
        completeType = RunType.IO;
        comRunnable = runnable;
        return this;
    }

}
