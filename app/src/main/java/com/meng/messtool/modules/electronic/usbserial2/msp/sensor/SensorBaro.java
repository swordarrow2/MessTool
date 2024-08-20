package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 18:01
 */
public class SensorBaro {
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
//    uint8_t instance;
//    uint32_t timeMs;
//    float pressurePa;
//    int16_t temp; // centi-degrees C
}
