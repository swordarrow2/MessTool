package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:30
 */
public class MSP2_INAV_OPTICAL_FLOW {

    public int rawQuality;
    public int flowRateX;
    public int flowRateY;
    public int bodyRateX;
    public int bodyRateY;

    public MSP2_INAV_OPTICAL_FLOW(int rawQuality, int flowRateX, int flowRateY, int bodyRateX, int bodyRateY) {
        this.rawQuality = rawQuality;
        this.flowRateX = flowRateX;
        this.flowRateY = flowRateY;
        this.bodyRateX = bodyRateX;
        this.bodyRateY = bodyRateY;
    }
}
