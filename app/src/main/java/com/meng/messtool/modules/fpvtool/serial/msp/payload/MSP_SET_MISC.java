package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:24
 */
public class MSP_SET_MISC implements IEncodeable {

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

    public MSP_SET_MISC(int pwmRangeMiddle, int min_throttle, int motorConfigMaxthrottle, int motorConfigMincommand, int currentBatteryProfileFailsafe_throttle, int gpsConfigProvider, int gps_baudrate, int gpsConfigSbasMode, int multiwiiCurrentMeterOutput, int rxConfigRssi_channel, int zero, int compassConfigMag_declination, int batteryMetersConfigVoltageScale, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning) {
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

    public MSP_SET_MISC() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(pwmRangeMiddle)
                .writeUint16(min_throttle)
                .writeUint16(motorConfigMaxthrottle)
                .writeUint16(motorConfigMincommand)
                .writeUint16(currentBatteryProfileFailsafe_throttle)
                .writeUint8(gpsConfigProvider)
                .writeUint8(gps_baudrate)
                .writeUint8(gpsConfigSbasMode)
                .writeUint8(multiwiiCurrentMeterOutput)
                .writeUint8(rxConfigRssi_channel)
                .writeUint8(zero)
                .writeUint16(compassConfigMag_declination)
                .writeUint8(batteryMetersConfigVoltageScale)
                .writeUint8(currentBatteryProfileCellMin)
                .writeUint8(currentBatteryProfileCellMax)
                .writeUint8(currentBatteryProfileCellWarning);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_MISC{" + "pwmRangeMiddle=" + pwmRangeMiddle +
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
