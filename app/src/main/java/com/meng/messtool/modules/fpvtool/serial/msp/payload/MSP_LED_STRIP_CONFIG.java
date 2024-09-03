package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:33
 */
public class MSP_LED_STRIP_CONFIG {

    public int legacyLedConfig;

    public MSP_LED_STRIP_CONFIG(int legacyLedConfig) {
        this.legacyLedConfig = legacyLedConfig;
    }
}
