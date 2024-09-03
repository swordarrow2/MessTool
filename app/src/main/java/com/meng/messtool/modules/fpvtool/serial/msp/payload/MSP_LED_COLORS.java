package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:31
 */
public class MSP_LED_COLORS {

    public int h;
    public int s;
    public int v;

    public MSP_LED_COLORS(int h, int s, int v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }
}
