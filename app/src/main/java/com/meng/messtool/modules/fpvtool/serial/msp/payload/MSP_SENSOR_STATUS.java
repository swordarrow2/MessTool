package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:17
 */
public class MSP_SENSOR_STATUS {

    public MSP_SENSOR_STATUS(int isHardwareHealthy, int getHwGyroStatus, int getHwAccelerometerStatus, int getHwCompassStatus, int getHwBarometerStatus, int getHwGPSStatus, int getHwRangefinderStatus, int getHwPitotmeterStatus, int getHwOpticalFlowStatus) {
        this.isHardwareHealthy = isHardwareHealthy;
        this.getHwGyroStatus = getHwGyroStatus;
        this.getHwAccelerometerStatus = getHwAccelerometerStatus;
        this.getHwCompassStatus = getHwCompassStatus;
        this.getHwBarometerStatus = getHwBarometerStatus;
        this.getHwGPSStatus = getHwGPSStatus;
        this.getHwRangefinderStatus = getHwRangefinderStatus;
        this.getHwPitotmeterStatus = getHwPitotmeterStatus;
        this.getHwOpticalFlowStatus = getHwOpticalFlowStatus;
    }

    public int isHardwareHealthy;
    public int getHwGyroStatus;
    public int getHwAccelerometerStatus;
    public int getHwCompassStatus;
    public int getHwBarometerStatus;
    public int getHwGPSStatus;
    public int getHwRangefinderStatus;
    public int getHwPitotmeterStatus;
    public int getHwOpticalFlowStatus;
}
