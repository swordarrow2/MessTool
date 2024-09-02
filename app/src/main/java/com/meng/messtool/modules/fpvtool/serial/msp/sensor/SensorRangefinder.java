package com.meng.messtool.modules.fpvtool.serial.msp.sensor;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:37
 */
public class SensorRangefinder implements MspSeriallizeable<SensorRangefinder> {
    private int quality_uint8;    // [0;255]
    private int distanceMm_int32; // Negative value for out of range

    public int getQuality_uint8() {
        return quality_uint8;
    }

    public void setQuality_uint8(int quality_uint8) {
        this.quality_uint8 = quality_uint8 & 0xFF;
    }

    public int getDistanceMm_int32() {
        return distanceMm_int32;
    }

    public void setDistanceMm_int32(int distanceMm_int32) {
        this.distanceMm_int32 = distanceMm_int32;
    }

    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getQuality_uint8())
                .writeInt32(getDistanceMm_int32()).encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setQuality_uint8(reader.readUint8());
        setDistanceMm_int32(reader.readInt32());
    }

    @Override
    public SensorRangefinder deepCopy() {
        SensorRangefinder rangefinder = new SensorRangefinder();
        rangefinder.setQuality_uint8(getQuality_uint8());
        rangefinder.setDistanceMm_int32(getDistanceMm_int32());
        return rangefinder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorRangefinder{");
        sb.append("quality_uint8=").append(quality_uint8);
        sb.append(", distanceMm_int32=").append(distanceMm_int32);
        sb.append('}');
        return sb.toString();
    }
//    uint8_t quality;    // [0;255]
//    int32_t distanceMm; // Negative value for out of range
}
