package com.szpcqy.fisher.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.event.pair.WifiResponse;
import com.szpcqy.fisher.mt.MTActivity;
import com.szpcqy.fisher.mt.MTApplication;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.tool.WifiTool;
import com.szpcqy.fisher.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/** 
  * wifi 的service
  * @author   jzk
  * create at: 2018/8/25 14:09
  */  
public class WifiService extends Service {


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiConnectRequest(final WifiRequest req) {
        final MTActivity atx = MTApplication.getInstance().getCurrentActivity();
        if(!MTApplication.getInstance().isValid(atx)) {
            return; //有可能已经退出了
        }

        final MTDialog.MTDialogContent dia = MTLightbox.show(atx, MTLightbox.IconType.PROGRESS, "切换设备中", false);

        WifiTool.from(atx).connect(req.getSsid(), req.getSspw(), new WifiTool.OnWifiCallbak() {
            @Override
            public void onSuccess(String ssid) {
                dia.close();
                EventBus.getDefault().post(new WifiResponse(req.getSsid(), true));
            }

            @Override
            public void onFailure(String ssid) {
                dia.close();
                EventBus.getDefault().post(new WifiResponse(req.getSsid(), false));

                AlertDialog.Builder bd = new AlertDialog.Builder(atx);
                bd.setTitle("警告");
                bd.setMessage("Wifi:" + ssid + "无法连接，是否重新连接?");
                bd.setCancelable(false);
                bd.setNegativeButton("退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MTApplication.getInstance().onFinishAllActivities();
                        atx.startActivity(new Intent(atx, LoginActivity.class));
                    }
                });
                bd.setPositiveButton("重新连接", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onWifiConnectRequest(req);
                    }
                });
                bd.show();
            }
        });
    }


    //初始化/////////////////////////////////////////
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    ///////////////////////////////////////////////////


}
