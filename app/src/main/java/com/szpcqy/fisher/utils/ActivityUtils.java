package com.szpcqy.fisher.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * $dsc
 * author: timi
 * create at: 2018-09-01 19:49
 */
public class ActivityUtils {

    /**
     * 判断某一个类是否存在任务栈里面
     *
     * @return
     */
    public static boolean isExsitActivity(Class<?> cls, Context context) {
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        // 说明系统中存在这个activity
        if (cmpName != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                // 说明它已经启动了
                if (taskInfo.baseActivity.equals(cmpName)) {
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }

}
