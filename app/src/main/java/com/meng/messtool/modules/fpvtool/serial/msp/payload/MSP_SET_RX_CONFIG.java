package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_RX_CONFIG implements IEncodeable {

    public int serialrx_provider;
    public int maxcheck;
    @InavIgnore
    public int PWM_RANGE_MIDDLE;
    public int mincheck;
    public int spektrum_sat_bind;
    public int rx_min_usec;
    public int rx_max_usec;
    @InavIgnore
    public int rcInterpolation;
    @InavIgnore
    public int rcInterpolationInterval;
    @InavIgnore
    public int airModeActivateThreshold;
    @InavIgnore
    public int zero;
    @InavIgnore
    public int zero1;
    @InavIgnore
    public int zero2;
    @InavIgnore
    public int fpvCamAngleDegrees;
    public int receiverType;

    public MSP_SET_RX_CONFIG(int serialrx_provider, int maxcheck, int PWM_RANGE_MIDDLE, int mincheck, int spektrum_sat_bind, int rx_min_usec, int rx_max_usec, int rcInterpolation, int rcInterpolationInterval, int airModeActivateThreshold, int zero, int zero1, int zero2, int fpvCamAngleDegrees, int receiverType) {
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

    public MSP_SET_RX_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(serialrx_provider)
                .writeUint16(maxcheck)
                .writeUint16(PWM_RANGE_MIDDLE)
                .writeUint16(mincheck)
                .writeUint8(spektrum_sat_bind)
                .writeUint16(rx_min_usec)
                .writeUint16(rx_max_usec)
                .writeUint8(rcInterpolation)
                .writeUint8(rcInterpolationInterval)
                .writeUint16(airModeActivateThreshold)
                .writeUint8(zero)
                .writeUint32(zero1)
                .writeUint8(zero2)
                .writeUint8(fpvCamAngleDegrees)
                .writeUint8(receiverType);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RX_CONFIG{" + "serialrx_provider=" + serialrx_provider +
                ", maxcheck=" + maxcheck +
                ", PWM_RANGE_MIDDLE=" + PWM_RANGE_MIDDLE +
                ", mincheck=" + mincheck +
                ", spektrum_sat_bind=" + spektrum_sat_bind +
                ", rx_min_usec=" + rx_min_usec +
                ", rx_max_usec=" + rx_max_usec +
                ", rcInterpolation=" + rcInterpolation +
                ", rcInterpolationInterval=" + rcInterpolationInterval +
                ", airModeActivateThreshold=" + airModeActivateThreshold +
                ", zero=" + zero +
                ", zero1=" + zero1 +
                ", zero2=" + zero2 +
                ", fpvCamAngleDegrees=" + fpvCamAngleDegrees +
                ", receiverType=" + receiverType +
                '}';
    }
}
