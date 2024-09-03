package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:37
 */
public class MSP2_INAV_BATTERY_CONFIG {
    public int batteryMetersConfigVoltageScale;
    public int batteryMetersConfigVoltageSource;
    public int currentBatteryProfileCells;
    public int currentBatteryProfileCellDetect;
    public int currentBatteryProfileCellMin;
    public int currentBatteryProfileCellMax;
    public int currentBatteryProfileCellWarning;
    public int batteryMetersConfigCurrentOffset;
    public int batteryMetersConfigCurrentScale;
    public int currentBatteryProfileCapacityValue;
    public int currentBatteryProfileCapacityWarning;
    public int currentBatteryProfileCapacityCritical;
    public int currentBatteryProfileCapacityUnit;

    public MSP2_INAV_BATTERY_CONFIG(int batteryMetersConfigVoltageScale, int batteryMetersConfigVoltageSource, int currentBatteryProfileCells, int currentBatteryProfileCellDetect, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning, int batteryMetersConfigCurrentOffset, int batteryMetersConfigCurrentScale, int currentBatteryProfileCapacityValue, int currentBatteryProfileCapacityWarning, int currentBatteryProfileCapacityCritical, int currentBatteryProfileCapacityUnit) {
        this.batteryMetersConfigVoltageScale = batteryMetersConfigVoltageScale;
        this.batteryMetersConfigVoltageSource = batteryMetersConfigVoltageSource;
        this.currentBatteryProfileCells = currentBatteryProfileCells;
        this.currentBatteryProfileCellDetect = currentBatteryProfileCellDetect;
        this.currentBatteryProfileCellMin = currentBatteryProfileCellMin;
        this.currentBatteryProfileCellMax = currentBatteryProfileCellMax;
        this.currentBatteryProfileCellWarning = currentBatteryProfileCellWarning;
        this.batteryMetersConfigCurrentOffset = batteryMetersConfigCurrentOffset;
        this.batteryMetersConfigCurrentScale = batteryMetersConfigCurrentScale;
        this.currentBatteryProfileCapacityValue = currentBatteryProfileCapacityValue;
        this.currentBatteryProfileCapacityWarning = currentBatteryProfileCapacityWarning;
        this.currentBatteryProfileCapacityCritical = currentBatteryProfileCapacityCritical;
        this.currentBatteryProfileCapacityUnit = currentBatteryProfileCapacityUnit;
    }
}
