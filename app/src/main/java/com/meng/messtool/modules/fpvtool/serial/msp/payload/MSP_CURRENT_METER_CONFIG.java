package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:04
 */
public class MSP_CURRENT_METER_CONFIG {

    public int scale;
    public int offset;
    public int type;
    public int capacity;

    public MSP_CURRENT_METER_CONFIG(int scale, int offset, int type, int capacity) {
        this.scale = scale;
        this.offset = offset;
        this.type = type;
        this.capacity = capacity;
    }
}
