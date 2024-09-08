package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:12
 */
public class MSP_MISC implements IDecodeable {

    private static final String TAG = "MSP_MISC";

    public int pwmRangeMiddle;
    public int min_throttle;
    public int motorConfigMaxthrottle;
    public int motorConfigMincommand;
    public int currentBatteryProfileFailsafe_throttle;
    public int gpsConfigProvider;
    public int gps_baudrate;
    public int gpsConfigSbasMode;
    public int multiwiiCurrentMeterOutput;
    public int rxConfigRssi_channel;
    public int zero;
    public int compassConfigMag_declination;
    public int batteryMetersConfigVoltageScale;
    public int currentBatteryProfileCellMin;
    public int currentBatteryProfileCellMax;
    public int currentBatteryProfileCellWarning;

    public MSP_MISC(int pwmRangeMiddle, int min_throttle, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int gps_baudrate, int gpsConfigSbasMode, int multiwiiCurrentMeterOutput, int rxConfigRssi_channel, int zero, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning) {
        Debuger.checkDebugMode();
        this.pwmRangeMiddle = pwmRangeMiddle;
        this.min_throttle = min_throttle;
        this.motorConfigMaxthrottle = motorConfigMaxthrottle;
        this.motorConfigMincommand = motorConfigMincommand;
        this.currentBatteryProfileFailsafe_throttle = currentBatteryProfileFailsafe_throttle;
        this.gpsConfigProvider = gpsConfigProvider;
        this.gps_baudrate = gps_baudrate;
        this.gpsConfigSbasMode = gpsConfigSbasMode;
        this.multiwiiCurrentMeterOutput = multiwiiCurrentMeterOutput;
        this.rxConfigRssi_channel = rxConfigRssi_channel;
        this.zero = zero;
        this.compassConfigMag_declination = compassConfigMag_declination;
        this.batteryMetersConfigVoltageScale = batteryMetersConfigVoltageScale;
        this.currentBatteryProfileCellMin = currentBatteryProfileCellMin;
        this.currentBatteryProfileCellMax = currentBatteryProfileCellMax;
        this.currentBatteryProfileCellWarning = currentBatteryProfileCellWarning;
    }

    public MSP_MISC(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        pwmRangeMiddle = reader.readUint16();
        min_throttle = reader.readUint16();
        motorConfigMaxthrottle = reader.readUint16();
        motorConfigMincommand = reader.readUint16();
        currentBatteryProfileFailsafe_throttle = reader.readUint16();
        gpsConfigProvider = reader.readUint8();
        gps_baudrate = reader.readUint8();
        gpsConfigSbasMode = reader.readUint8();
        multiwiiCurrentMeterOutput = reader.readUint8();
        rxConfigRssi_channel = reader.readUint8();
        zero = reader.readUint8();
        compassConfigMag_declination = reader.readUint16();
        batteryMetersConfigVoltageScale = reader.readUint8();
        currentBatteryProfileCellMin = reader.readUint8();
        currentBatteryProfileCellMax = reader.readUint8();
        currentBatteryProfileCellWarning = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_MISC{" + "pwmRangeMiddle=" + pwmRangeMiddle +
                ", min_throttle=" + min_throttle +
                ", motorConfigMaxthrottle=" + motorConfigMaxthrottle +
                ", motorConfigMincommand=" + motorConfigMincommand +
                ", currentBatteryProfileFailsafe_throttle=" + currentBatteryProfileFailsafe_throttle +
                ", gpsConfigProvider=" + gpsConfigProvider +
                ", gps_baudrate=" + gps_baudrate +
                ", gpsConfigSbasMode=" + gpsConfigSbasMode +
                ", multiwiiCurrentMeterOutput=" + multiwiiCurrentMeterOutput +
                ", rxConfigRssi_channel=" + rxConfigRssi_channel +
                ", zero=" + zero +
                ", compassConfigMag_declination=" + compassConfigMag_declination +
                ", batteryMetersConfigVoltageScale=" + batteryMetersConfigVoltageScale +
                ", currentBatteryProfileCellMin=" + currentBatteryProfileCellMin +
                ", currentBatteryProfileCellMax=" + currentBatteryProfileCellMax +
                ", currentBatteryProfileCellWarning=" + currentBatteryProfileCellWarning +
                '}';
    }
}
