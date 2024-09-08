package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP2_INAV_SET_PROGRAMMING_PID implements IEncodeable {

    public int index;
    public int enabled;
    public int setpointType;
    public int setpointValue;
    public int measurementType;
    public int measurementValue;
    public int gainsP;
    public int gainsI;
    public int gainsD;
    public int gainsFF;

    public MSP2_INAV_SET_PROGRAMMING_PID(int index, int enabled, int setpointType, int setpointValue, int measurementType, int measurementValue, int gainsP, int gainsI, int gainsD, int gainsFF) {
        this.index = index;
        this.enabled = enabled;
        this.setpointType = setpointType;
        this.setpointValue = setpointValue;
        this.measurementType = measurementType;
        this.measurementValue = measurementValue;
        this.gainsP = gainsP;
        this.gainsI = gainsI;
        this.gainsD = gainsD;
        this.gainsFF = gainsFF;
    }

    public MSP2_INAV_SET_PROGRAMMING_PID() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(enabled)
                .writeUint8(setpointType)
                .writeInt32(setpointValue)
                .writeUint8(measurementType)
                .writeInt32(measurementValue)
                .writeUint16(gainsP)
                .writeUint16(gainsI)
                .writeUint16(gainsD)
                .writeUint16(gainsFF);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_PROGRAMMING_PID{" + "index=" + index +
                ", enabled=" + enabled +
                ", setpointType=" + setpointType +
                ", setpointValue=" + setpointValue +
                ", measurementType=" + measurementType +
                ", measurementValue=" + measurementValue +
                ", gainsP=" + gainsP +
                ", gainsI=" + gainsI +
                ", gainsD=" + gainsD +
                ", gainsFF=" + gainsFF +
                '}';
    }
}
