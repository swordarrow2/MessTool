package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

import com.meng.messtool.modules.electronic.usbserial2.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 18:01
 */
public class SensorBaro implements MspSeriallizeable<SensorBaro> {
    private int instance_uint8;
    private long timeMs_uint32;
    private float pressurePa_float;
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

    public float getPressurePa_float() {
        return pressurePa_float;
    }

    public void setPressurePa_float(float pressurePa_float) {
        this.pressurePa_float = pressurePa_float;
    }

    public int getTemp_int16() {
        return temp_int16;
    }

    public void setTemp_int16(int temp_int16) {
        this.temp_int16 = temp_int16 & 0xFFFF;
    }

    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getInstance_uint8())
                .writeUint32(getTimeMs_uint32())
                .writeFloat(getPressurePa_float())
                .writeInt16(getTemp_int16())
                .encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setInstance_uint8(reader.readUint8());
        setTimeMs_uint32(reader.readUint32());
        setPressurePa_float(reader.readFloat());
        setTemp_int16(reader.readInt16());
    }

    @Override
    public SensorBaro deepCopy() {
        SensorBaro sensorBaro = new SensorBaro();
        sensorBaro.setInstance_uint8(getInstance_uint8());
        sensorBaro.setTimeMs_uint32(getTimeMs_uint32());
        sensorBaro.setPressurePa_float(getPressurePa_float());
        sensorBaro.setTemp_int16(getTemp_int16());
        return sensorBaro;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorBaro{");
        sb.append("instance_uint8=").append(instance_uint8);
        sb.append(", timeMs_uint32=").append(timeMs_uint32);
        sb.append(", pressurePa_float=").append(pressurePa_float);
        sb.append(", temp_int16=").append(temp_int16);
        sb.append('}');
        return sb.toString();
    }

//    uint8_t instance;
//    uint32_t timeMs;
//    float pressurePa;
//    int16_t temp; // centi-degrees C
}
