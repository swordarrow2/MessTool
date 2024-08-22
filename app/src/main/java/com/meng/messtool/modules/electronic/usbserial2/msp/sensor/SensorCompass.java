package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

import com.meng.messtool.modules.electronic.usbserial2.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 18:07
 */
public class SensorCompass implements MspSeriallizeable<SensorCompass> {
    private int instance_uint8;
    private long timeMs;
    private int magX_int16; // mGauss, front
    private int magY_int16; // mGauss, right
    private int magZ_int16; // mGauss, down

    public int getInstance_uint8() {
        return instance_uint8;
    }

    public void setInstance_uint8(int instance_uint8) {
        this.instance_uint8 = instance_uint8 & 0xFF;
    }

    public long getTimeMs() {
        return timeMs;
    }

    public void setTimeMs(long timeMs) {
        this.timeMs = timeMs & 0xFFFFFFFFL;
    }

    public int getMagX_int16() {
        return magX_int16;
    }

    public void setMagX_int16(int magX_int16) {
        this.magX_int16 = magX_int16 & 0xFFFF;
    }

    public int getMagY_int16() {
        return magY_int16;
    }

    public void setMagY_int16(int magY_int16) {
        this.magY_int16 = magY_int16 & 0xFF;
    }

    public int getMagZ_int16() {
        return magZ_int16;
    }

    public void setMagZ_int16(int magZ_int16) {
        this.magZ_int16 = magZ_int16 & 0xFF;
    }

    @Override
    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getInstance_uint8())
                .writeUint32(getTimeMs())
                .writeInt16(getMagX_int16())
                .writeInt16(getMagY_int16())
                .writeInt16(getMagZ_int16())
                .encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setInstance_uint8(reader.readUint8());
        setTimeMs(reader.readUint32());
        setMagX_int16(reader.readInt16());
        setMagY_int16(reader.readInt16());
        setMagZ_int16(reader.readInt16());
    }

    @Override
    public SensorCompass deepCopy() {
        SensorCompass compass = new SensorCompass();
        compass.setInstance_uint8(getInstance_uint8());
        compass.setTimeMs(getTimeMs());
        compass.setMagX_int16(getMagX_int16());
        compass.setMagY_int16(getMagY_int16());
        compass.setMagZ_int16(getMagZ_int16());
        return compass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorCompass{");
        sb.append("instance_uint8=").append(instance_uint8);
        sb.append(", timeMs=").append(timeMs);
        sb.append(", magX_int16=").append(magX_int16);
        sb.append(", magY_int16=").append(magY_int16);
        sb.append(", magZ_int16=").append(magZ_int16);
        sb.append('}');
        return sb.toString();
    }
    //    uint8_t instance;
//    uint32_t timeMs;
//    int16_t magX; // mGauss, front
//    int16_t magY; // mGauss, right
//    int16_t magZ; // mGauss, down
}
