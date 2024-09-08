package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 9:20
 */
public class MSP2_INAV_STATUS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_STATUS";

    public int cycleTime;
    public int i2cGetErrorCounter;
    public int packSensorStatus;
    public int averageSystemLoadPercent;
    public int getConfigProfile;
    public int armingFlags;
    public int mspBoxModeFlags;
    public int getConfigMixerProfile;

    public MSP2_INAV_STATUS(int cycleTime, int i2cGetErrorCounter, int packSensorStatus, int averageSystemLoadPercent, int getConfigProfile, int armingFlags, int mspBoxModeFlags, int getConfigMixerProfile) {
        Debuger.checkDebugMode();
        this.cycleTime = cycleTime;
        this.i2cGetErrorCounter = i2cGetErrorCounter;
        this.packSensorStatus = packSensorStatus;
        this.averageSystemLoadPercent = averageSystemLoadPercent;
        this.getConfigProfile = getConfigProfile;
        this.armingFlags = armingFlags;
        this.mspBoxModeFlags = mspBoxModeFlags;
        this.getConfigMixerProfile = getConfigMixerProfile;
    }

    public MSP2_INAV_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        cycleTime = reader.readUint16();
        i2cGetErrorCounter = reader.readUint16();
        packSensorStatus = reader.readUint16();
        averageSystemLoadPercent = reader.readUint16();
        getConfigProfile = reader.readUint8();
        armingFlags = reader.readInt32();
        mspBoxModeFlags = reader.readInt32();
        getConfigMixerProfile = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_STATUS{" + "cycleTime=" + cycleTime +
                ", i2cGetErrorCounter=" + i2cGetErrorCounter +
                ", packSensorStatus=" + packSensorStatus +
                ", averageSystemLoadPercent=" + averageSystemLoadPercent +
                ", getConfigProfile=" + getConfigProfile +
                ", armingFlags=" + armingFlags +
                ", mspBoxModeFlags=" + mspBoxModeFlags +
                ", getConfigMixerProfile=" + getConfigMixerProfile +
                '}';
    }
}
