package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:37
 */
public class MSP_ANALOG {

    public int getBatteryVoltage;
    public int getMAhDrawn;
    public int getRSSI;
    public int getAmperage;

    public MSP_ANALOG(int getBatteryVoltage, int getMAhDrawn, int getRSSI, int getAmperage) {
        this.getBatteryVoltage = getBatteryVoltage;
        this.getMAhDrawn = getMAhDrawn;
        this.getRSSI = getRSSI;
        this.getAmperage = getAmperage;
    }
}
