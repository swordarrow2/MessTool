package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:37
 */
public class SensorRangefinder {
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
//    uint8_t quality;    // [0;255]
//    int32_t distanceMm; // Negative value for out of range
}
