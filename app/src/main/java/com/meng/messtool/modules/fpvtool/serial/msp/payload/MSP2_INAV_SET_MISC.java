package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:24
 */
public class MSP2_INAV_SET_MISC implements IEncodeable {

    //todo msp datapack

    @InavIgnore
    public int pwmRangeMiddle;
    @InavIgnore
    public int motorConfigMinthrottle;
    public int motorConfigMaxthrottle;
    public int motorConfigMincommand;
    public int currentBatteryProfileFailsafe_throttle;
    public int gpsConfigProvider;
    @InavIgnore
    public int gps_baudrate;
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

    public MSP2_INAV_SET_MISC(int pwmRangeMiddle, int motorConfigMinthrottle, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int gps_baudrate, int gpsConfigSbasMode, int rxConfigRssi_channel, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int batteryMetersConfigVoltageSource, int currentBatteryProfileCells, int currentBatteryProfileCellDetect, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning, int currentBatteryProfileCapacityValue, int currentBatteryProfileCapacityWarning, int currentBatteryProfileCapacityCritical, int currentBatteryProfileCapacityUnit) {
        this.pwmRangeMiddle = pwmRangeMiddle;
        this.motorConfigMinthrottle = motorConfigMinthrottle;
        this.motorConfigMaxthrottle = motorConfigMaxthrottle;
        this.motorConfigMincommand = motorConfigMincommand;
        this.currentBatteryProfileFailsafe_throttle = currentBatteryProfileFailsafe_throttle;
        this.gpsConfigProvider = gpsConfigProvider;
        this.gps_baudrate = gps_baudrate;
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

    public MSP2_INAV_SET_MISC() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(pwmRangeMiddle)
                .writeUint16(motorConfigMinthrottle)
                .writeUint16(motorConfigMaxthrottle)
                .writeUint16(motorConfigMincommand)
                .writeUint16(currentBatteryProfileFailsafe_throttle)
                .writeUint8(gpsConfigProvider)
                .writeUint8(gps_baudrate)
                .writeUint8(gpsConfigSbasMode)
                .writeUint8(rxConfigRssi_channel)
                .writeUint16(compassConfigMag_declination)
                .writeUint16(batteryMetersConfigVoltageScale)
                .writeUint8(batteryMetersConfigVoltageSource)
                .writeUint8(currentBatteryProfileCells)
                .writeUint16(currentBatteryProfileCellDetect)
                .writeUint16(currentBatteryProfileCellMin)
                .writeUint16(currentBatteryProfileCellMax)
                .writeUint16(currentBatteryProfileCellWarning)
                .writeInt32(currentBatteryProfileCapacityValue)
                .writeInt32(currentBatteryProfileCapacityWarning)
                .writeInt32(currentBatteryProfileCapacityCritical)
                .writeUint8(currentBatteryProfileCapacityUnit);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_MISC{" + "pwmRangeMiddle=" + pwmRangeMiddle +
                ", motorConfigMinthrottle=" + motorConfigMinthrottle +
                ", motorConfigMaxthrottle=" + motorConfigMaxthrottle +
                ", motorConfigMincommand=" + motorConfigMincommand +
                ", currentBatteryProfileFailsafe_throttle=" + currentBatteryProfileFailsafe_throttle +
                ", gpsConfigProvider=" + gpsConfigProvider +
                ", gps_baudrate=" + gps_baudrate +
                ", gpsConfigSbasMode=" + gpsConfigSbasMode +
                ", rxConfigRssi_channel=" + rxConfigRssi_channel +
                ", compassConfigMag_declination=" + compassConfigMag_declination +
                ", batteryMetersConfigVoltageScale=" + batteryMetersConfigVoltageScale +
                ", batteryMetersConfigVoltageSource=" + batteryMetersConfigVoltageSource +
                ", currentBatteryProfileCells=" + currentBatteryProfileCells +
                ", currentBatteryProfileCellDetect=" + currentBatteryProfileCellDetect +
                ", currentBatteryProfileCellMin=" + currentBatteryProfileCellMin +
                ", currentBatteryProfileCellMax=" + currentBatteryProfileCellMax +
                ", currentBatteryProfileCellWarning=" + currentBatteryProfileCellWarning +
                ", currentBatteryProfileCapacityValue=" + currentBatteryProfileCapacityValue +
                ", currentBatteryProfileCapacityWarning=" + currentBatteryProfileCapacityWarning +
                ", currentBatteryProfileCapacityCritical=" + currentBatteryProfileCapacityCritical +
                ", currentBatteryProfileCapacityUnit=" + currentBatteryProfileCapacityUnit +
                '}';
    }
}
