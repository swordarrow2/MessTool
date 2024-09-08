package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:37
 */
public class MSP2_INAV_BATTERY_CONFIG implements IDecodeable {

    private static final String TAG = "MSP2_INAV_BATTERY_CONFIG";

    public int batteryMetersConfigVoltageScale;
    public int batteryMetersConfigVoltageSource;
    public int currentBatteryProfileCells;
    public int currentBatteryProfileCellDetect;
    public int currentBatteryProfileCellMin;
    public int currentBatteryProfileCellMax;
    public int currentBatteryProfileCellWarning;
    public int batteryMetersConfigCurrentOffset;
    public int batteryMetersConfigCurrentScale;
    public long currentBatteryProfileCapacityValue;
    public int currentBatteryProfileCapacityWarning;
    public int currentBatteryProfileCapacityCritical;
    public int currentBatteryProfileCapacityUnit;

    public MSP2_INAV_BATTERY_CONFIG(int batteryMetersConfigVoltageScale, int batteryMetersConfigVoltageSource, int currentBatteryProfileCells, int currentBatteryProfileCellDetect, int currentBatteryProfileCellMin, int currentBatteryProfileCellMax, int currentBatteryProfileCellWarning, int batteryMetersConfigCurrentOffset, int batteryMetersConfigCurrentScale, int currentBatteryProfileCapacityValue, int currentBatteryProfileCapacityWarning, int currentBatteryProfileCapacityCritical, int currentBatteryProfileCapacityUnit) {
        Debuger.checkDebugMode();
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

    public MSP2_INAV_BATTERY_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        batteryMetersConfigVoltageScale = reader.readUint16();
        batteryMetersConfigVoltageSource = reader.readUint8();
        currentBatteryProfileCells = reader.readUint8();
        currentBatteryProfileCellDetect = reader.readUint16();
        currentBatteryProfileCellMin = reader.readUint16();
        currentBatteryProfileCellMax = reader.readUint16();
        currentBatteryProfileCellWarning = reader.readUint16();
        batteryMetersConfigCurrentOffset = reader.readUint16();
        batteryMetersConfigCurrentScale = reader.readUint16();
        currentBatteryProfileCapacityValue = reader.readUint32();
        currentBatteryProfileCapacityWarning = reader.readUint16();
        currentBatteryProfileCapacityCritical = reader.readUint16();
        currentBatteryProfileCapacityUnit = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_BATTERY_CONFIG{" + "batteryMetersConfigVoltageScale=" + batteryMetersConfigVoltageScale +
                ", batteryMetersConfigVoltageSource=" + batteryMetersConfigVoltageSource +
                ", currentBatteryProfileCells=" + currentBatteryProfileCells +
                ", currentBatteryProfileCellDetect=" + currentBatteryProfileCellDetect +
                ", currentBatteryProfileCellMin=" + currentBatteryProfileCellMin +
                ", currentBatteryProfileCellMax=" + currentBatteryProfileCellMax +
                ", currentBatteryProfileCellWarning=" + currentBatteryProfileCellWarning +
                ", batteryMetersConfigCurrentOffset=" + batteryMetersConfigCurrentOffset +
                ", batteryMetersConfigCurrentScale=" + batteryMetersConfigCurrentScale +
                ", currentBatteryProfileCapacityValue=" + currentBatteryProfileCapacityValue +
                ", currentBatteryProfileCapacityWarning=" + currentBatteryProfileCapacityWarning +
                ", currentBatteryProfileCapacityCritical=" + currentBatteryProfileCapacityCritical +
                ", currentBatteryProfileCapacityUnit=" + currentBatteryProfileCapacityUnit +
                '}';
    }
}
