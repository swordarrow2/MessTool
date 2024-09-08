package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:28
 */
public class MSP2_INAV_MISC implements IDecodeable {

    private static final String TAG = "MSP2_INAV_MISC";

    public int pwmRangeMiddle;
    public int motorConfigMinthrottle;
    public int motorConfigMaxthrottle;
    public int motorConfigMincommand;
    public int currentBatteryProfileFailsafe_throttle;
    public int gpsConfigProvider;
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

    public MSP2_INAV_MISC(int pwmRangeMiddle, int motorConfigMinthrottle, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int gps_baudrate, int gpsConfigSbasMode, int rxConfigRssi_channel, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int batteryMetersConfigVoltageSource, int currentBatteryProfileCells, int currentBatteryProfileCellDetect, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning, int currentBatteryProfileCapacityValue, int currentBatteryProfileCapacityWarning, int currentBatteryProfileCapacityCritical, int currentBatteryProfileCapacityUnit) {
        Debuger.checkDebugMode();
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

    public MSP2_INAV_MISC(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        pwmRangeMiddle = reader.readUint16();
        motorConfigMinthrottle = reader.readUint16();
        motorConfigMaxthrottle = reader.readUint16();
        motorConfigMincommand = reader.readUint16();
        currentBatteryProfileFailsafe_throttle = reader.readUint16();
        gpsConfigProvider = reader.readUint8();
        gps_baudrate = reader.readUint8();
        gpsConfigSbasMode = reader.readUint8();
        rxConfigRssi_channel = reader.readUint8();
        compassConfigMag_declination = reader.readUint16();
        batteryMetersConfigVoltageScale = reader.readUint16();
        batteryMetersConfigVoltageSource = reader.readUint8();
        currentBatteryProfileCells = reader.readUint8();
        currentBatteryProfileCellDetect = reader.readUint16();
        currentBatteryProfileCellMin = reader.readUint16();
        currentBatteryProfileCellMax = reader.readUint16();
        currentBatteryProfileCellWarning = reader.readUint16();
        currentBatteryProfileCapacityValue = reader.readInt32();
        currentBatteryProfileCapacityWarning = reader.readInt32();
        currentBatteryProfileCapacityCritical = reader.readInt32();
        currentBatteryProfileCapacityUnit = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_MISC{" + "pwmRangeMiddle=" + pwmRangeMiddle +
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
