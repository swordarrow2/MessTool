package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:24
 */
public class MSP_RSSI_CONFIG {

    public int rssi_channel;

    public MSP_RSSI_CONFIG(int rssi_channel) {
        this.rssi_channel = rssi_channel;
    }
}
