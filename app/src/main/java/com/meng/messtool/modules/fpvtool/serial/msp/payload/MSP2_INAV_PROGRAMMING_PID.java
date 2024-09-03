package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:55
 */
public class MSP2_INAV_PROGRAMMING_PID {
    public int enabled;
    public int setpointType;
    public int setpointValue;
    public int measurementType;
    public int measurementValue;
    public int gainsP;
    public int gainsI;
    public int gainsD;

    public MSP2_INAV_PROGRAMMING_PID(int enabled, int setpointType, int setpointValue, int measurementType, int measurementValue, int gainsP, int gainsI, int gainsD, int gainsFF) {
        this.enabled = enabled;
        this.setpointType = setpointType;
        this.setpointValue = setpointValue;
        this.measurementType = measurementType;
        this.measurementValue = measurementValue;
        this.gainsP = gainsP;
        this.gainsI = gainsI;
        this.gainsD = gainsD;
        this.gainsFF = gainsFF;
    }

    public int gainsFF;
}
