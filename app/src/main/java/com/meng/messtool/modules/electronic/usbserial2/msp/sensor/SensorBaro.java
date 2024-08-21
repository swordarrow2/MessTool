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

    public byte[] encode() {
        byte[] result = new byte[15];

        result[0] = (byte) instance_uint8;

        result[1] = (byte) (timeMs_uint32 >>> 0);
        result[2] = (byte) (timeMs_uint32 >>> 8);
        result[3] = (byte) (timeMs_uint32 >>> 16);
        result[4] = (byte) (timeMs_uint32 >>> 24);
        result[5] = (byte) (timeMs_uint32 >>> 32);
        result[6] = (byte) (timeMs_uint32 >>> 40);
        result[7] = (byte) (timeMs_uint32 >>> 48);
        result[8] = (byte) (timeMs_uint32 >>> 56);

        int fbyte = Float.floatToRawIntBits(pressurePa_float);

        result[9] = (byte) (fbyte >>> 0);
        result[10] = (byte) (fbyte >>> 8);
        result[11] = (byte) (fbyte >>> 16);
        result[12] = (byte) (fbyte >>> 24);


        result[13] = (byte) (temp_int16 >>> 0);
        result[14] = (byte) (temp_int16 >>> 8);
        return result;
    }

//    uint8_t instance;
//    uint32_t timeMs;
//    float pressurePa;
//    int16_t temp; // centi-degrees C
}
