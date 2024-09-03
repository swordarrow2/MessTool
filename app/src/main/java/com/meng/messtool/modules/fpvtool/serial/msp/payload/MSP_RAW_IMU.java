package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:18
 */
public class MSP_RAW_IMU {

    public int accADCf1;
    public int accADCf2;
    public int accADCf3;
    public int gyroRateDps1;
    public int gyroRateDps2;
    public int gyroRateDps3;
    public int magADC1;
    public int magADC2;
    public int magADC3;

    public MSP_RAW_IMU(int accADCf1, int accADCf2, int accADCf3, int gyroRateDps1, int gyroRateDps2, int gyroRateDps3, int magADC1, int magADC2, int magADC3) {
        this.accADCf1 = accADCf1;
        this.accADCf2 = accADCf2;
        this.accADCf3 = accADCf3;
        this.gyroRateDps1 = gyroRateDps1;
        this.gyroRateDps2 = gyroRateDps2;
        this.gyroRateDps3 = gyroRateDps3;
        this.magADC1 = magADC1;
        this.magADC2 = magADC2;
        this.magADC3 = magADC3;
    }
}
