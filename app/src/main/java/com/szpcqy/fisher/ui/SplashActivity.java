package com.szpcqy.fisher.ui;

import android.content.Intent;

import com.szpcqy.fisher.R;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.mt.MTActivity;
import com.szpcqy.fisher.service.SocketServce;
import com.szpcqy.fisher.service.WifiService;
import com.szpcqy.fisher.tool.Clock;
import com.szpcqy.fisher.ui.login.LoginActivity;

/**
  *
  * @author   jzk
  * create at: 2018/8/25 14:00
  */  
public class SplashActivity extends MTActivity {


    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent wifi = new Intent(this, WifiService.class);
        startService(wifi);

        Intent socket = new Intent(this, SocketServce.class);
        startService(socket);

        Clock.create(this).count(1).interval(1000).onCompleteOnUI(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(getContext(), LoginActivity.class);
                startActivity(it);
                finish();
            }
        }).start();
    }

    @Override
    public int[] listenProtocols() {
        return new int[0];
    }

    @Override
    public void execResponse(SocketResonse res) {

    }
}
