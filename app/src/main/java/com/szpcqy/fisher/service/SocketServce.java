package com.szpcqy.fisher.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.szpcqy.fisher.event.pair.CommonRequest;
import com.szpcqy.fisher.event.pair.NetRequest;
import com.szpcqy.fisher.event.pair.NetResponse;
import com.szpcqy.fisher.event.pair.SocketRequest;
import com.szpcqy.fisher.event.pair.SocketResonse;
import com.szpcqy.fisher.event.pair.WifiRequest;
import com.szpcqy.fisher.mt.MTActivity;
import com.szpcqy.fisher.mt.MTApplication;
import com.szpcqy.fisher.mt.MTDialog;
import com.szpcqy.fisher.mt.MTLightbox;
import com.szpcqy.fisher.mt.MTWebsocket;
import com.szpcqy.fisher.net.Gateway;
import com.szpcqy.fisher.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;


public class SocketServce extends Service {

    private MTWebsocket m_socket;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWifiConnectRequest(final WifiRequest req) {
        //当请求wifi连接时，即切断socket
        if(m_socket!=null) {
            m_socket.close();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetRequest(final NetRequest req) {
        if (req.getType() == NetRequest.RequestType.DISCONNECT) {
            if (m_socket != null) {
                m_socket.close();
                m_socket = null;
            }
        } else if (req.getType() == NetRequest.RequestType.CONNECT) {
            String url = Gateway.getWSURL(Gateway.APP_NAME, req.getIp(), Gateway.APP_PORT);
            setupSocket(url, req.getIp());
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSocketRequest(final SocketRequest req) {
        if (m_socket != null) {
            m_socket.send(req.toString());
        }
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSocketRequest(final CommonRequest req) {
        if (m_socket != null) {
            String reqJson=req._tostring();
            m_socket.send(reqJson);
        }
    }

    private void setupSocket(final String url, final String ip) {
        final MTActivity atx = MTApplication.getInstance().getCurrentActivity();
        if(!MTApplication.getInstance().isValid(atx)) {
            return; //有可能已经退出了
        }

        final MTDialog.MTDialogContent dia = MTLightbox.show(atx, MTLightbox.IconType.PROGRESS, "连接设备中", false);

        m_socket = MTWebsocket.connect(url, new MTWebsocket.OnCallback() {
            @Override
            public void onOpen(WebSocketClient client) {
                Log.d("MT", "Socket Open " + url);
                dia.close();
                EventBus.getDefault().post(new NetResponse(ip, true, false));
            }

            @Override
            public void onClose(String reason, boolean isRemote) {
                Log.d("MT", "Socket Close ip - " + url + " remote - " + isRemote + " reason -" + reason);
                EventBus.getDefault().post(new NetResponse(ip, false, isRemote));
            }

            @Override
            public void onError(String reason) {
                Log.d("MT", "Socket onError ip-" + ip + " reason - " + reason);
                dia.close();
                EventBus.getDefault().post(new NetResponse(ip, false, false));

                if(atx==null) {
                    return;
                }

                atx.runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder bd = new AlertDialog.Builder(atx);
                                bd.setTitle("警告");
                                bd.setMessage("服务器:" + url + "无法连接，是否重新连接?");
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
                                        setupSocket(url, ip);
                                    }
                                });
                                bd.show();
                            }
                        }
                );
            }

            @Override
            public void onMessage(WebSocketClient client, String msg) {
                Log.d("MT", "onMessage-" + msg);
                try {
                    SocketResonse resp = new Gson().fromJson(msg, SocketResonse.class);
                    EventBus.getDefault().post(resp);
                }catch(Exception e){
                    Log.d("MT", e.getCause().toString());
                }
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
