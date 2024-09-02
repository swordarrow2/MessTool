package com.meng.messtool.modules.fpvtool.serial.msp.sensor;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:36
 */
public class SensorOpflow implements MspSeriallizeable<SensorOpflow> {
    private int quality_uint8; // [0;255]
    private int motionX_int32;
    private int motionY_int32;

    public int getQuality_uint8() {
        return quality_uint8;
    }

    public void setQuality_uint8(int quality_uint8) {
        this.quality_uint8 = quality_uint8 & 0xFF;
    }

    public int getMotionX_int32() {
        return motionX_int32;
    }

    public void setMotionX_int32(int motionX_int32) {
        this.motionX_int32 = motionX_int32;
    }

    public int getMotionY_int32() {
        return motionY_int32;
    }

    public void setMotionY_int32(int motionY_int32) {
        this.motionY_int32 = motionY_int32;
    }


    @Override
    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getQuality_uint8())
                .writeInt32(getMotionX_int32())
                .writeInt32(getMotionY_int32()).encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setQuality_uint8(reader.readUint8());
        setMotionX_int32(reader.readInt32());
        setMotionY_int32(reader.readInt32());
    }

    @Override
    public SensorOpflow deepCopy() {
        SensorOpflow opflow = new SensorOpflow();
        opflow.setQuality_uint8(getQuality_uint8());
        opflow.setMotionX_int32(getMotionX_int32());
        opflow.setMotionY_int32(getMotionY_int32());
        return opflow;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorOpflow{");
        sb.append("quality_uint8=").append(quality_uint8);
        sb.append(", motionX_int32=").append(motionX_int32);
        sb.append(", motionY_int32=").append(motionY_int32);
        sb.append('}');
        return sb.toString();
    }
//    uint8_t quality; // [0;255]
//    int32_t motionX;
//    int32_t motionY;
}
