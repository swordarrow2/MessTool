package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:12
 */
public class MSP_MISC {

    public int PWM_RANGE_MIDDLE;
    public int zero;
    public int motorConfigMaxthrottle;
    public int motorConfigMincommand;
    public int currentBatteryProfileFailsafe_throttle;
    public int gpsConfigProvider;
    public int zero1;
    public int gpsConfigSbasMode;
    public int zero2;
    public int rxConfigRssi_channel;
    public int zero3;
    public int compassConfigMag_declination;
    public int batteryMetersConfigVoltageScale;
    public int currentBatteryProfileCellMin;
    public int currentBatteryProfileCellMax;
    public int currentBatteryProfileCellWarning;

    public MSP_MISC(int PWM_RANGE_MIDDLE, int zero, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int zero1, int gpsConfigSbasMode, int zero2, int rxConfigRssi_channel, int zero3, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning) {
        this.PWM_RANGE_MIDDLE = PWM_RANGE_MIDDLE;
        this.zero = zero;
        this.motorConfigMaxthrottle = motorConfigMaxthrottle;
        this.motorConfigMincommand = motorConfigMincommand;
        this.currentBatteryProfileFailsafe_throttle = currentBatteryProfileFailsafe_throttle;
        this.gpsConfigProvider = gpsConfigProvider;
        this.zero1 = zero1;
        this.gpsConfigSbasMode = gpsConfigSbasMode;
        this.zero2 = zero2;
        this.rxConfigRssi_channel = rxConfigRssi_channel;
        this.zero3 = zero3;
        this.compassConfigMag_declination = compassConfigMag_declination;
        this.batteryMetersConfigVoltageScale = batteryMetersConfigVoltageScale;
        this.currentBatteryProfileCellMin = currentBatteryProfileCellMin;
        this.currentBatteryProfileCellMax = currentBatteryProfileCellMax;
        this.currentBatteryProfileCellWarning = currentBatteryProfileCellWarning;
    }
}
