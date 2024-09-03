package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:18
 */
public class MSP_RX_CONFIG {

    public int serialrx_provider;
    public int maxcheck;
    public int PWM_RANGE_MIDDLE;
    public int mincheck;
    public int spektrum_sat_bind;
    public int rx_min_usec;
    public int rx_max_usec;
    public int rcInterpolation;
    public int rcInterpolationInterval;
    public int airModeActivateThreshold;
    public int zero;
    public int zero1;
    public int zero2;
    public int fpvCamAngleDegrees;
    public int receiverType;

    public MSP_RX_CONFIG(int serialrx_provider, int maxcheck, int PWM_RANGE_MIDDLE, int mincheck, int spektrum_sat_bind, int rx_min_usec, int rx_max_usec, int rcInterpolation, int rcInterpolationInterval, int airModeActivateThreshold, int zero, int zero1, int zero2, int fpvCamAngleDegrees, int receiverType) {
        this.serialrx_provider = serialrx_provider;
        this.maxcheck = maxcheck;
        this.PWM_RANGE_MIDDLE = PWM_RANGE_MIDDLE;
        this.mincheck = mincheck;
        this.spektrum_sat_bind = spektrum_sat_bind;
        this.rx_min_usec = rx_min_usec;
        this.rx_max_usec = rx_max_usec;
        this.rcInterpolation = rcInterpolation;
        this.rcInterpolationInterval = rcInterpolationInterval;
        this.airModeActivateThreshold = airModeActivateThreshold;
        this.zero = zero;
        this.zero1 = zero1;
        this.zero2 = zero2;
        this.fpvCamAngleDegrees = fpvCamAngleDegrees;
        this.receiverType = receiverType;
    }
}
