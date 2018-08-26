package com.szpcqy.fisher.mt;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;


/**
 * Created by Master on 2017/10/31.
 */

public class MTApplication extends MultiDexApplication {

    static private MTApplication _instance;

    private ArrayList<MTActivity> _activityList;

    /**
     * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;
        _activityList = new ArrayList<>();

        //初始化Log
        Logger.addLogAdapter(new AndroidLogAdapter());
        //读取配置


    }

    public void onCreateActivity(MTActivity atx) {
        _activityList.add(atx);
    }

    public void onFinishActivity(MTActivity atx) {
        if (_activityList.contains(atx)) {
            _activityList.remove(atx);
        }
    }

    public void onFinishAllActivities() {
        while(_activityList.size()>0){
            _activityList.remove(0).finish();;
        }
    }

    public boolean isValid(MTActivity atx){
        return _activityList.contains(atx);
    }


    static public MTApplication getInstance() {
        return _instance;
    }

    public MTActivity getCurrentActivity() {
        if (_activityList.size() > 0) {
            return _activityList.get(_activityList.size() - 1);
        } else {
            return null;
        }
    }



}
