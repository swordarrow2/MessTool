package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:35
 */
public class MSP2_INAV_MISC2 {

    public int seconds;
    public int getFlightTime;
    public int getThrottlePercentUseScaled;
    public int navigationIsControllingThrottle;

    public MSP2_INAV_MISC2(int seconds, int getFlightTime, int getThrottlePercentUseScaled, int navigationIsControllingThrottle) {
        this.seconds = seconds;
        this.getFlightTime = getFlightTime;
        this.getThrottlePercentUseScaled = getThrottlePercentUseScaled;
        this.navigationIsControllingThrottle = navigationIsControllingThrottle;
    }
}
