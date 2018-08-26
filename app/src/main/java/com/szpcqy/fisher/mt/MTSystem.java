package com.szpcqy.fisher.mt;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

/**
 * Created by Master on 2017/12/21.
 */

public class MTSystem {

    public enum ReleaseStatus {
        UNKNOWN,
        DEVELOPING,
        DISTRIBUTION
    }

    static public int getDensityDPI(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    static public float getDensity(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.density;
    }

    static public float getScaledDensity(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.scaledDensity;
    }

    static public ReleaseStatus getAppStatus(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            if ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                return ReleaseStatus.DISTRIBUTION;
            } else {
                return ReleaseStatus.DEVELOPING;
            }
        } catch (Exception e) {
            return ReleaseStatus.UNKNOWN;
        }
    }

    static public boolean getNetwork(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            if (info.isAvailable()) return true;
            else return false;
        } else {
            return false;
        }
    }

}
