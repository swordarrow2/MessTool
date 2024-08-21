package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:36
 */
public class SensorOpflow {
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

    public byte[] encode() {
        byte[] result = new byte[9];

        result[0] = (byte) quality_uint8;

        result[1] = (byte) (motionX_int32 >>> 0);
        result[2] = (byte) (motionX_int32 >>> 8);
        result[3] = (byte) (motionX_int32 >>> 16);
        result[4] = (byte) (motionX_int32 >>> 24);

        result[5] = (byte) (motionY_int32 >>> 0);
        result[6] = (byte) (motionY_int32 >>> 8);
        result[7] = (byte) (motionY_int32 >>> 16);
        result[8] = (byte) (motionY_int32 >>> 24);
        return result;
    }

//    uint8_t quality; // [0;255]
//    int32_t motionX;
//    int32_t motionY;
}
