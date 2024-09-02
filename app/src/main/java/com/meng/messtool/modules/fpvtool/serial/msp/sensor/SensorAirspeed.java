package com.meng.messtool.modules.fpvtool.serial.msp.sensor;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 18:05
 */
public class SensorAirspeed implements MspSeriallizeable<SensorAirspeed> {
    private int instance_uint8;
    private long timeMs_uint32;
    private float diffPressurePa_float;
    private int temp_int16; // centi-degrees C

    public int getInstance_uint8() {
        return instance_uint8;
    }

    public void setInstance_uint8(int instance_uint8) {
        this.instance_uint8 = instance_uint8 & 0xFF;
    }

    public long getTimeMs_uint32() {
        return timeMs_uint32;
    }

    public void setTimeMs_uint32(long timeMs_uint32) {
        this.timeMs_uint32 = timeMs_uint32;
    }

    public float getDiffPressurePa_float() {
        return diffPressurePa_float;
    }

    public void setDiffPressurePa_float(float diffPressurePa_float) {
        this.diffPressurePa_float = diffPressurePa_float;
    }

    public int getTemp_int16() {
        return temp_int16;
    }

    public void setTemp_int16(int temp_int16) {
        this.temp_int16 = temp_int16 & 0xFFFF;
    }

    @Override
    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getInstance_uint8())
                .writeUint32(getTimeMs_uint32())
                .writeFloat(getDiffPressurePa_float())
                .writeInt16(getTemp_int16()).encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setInstance_uint8(reader.readUint8());
        setTimeMs_uint32(reader.readUint32());
        setDiffPressurePa_float(reader.readFloat());
        setTemp_int16(reader.readInt16());
    }

    @Override
    public SensorAirspeed deepCopy() {
        SensorAirspeed airspeed = new SensorAirspeed();
        airspeed.setTemp_int16(getTemp_int16());
        airspeed.setTimeMs_uint32(getTimeMs_uint32());
        airspeed.setDiffPressurePa_float(getDiffPressurePa_float());
        airspeed.setTemp_int16(getTemp_int16());
        return airspeed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorAirspeed{");
        sb.append("instance_uint8=").append(instance_uint8);
        sb.append(", timeMs_uint32=").append(timeMs_uint32);
        sb.append(", diffPressurePa_float=").append(diffPressurePa_float);
        sb.append(", temp_int16=").append(temp_int16);
        sb.append('}');
        return sb.toString();
    }
    //    uint8_t instance;
//    uint32_t timeMs;
//    float diffPressurePa;
//    int16_t temp; // centi-degrees C
}
