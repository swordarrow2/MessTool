package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:02
 */
public class MSP_FEATURE {

    public int featureMask;

    public MSP_FEATURE(int featureMask) {
        this.featureMask = featureMask;
    }
}
