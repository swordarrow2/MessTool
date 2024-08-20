package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:36
 */
public class SensorOpflowData {
    private int quality_uint8; // [0;255]
    private int motionX_int32;
    private int motionY_int32;

    public int getQuality_uint8() {
        return quality_uint8;
    }

    public void setQuality(int quality_uint8) {
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
//    uint8_t quality; // [0;255]
//    int32_t motionX;
//    int32_t motionY;
}
