package com.szpcqy.fisher.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;
import static android.content.Context.WIFI_SERVICE;

/**
 * $dsc
 * author: timi
 * create at: 2018-09-01 13:28
 */
public class WifiUtils {
    public static WifiConfiguration getWifiConfiguration(Context context)
    {
        WifiConfiguration mWifiConfig = null;
        try
        {
            WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
            Method method = wifiManager.getClass().getMethod("getWifiApConfiguration");
            method.setAccessible(true);
            mWifiConfig = (WifiConfiguration) method.invoke(wifiManager);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mWifiConfig;
    }


    /**
     * @param flag 0: ssid
     *             1: pwd
     */
    public static String getApSSIDAndPwd(Context context, int flag)
    {
        WifiConfiguration mWifiConfig = getWifiConfiguration(context);
        String ssid = null;
        String pwd = null;
        if (null != mWifiConfig)
        {
            Field[] fields = mWifiConfig.getClass().getDeclaredFields();
            if (null != fields)
            {
                for (Field field : fields)
                {
                    try
                    {
                        if (field.getName().equals("SSID"))
                        {
                            ssid = field.get(mWifiConfig).toString();
                            Log.e(TAG, "AP SSI = " + ssid);
                        }
                        else if (field.getName().equals("preSharedKey"))
                        {
                            pwd = field.get(mWifiConfig).toString();
                            Log.e(TAG, "AP pwd = " + pwd);
                        }


                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.e(TAG, "getApSSIDAndPwd()-->error:" + e);
                    }
                }
            }
        }


        if (null == ssid)
        {
            ssid = "Unknown";
        }


        if (null == pwd)
        {
            pwd = "Unknown";
        }


        if (0 == flag)
        {
            return ssid;
        }
        else
        {
            return pwd;
        }
    }
}
