package com.meng.messtool.modules.fpvtool.gui.pages;

import android.view.*;
import android.widget.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial
 *@author  清梦
 *@date    2024/9/2 23:24
 */
public interface IFpvConfigPage {
    String getName();

    View getMainView(FrameLayout tabContent);

    void processRecieved(byte[] data);
}
