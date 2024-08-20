package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 18:07
 */
public class SensorCompass {
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
//    uint8_t instance;
//    uint32_t timeMs;
//    int16_t magX; // mGauss, front
//    int16_t magY; // mGauss, right
//    int16_t magZ; // mGauss, down
}
