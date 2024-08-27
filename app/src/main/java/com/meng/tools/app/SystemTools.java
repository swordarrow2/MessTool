package com.meng.tools.app;

import android.content.*;
import android.net.*;
import android.os.*;

import com.meng.messtool.*;

import java.text.*;
import java.util.*;

import static android.content.Context.*;

/**
 * Created by SJF on 2024/6/29.
 */

public class SystemTools {
    public static boolean isUsingWifi() {
        return ((ConnectivityManager) ApplicationHolder.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
    }

    public static String getTimeEndWithSeconed() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getTime(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp));
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getDate(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeStamp));
    }

    public static String formatDate(int y, int m, int d) {
        return getDate(new Date(y, m, d).getTime());
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;// 2012-10-03 23:41:31
    }

    public static String getTimeWithMinute() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
	} 
    
    public static void doVibrate(Context context, long time) {
        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(time);
        }
    }
}
