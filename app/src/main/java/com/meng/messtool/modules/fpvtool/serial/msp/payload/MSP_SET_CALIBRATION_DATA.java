package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:27
 */
public class MSP_SET_CALIBRATION_DATA implements IEncodeable {

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

    public MSP_SET_CALIBRATION_DATA(int accZeroX, int accZeroY, int accZeroZ, int accGainX, int accGainY, int accGainZ, int magZeroX, int magZeroY, int magZeroZ, int opflow_scale, int magGainX, int magGainY, int magGainZ) {
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

    public MSP_SET_CALIBRATION_DATA() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(accZeroX)
                .writeUint16(accZeroY)
                .writeUint16(accZeroZ)
                .writeUint16(accGainX)
                .writeUint16(accGainY)
                .writeUint16(accGainZ)
                .writeUint16(magZeroX)
                .writeUint16(magZeroY)
                .writeUint16(magZeroZ)
                .writeUint16(opflow_scale)
                .writeUint16(magGainX)
                .writeUint16(magGainY)
                .writeUint16(magGainZ);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_CALIBRATION_DATA{" + "accZeroX=" + accZeroX +
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
