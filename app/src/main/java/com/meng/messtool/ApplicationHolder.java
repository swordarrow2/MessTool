package com.meng.messtool;

import android.app.*;

import com.meng.tools.*;
import com.meng.tools.Stack;
import com.meng.tools.app.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

/*
 *package  com.meng.messtool
 *@author  清梦
 *@date    2024/8/1 10:20
 */
public class ApplicationHolder extends Application {

    public final Stack<Object> objectStack = new Stack<>();

    public static void showToast(final String msgAbbr, final String msgOrigin) {
        BaseActivity activity = getActivity();
        if (activity != null) {
            activity.showToast(msgAbbr, msgOrigin);
        }
    }

    public static void showToast(final String msg) {
        BaseActivity activity = getActivity();
        if (activity != null) {
            activity.showToast(msg);
        }
    }

    public static BaseActivity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return (BaseActivity) activity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCatcher.getInstance().init(this);
        SharedPreferenceHelper.init(this, "main");
        FileTool.init(this);
    }
}
