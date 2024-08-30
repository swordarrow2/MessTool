package com.meng.api.javascript;

import android.webkit.*;

import com.meng.messtool.*;

public class JsApi {

    /*
     *@author 清梦
     *@date 2024-08-28 19:28:37
     */
    public static final String TAG = "JsApi";
    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    @JavascriptInterface
    public void openLeftDrawer() {
        mainActivity.openLeftDrawer();
    }

}
