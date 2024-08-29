package com.meng.messtool.modules.chat.simulator;

import android.view.*;
import android.widget.*;

public class ChatRoomInfo {

    /*
     *@author 清梦
     *@date 2024-08-26 21:50:16
     */
    public static final String TAG = "ChatRoomInfo";
    public View titleView;


    public void setTitleView(TextView tvTitle) {
        this.titleView = tvTitle;
    }

    public View getTitleView() {
        return titleView;
    }
}
