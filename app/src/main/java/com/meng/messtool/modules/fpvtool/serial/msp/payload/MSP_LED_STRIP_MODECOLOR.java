package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:41
 */
public class MSP_LED_STRIP_MODECOLOR {

    public int LED_MODE_COUNT;
    public int j;
    public int color;

    public MSP_LED_STRIP_MODECOLOR(int LED_MODE_COUNT, int j, int color) {
        this.LED_MODE_COUNT = LED_MODE_COUNT;
        this.j = j;
        this.color = color;
    }
}
