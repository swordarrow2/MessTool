package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:45
 */
public class MSP2_INAV_ANALOG {
    // Bit 1: battery full, Bit 2: use capacity threshold, Bit 3-4: battery
    // state, Bit 5-8: battery cell count
    public int batteryFlags;
    public int getBatteryVoltage;
    public int getAmperage;
    public int getPower;
    public int getMAhDrawn;
    public int getMWhDrawn;
    public int getBatteryRemainingCapacity;
    public int calculateBatteryPercentage;
    public int getRSSI;

    public MSP2_INAV_ANALOG(int batteryFlags, int getBatteryVoltage, int getAmperage, int getPower, int getMAhDrawn, int getMWhDrawn, int getBatteryRemainingCapacity, int calculateBatteryPercentage, int getRSSI) {
        this.batteryFlags = batteryFlags;
        this.getBatteryVoltage = getBatteryVoltage;
        this.getAmperage = getAmperage;
        this.getPower = getPower;
        this.getMAhDrawn = getMAhDrawn;
        this.getMWhDrawn = getMWhDrawn;
        this.getBatteryRemainingCapacity = getBatteryRemainingCapacity;
        this.calculateBatteryPercentage = calculateBatteryPercentage;
        this.getRSSI = getRSSI;
    }
}
