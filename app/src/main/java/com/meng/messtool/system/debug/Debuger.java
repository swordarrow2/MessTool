package com.meng.messtool.system.debug;

import android.content.*;

import com.meng.tools.app.*;

/*
 *package  com.meng.messtool
 *@author  清梦
 *@date    2024/7/31 23:29
 */
public class Debuger {

    public static void init(Context context) {
        DebugDataBase.getInstance().init(context);
        DebugDataBase.getInstance().addOperate("debug logger init");
    }

    public static void addLog(String s) {
        if (SharedPreferenceHelper.isDebugMode()) {
            DebugDataBase.getInstance().addOperate(s);
        }
    }

    public static void addLog(String tag, String content) {
        addLog(tag + ":" + content);
    }

    public static void addLog(String tag, String key, String value) {
        addLog(tag + ":{ " + key + " = " + value + " }");
    }

    public static void addLog(String tag, Object key, Object value) {
        addLog(tag + ":{ " + key.toString() + " = " + value.toString() + " }");
    }
}
