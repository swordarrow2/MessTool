package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:01
 */
public class MSP_BATTERY_STATE implements IDecodeable {

    private static final String TAG = "MSP_BATTERY_STATE";

    // Battery characteristics
    public int getBatteryCellCount;
    public int capacityValue;
    public int getBatteryVoltageDivide10;// in 0.1V steps
    public int getMAhDrawn;
    public int getAmperage;
    // Battery alerts - used values match Betaflight's/DJI's
    public int getBatteryState;
    // Additional battery voltage field (in 0.01V steps)
    public int getBatteryVoltage;

    public MSP_BATTERY_STATE(int getBatteryCellCount, int capacityValue, int getBatteryVoltageDivide10, int getMAhDrawn, int getAmperage, int getBatteryState, int getBatteryVoltage) {
        Debuger.checkDebugMode();
        this.getBatteryCellCount = getBatteryCellCount;
        this.capacityValue = capacityValue;
        this.getBatteryVoltageDivide10 = getBatteryVoltageDivide10;
        this.getMAhDrawn = getMAhDrawn;
        this.getAmperage = getAmperage;
        this.getBatteryState = getBatteryState;
        this.getBatteryVoltage = getBatteryVoltage;
    }

    public MSP_BATTERY_STATE(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        getBatteryCellCount = reader.readUint8();
        capacityValue = reader.readUint16();
        getBatteryVoltageDivide10 = reader.readUint8();
        getMAhDrawn = reader.readUint16();
        getAmperage = reader.readUint16();
        getBatteryState = reader.readUint8();
        getBatteryVoltage = reader.readUint16();
    }

    @Override
    public String toString() {
        return "MSP_BATTERY_STATE{" + "getBatteryCellCount=" + getBatteryCellCount +
                ", capacityValue=" + capacityValue +
                ", getBatteryVoltageDivide10=" + getBatteryVoltageDivide10 +
                ", getMAhDrawn=" + getMAhDrawn +
                ", getAmperage=" + getAmperage +
                ", getBatteryState=" + getBatteryState +
                ", getBatteryVoltage=" + getBatteryVoltage +
                '}';
    }
}
