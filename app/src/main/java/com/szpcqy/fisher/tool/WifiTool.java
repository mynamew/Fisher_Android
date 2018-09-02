package com.szpcqy.fisher.tool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class WifiTool {

    public interface OnWifiCallbak {
        void onSuccess(String ssid);

        void onFailure(String ssid);
    }

    private String m_ssid;
    private String m_sspw;
    private int m_timeoutms = 10000;
    private OnWifiCallbak m_callback;
    private Activity m_activity;
    private WifiStatusReceiver m_receiver;
    private Clock m_clock;

    static public WifiTool from(Activity activity) {
        return new WifiTool(activity);
    }

    public WifiTool(Activity ctx) {
        m_activity = ctx;
    }

    public WifiTool connect(String ssid, String sspw, int timeoutms, OnWifiCallbak callback) {
        m_ssid = ssid;
        m_sspw = sspw;
        m_timeoutms = timeoutms;
        m_callback = callback;
        doconnect();
        return this;
    }

    public WifiTool connect(String ssid, String sspw, OnWifiCallbak callback) {
        m_ssid = ssid;
        m_sspw = sspw;
        m_callback = callback;
        doconnect();
        return this;
    }

    private void doconnect() {
        WifiManager wifiManager = (WifiManager) m_activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        addMonitor(wifiManager);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
//        /**
//         * 判断是否连接了正确的wifi
//         * 判断是否能够连接网络
//         */
//        if(judgeWifiIsConnectRight()){
//            removeMonitor();
//            m_callback.onSuccess(m_ssid);
//            return;
//        }
        /**
         * 连接wifi
         */
        WifiConfiguration conn = getSavedWifiConfiguration(wifiManager, composeSSID());
        if (conn == null) {
            conn = new WifiConfiguration();
        }
        conn.allowedAuthAlgorithms.clear();
        conn.allowedGroupCiphers.clear();
        conn.allowedKeyManagement.clear();
        conn.allowedPairwiseCiphers.clear();
        conn.allowedProtocols.clear();
        conn.SSID = composeSSID();
        conn.preSharedKey = composeSSPW();
        conn.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        conn.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        conn.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        conn.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        conn.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        conn.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        conn.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        conn.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        conn.status = WifiConfiguration.Status.ENABLED;

        int wifiId = wifiManager.addNetwork(conn);
        if (wifiId != -1) {
            wifiManager.enableNetwork(wifiId, true);
        }
    }

    /**
     * 判断wifi连接是否正确
     *
     * @return
     */
    private boolean judgeWifiIsConnectRight() {
        /**
         * 判断当前wifi名是否是想要连接的wifi
         */
        WifiManager wifiManager = (WifiManager) m_activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String currentSsid = wifiInfo.getSSID();
        ConnectivityManager connec = (ConnectivityManager) m_activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
            if (currentSsid.contains(m_ssid)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private WifiConfiguration getSavedWifiConfiguration(WifiManager wifiManager, String ssid) {
        List<WifiConfiguration> savedList = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration conn : savedList) {
            if (conn.SSID.equals(ssid)) {
                return conn;
            }
        }
        return null;
    }

    private String composeSSID() {
        return "\"" + m_ssid + "\"";
    }

    private String composeSSPW() {
        return "\"" + m_sspw + "\"";
    }

    private void addMonitor(WifiManager wifiManager) {
        IntentFilter it = new IntentFilter();
        it.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        it.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        it.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        it.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        it.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        m_receiver = new WifiStatusReceiver();
        m_activity.getApplicationContext().registerReceiver(m_receiver, it);

        m_clock = Clock.create(m_activity).delay(0).interval(m_timeoutms).count(1).onCompleteOnUI(new Runnable() {
            @Override
            public void run() {
                removeMonitor();
                if (m_callback != null) {
                    m_callback.onFailure(m_ssid);
                }
            }
        }).start();
    }

    private void removeMonitor() {
        m_activity.getApplicationContext().unregisterReceiver(m_receiver);
        m_clock.stop();
    }

    private class WifiStatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean equals = WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction());
            if (equals) {
                Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (parcelableExtra != null) {
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    NetworkInfo.State state = networkInfo.getState();
                    String SSID = networkInfo.getExtraInfo();
                    String type = networkInfo.getTypeName();
                    boolean isConnected = state == NetworkInfo.State.CONNECTED;
                    if (type.equals("WIFI")) {
                        if (isConnected) {
                            if (composeSSID().equals(SSID)) {
                                removeMonitor();
                                if (m_callback != null) {
                                    m_callback.onSuccess(m_ssid);
                                }
                            }
                        } else {
                            //因为会被调用很多次，所以要一直等待
                        }
                    }else {

                    }
                }
            }

        }
    }


    //通过反射的方式去判断wifi是否已经连接上，并且可以开始传输数据
    private boolean checkWiFiConnectSuccess() {
        Class classType = WifiInfo.class;
        try {
            Object invo = classType.newInstance();
            Object result = invo.getClass().getMethod("getMeteredHint").invoke(invo);
            return (boolean) result;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }


}
