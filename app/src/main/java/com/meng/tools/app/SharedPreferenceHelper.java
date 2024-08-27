package com.meng.tools.app;

import android.content.*;
import android.os.*;

import com.meng.messtool.*;

import java.lang.reflect.*;
import java.util.*;


public class SharedPreferenceHelper {
    private static final String TAG = "Preference";

    /********** base **********/
    public static boolean isFirstUse() {
        return sp.getBoolean("first_use", true);
    }

    public static void setFirstUse(boolean b) {
        putBoolean("first_use", b);
    }

    public static String getTheme() {
        return sp.getString("theme", "蓝");
    }

    public static void setTheme(String v) {
        putString("theme", v);
    }

    public static String getVersion() {
        return sp.getString("newVersion", "0.0.0");
    }

    public static void setVersion(String v) {
        putString("newVersion", v);
    }

    public static void setSaveDebugLog(boolean b) {
        putBoolean("debugLog", b);
    }

    public static void setDebugMode(boolean b) {
        putBoolean("debugMode", b);
    }

    public static boolean isDebugMode() {
        return sp.getBoolean("debugMode", false);
    }

    public static boolean isSaveDebugLog() {
        return sp.getBoolean("debugLog", false);
    }

    public static void setOpenDrawer(boolean b) {
        putBoolean("opendraw", b);
    }

    public static boolean isOpenDrawer() {
        return sp.getBoolean("opendraw", true);
    }

    public static boolean isExit0() {
        return sp.getBoolean("exitsettings", false);
    }

    public static void setExit0(boolean b) {
        putBoolean("exitsettings", b);
    }

    /********** menu **********/
    public static boolean isShowSJF() {
        return sp.getBoolean("showSJF", false);
    }

    public static void setShowSJF(boolean use) {
        putBoolean("showSJF", use);
    }

    public static void setShowGroupPicture(boolean b) {
        putBoolean("GROUP_PICTURE", b);
    }

    public static boolean isShowGroupPicture() {
        return sp.getBoolean("GROUP_PICTURE", true);
    }

    public static void setShowGroupVideo(boolean b) {
        putBoolean("GROUP_VIDEO", b);
    }

    public static boolean isShowGroupVideo() {
        return sp.getBoolean("GROUP_VIDEO", true);
    }

    public static void setShowGroupAudio(boolean b) {
        putBoolean("GROUP_AUDIO", b);
    }

    public static boolean isShowGroupAudio() {
        return sp.getBoolean("GROUP_AUDIO", true);
    }

    public static void setShowGroupElectronic(boolean b) {
        putBoolean("GROUP_ELECTRONIC", b);
    }

    public static boolean isShowGroupElectronic() {
        return sp.getBoolean("GROUP_ELECTRONIC", true);
    }

    public static void setShowGroupSystem(boolean b) {
        throw new UnsupportedOperationException();
    }

    public static boolean isShowGroupSystem() {
        return true;
    }

    public static void setShowGroup(String key, boolean value) {
        if ("GROUP_SYSTEM".equals(key)) {
            throw new UnsupportedOperationException();
        }
        putBoolean(key, value);
    }

    public static boolean isShowGroup(String key) {
        if ("GROUP_SYSTEM".equals(key)) {
            return true;
        }
        return sp.getBoolean(key, false);
    }

    public static void setShowGroupName(boolean b) {
        putBoolean("show_group_name", b);
    }

    public static boolean isShowGroupName() {
        return sp.getBoolean("show_group_name", true);
    }

    /********** pixiv **********/
    public static void setPixivCookie(String cookie) {
        putString("cookievalue", cookie);
    }

    public static String getPixivCookie() {
        return sp.getString("cookievalue", "");
    }

    public static void setThreads(String t) {
        putString("threads", t);
    }

    public static String getThreads() {
        return sp.getString("threads", "3");
    }

    public static void setDownloadBigGif(boolean b) {
        putBoolean("bigpicturegif", b);
    }

    public static boolean isDownloadBigGif() {
        return sp.getBoolean("bigpicturegif", false);
    }

    public static void setDownloadBigPic(boolean b) {
        putBoolean("bigpicture", b);
    }

    public static boolean isDownloadBigPic() {
        return sp.getBoolean("bigpicture", false);
    }

    public static void setDeleteZipAfterGenenalGif(boolean b) {
        putBoolean("deleteZipAfterMakeGif", b);
    }

    public static boolean isDeleteZipAfterGenenalGif() {
        return sp.getBoolean("deleteZipAfterMakeGif", false);
    }

    /********** rtmp **********/
    public static String getRtmpAddr() {
        return sp.getString("rtmpAddr", "0.0.0");
    }

    public static void setRtmpAddr(String v) {
        putString("rtmpAddr", v);
    }

    public static String getRtmpCode() {
        return sp.getString("rtmpCode", "0.0.0");
    }

    public static void setRtmpCode(String v) {
        putString("rtmpCode", v);
    }

    /********** database operate **********/

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static void init(Context context, String preferenceName) {
        if (sp == null) {
//            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
//            ProxySharedPreference proxy = new ProxySharedPreference(sharedPreferences);
//            sp = (SharedPreferences) Proxy.newProxyInstance(sharedPreferences.getClass().getClassLoader(), new Class[]{SharedPreferences.class}, proxy);
            sp = context.getSharedPreferences(preferenceName, 0);
//            Debuger.setDebugMode(isDebugMode());
//            Debuger.addLog(TAG, "init");
//        } else {
//            Debuger.addLog(TAG, "init again");
        }
    }

    private static void putBoolean(String key, Boolean value) {
        editor = sp.edit();
        editor.putBoolean(key, value);
        Debuger.addLog(TAG, key, value);
        editor.apply();
    }

    private static void putLong(String key, long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        Debuger.addLog(TAG, key, value);
        editor.apply();
    }

    private static void putString(String key, String value) {
        editor = sp.edit();
        editor.putString(key, value);
        Debuger.addLog(TAG, key, value);
        editor.apply();
    }

//    private static class ProxySharedPreference implements InvocationHandler {
//        private SharedPreferences preferences;
//
//        public ProxySharedPreference(SharedPreferences preferences) {
//            this.preferences = preferences;
//        }
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            Object target = method.invoke(preferences, args);
//            Debuger.addLog(TAG, method.getName() + (args == null ? "()" : Arrays.asList(args).toString()) + "=" + target.toString());
//            return target;
//        }
//    }
}

