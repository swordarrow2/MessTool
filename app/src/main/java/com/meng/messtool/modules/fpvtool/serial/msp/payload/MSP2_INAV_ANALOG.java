package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:45
 */
public class MSP2_INAV_ANALOG implements IDecodeable {

    private static final String TAG = "MSP2_INAV_ANALOG";

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
        Debuger.checkDebugMode();
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

    public MSP2_INAV_ANALOG(byte[] data) {
        decode(data);
    }

    public boolean isBatteryWasFullWhenPluggedIn() {
        return (batteryFlags & 0b1) == 1;
    }

    public boolean isBatteryUsesCapacityThresholds() {
        return ((batteryFlags >>> 1) & 0b1) == 1;
    }

    public int getBatteryState() {
        return ((batteryFlags >>> 2) & 0b11);
    }

    public int getBatteryCellCount() {
        return ((batteryFlags >>> 4) & 0b1111);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        batteryFlags = reader.readUint8();
        getBatteryVoltage = reader.readUint16();
        getAmperage = reader.readUint16();
        getPower = reader.readInt32();
        getMAhDrawn = reader.readInt32();
        getMWhDrawn = reader.readInt32();
        getBatteryRemainingCapacity = reader.readInt32();
        calculateBatteryPercentage = reader.readUint8();
        getRSSI = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_ANALOG{" + "batteryFlags=" + batteryFlags +
                ", getBatteryVoltage=" + getBatteryVoltage +
                ", getAmperage=" + getAmperage +
                ", getPower=" + getPower +
                ", getMAhDrawn=" + getMAhDrawn +
                ", getMWhDrawn=" + getMWhDrawn +
                ", getBatteryRemainingCapacity=" + getBatteryRemainingCapacity +
                ", calculateBatteryPercentage=" + calculateBatteryPercentage +
                ", getRSSI=" + getRSSI +
                '}';
    }
}
