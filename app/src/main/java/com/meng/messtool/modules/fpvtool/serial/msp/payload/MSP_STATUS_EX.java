package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 9:13
 */
public class MSP_STATUS_EX implements IDecodeable {

    private static final String TAG = "MSP_STATUS_EX";
    //    MSP_STATUS_EX
    public int cycleTime;
    public int i2cGetErrorCounter;
    public int packSensorStatus;
    public int mspBoxModeFlags;
    public int getConfigProfile;
    //    MSP_STATUS_EX append
    public int averageSystemLoadPercent;
    public int armingFlags;
    public int accGetCalibrationAxisFlags;

    public MSP_STATUS_EX(int cycleTime, int i2cGetErrorCounter, int packSensorStatus, int mspBoxModeFlags, int getConfigProfile) {
        Debuger.checkDebugMode();
        this.cycleTime = cycleTime;
        this.i2cGetErrorCounter = i2cGetErrorCounter;
        this.packSensorStatus = packSensorStatus;
        this.mspBoxModeFlags = mspBoxModeFlags;
        this.getConfigProfile = getConfigProfile;
    }

    public MSP_STATUS_EX(int cycleTime, int i2cGetErrorCounter, int packSensorStatus, int mspBoxModeFlags, int getConfigProfile, int averageSystemLoadPercent, int armingFlags, int accGetCalibrationAxisFlags) {
        Debuger.checkDebugMode();
        this.cycleTime = cycleTime;
        this.i2cGetErrorCounter = i2cGetErrorCounter;
        this.packSensorStatus = packSensorStatus;
        this.mspBoxModeFlags = mspBoxModeFlags;
        this.getConfigProfile = getConfigProfile;
        this.averageSystemLoadPercent = averageSystemLoadPercent;
        this.armingFlags = armingFlags;
        this.accGetCalibrationAxisFlags = accGetCalibrationAxisFlags;
    }

    public MSP_STATUS_EX(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        cycleTime = reader.readUint16();
        i2cGetErrorCounter = reader.readUint16();
        packSensorStatus = reader.readUint16();
        mspBoxModeFlags = reader.readInt32();
        getConfigProfile = reader.readUint8();
        if (data.length == 11) {
            reader.checkFinish();
        } else {
            averageSystemLoadPercent = reader.readUint16();
            armingFlags = reader.readUint16();
            accGetCalibrationAxisFlags = reader.readUint8();
            reader.checkFinish();
        }
    }

    @Override
    public String toString() {
        return "MSP_STATUS_EX{" + "cycleTime=" + cycleTime +
                ", i2cGetErrorCounter=" + i2cGetErrorCounter +
                ", packSensorStatus=" + packSensorStatus +
                ", mspBoxModeFlags=" + mspBoxModeFlags +
                ", getConfigProfile=" + getConfigProfile +
                ", averageSystemLoadPercent=" + averageSystemLoadPercent +
                ", armingFlags=" + armingFlags +
                ", accGetCalibrationAxisFlags=" + accGetCalibrationAxisFlags +
                '}';
    }
}
