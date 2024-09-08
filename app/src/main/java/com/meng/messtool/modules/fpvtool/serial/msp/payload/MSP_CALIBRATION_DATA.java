package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:05
 */
public class MSP_CALIBRATION_DATA implements IDecodeable {

    private static final String TAG = "MSP_CALIBRATION_DATA";

    public int accGetCalibrationAxisFlags;
    public int accZeroX;
    public int accZeroY;
    public int accZeroZ;
    public int accGainX;
    public int accGainY;
    public int accGainZ;
    public int magZeroX;
    public int magZeroY;
    public int magZeroZ;
    public int opflow_scale;
    public int magGainX;
    public int magGainY;
    public int magGainZ;

    public MSP_CALIBRATION_DATA(int accGetCalibrationAxisFlags, int accZeroX, int accZeroY, int accZeroZ, int accGainX, int accGainY, int accGainZ, int magZeroX, int magZeroY, int magZeroZ, int opflow_scale, int magGainX, int magGainY, int magGainZ) {
        Debuger.checkDebugMode();
        this.accGetCalibrationAxisFlags = accGetCalibrationAxisFlags;
        this.accZeroX = accZeroX;
        this.accZeroY = accZeroY;
        this.accZeroZ = accZeroZ;
        this.accGainX = accGainX;
        this.accGainY = accGainY;
        this.accGainZ = accGainZ;
        this.magZeroX = magZeroX;
        this.magZeroY = magZeroY;
        this.magZeroZ = magZeroZ;
        this.opflow_scale = opflow_scale;
        this.magGainX = magGainX;
        this.magGainY = magGainY;
        this.magGainZ = magGainZ;
    }

    public MSP_CALIBRATION_DATA(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        accGetCalibrationAxisFlags = reader.readUint8();
        accZeroX = reader.readInt16();
        accZeroY = reader.readInt16();
        accZeroZ = reader.readInt16();
        accGainX = reader.readInt16();
        accGainY = reader.readInt16();
        accGainZ = reader.readInt16();
        magZeroX = reader.readInt16();
        magZeroY = reader.readInt16();
        magZeroZ = reader.readInt16();
        opflow_scale = reader.readInt16();
        magGainX = reader.readInt16();
        magGainY = reader.readInt16();
        magGainZ = reader.readInt16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_CALIBRATION_DATA{" + "accGetCalibrationAxisFlags=" + accGetCalibrationAxisFlags +
                ", accZeroX=" + accZeroX +
                ", accZeroY=" + accZeroY +
                ", accZeroZ=" + accZeroZ +
                ", accGainX=" + accGainX +
                ", accGainY=" + accGainY +
                ", accGainZ=" + accGainZ +
                ", magZeroX=" + magZeroX +
                ", magZeroY=" + magZeroY +
                ", magZeroZ=" + magZeroZ +
                ", opflow_scale=" + opflow_scale +
                ", magGainX=" + magGainX +
                ", magGainY=" + magGainY +
                ", magGainZ=" + magGainZ +
                '}';
    }
}
