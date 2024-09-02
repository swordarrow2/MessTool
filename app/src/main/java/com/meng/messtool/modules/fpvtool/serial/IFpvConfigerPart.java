package com.meng.messtool.modules.fpvtool.serial;

import android.view.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial
 *@author  清梦
 *@date    2024/9/2 23:24
 */
public interface IFpvConfigerPart {
    String getName();

    void initView(View view);

    int getMainView();

    void processRecieved(byte[] data);
}
