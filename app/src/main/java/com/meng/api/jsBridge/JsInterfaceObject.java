package com.meng.api.jsBridge;
import com.meng.messtool.*;
import android.webkit.*;

public class JsInterfaceObject {
    
    /*
	*@author 清梦
	*@date 2024-08-28 19:28:37
    */
public static final String TAG = "JsInterfaceObject";
    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

@JavascriptInterface
    public void openLeftDrawer(){
        mainActivity.openLeftDrawer();
    }
    
}
