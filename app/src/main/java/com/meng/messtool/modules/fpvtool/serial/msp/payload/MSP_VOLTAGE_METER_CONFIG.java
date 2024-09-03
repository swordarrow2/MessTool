package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:03
 */
public class MSP_VOLTAGE_METER_CONFIG {

    public int scale;
    public int cellMin;
    public int cellMax;
    public int cellWarning;

    public MSP_VOLTAGE_METER_CONFIG(int scale, int cellMin, int cellMax, int cellWarning) {
        this.scale = scale;
        this.cellMin = cellMin;
        this.cellMax = cellMax;
        this.cellWarning = cellWarning;
    }
}
