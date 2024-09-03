package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:28
 */
public class MSP2_INAV_MISC {

    public int PWM_RANGE_MIDDLE;
    public int zero;
    public int motorConfigMaxthrottle;
    public int motorConfigMincommand;
    public int currentBatteryProfileFailsafe_throttle;
    public int gpsConfigProvider;
    public int zero1;
    public int gpsConfigSbasMode;
    public int rxConfigRssi_channel;
    public int compassConfigMag_declination;
    public int batteryMetersConfigVoltageScale;
    public int batteryMetersConfigVoltageSource;
    public int currentBatteryProfileCells;
    public int currentBatteryProfileCellDetect;
    public int currentBatteryProfileCellMin;
    public int currentBatteryProfileCellMax;
    public int currentBatteryProfileCellWarning;
    public int currentBatteryProfileCapacityValue;
    public int currentBatteryProfileCapacityWarning;
    public int currentBatteryProfileCapacityCritical;
    public int currentBatteryProfileCapacityUnit;

    public MSP2_INAV_MISC(int PWM_RANGE_MIDDLE, int zero, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int zero1, int gpsConfigSbasMode, int rxConfigRssi_channel, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int batteryMetersConfigVoltageSource, int currentBatteryProfileCells, int currentBatteryProfileCellDetect, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning, int currentBatteryProfileCapacityValue, int currentBatteryProfileCapacityWarning, int currentBatteryProfileCapacityCritical, int currentBatteryProfileCapacityUnit) {
        this.PWM_RANGE_MIDDLE = PWM_RANGE_MIDDLE;
        this.zero = zero;
        this.motorConfigMaxthrottle = motorConfigMaxthrottle;
        this.motorConfigMincommand = motorConfigMincommand;
        this.currentBatteryProfileFailsafe_throttle = currentBatteryProfileFailsafe_throttle;
        this.gpsConfigProvider = gpsConfigProvider;
        this.zero1 = zero1;
        this.gpsConfigSbasMode = gpsConfigSbasMode;
        this.rxConfigRssi_channel = rxConfigRssi_channel;
        this.compassConfigMag_declination = compassConfigMag_declination;
        this.batteryMetersConfigVoltageScale = batteryMetersConfigVoltageScale;
        this.batteryMetersConfigVoltageSource = batteryMetersConfigVoltageSource;
        this.currentBatteryProfileCells = currentBatteryProfileCells;
        this.currentBatteryProfileCellDetect = currentBatteryProfileCellDetect;
        this.currentBatteryProfileCellMin = currentBatteryProfileCellMin;
        this.currentBatteryProfileCellMax = currentBatteryProfileCellMax;
        this.currentBatteryProfileCellWarning = currentBatteryProfileCellWarning;
        this.currentBatteryProfileCapacityValue = currentBatteryProfileCapacityValue;
        this.currentBatteryProfileCapacityWarning = currentBatteryProfileCapacityWarning;
        this.currentBatteryProfileCapacityCritical = currentBatteryProfileCapacityCritical;
        this.currentBatteryProfileCapacityUnit = currentBatteryProfileCapacityUnit;
    }
}
