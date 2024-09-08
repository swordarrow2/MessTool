package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:18
 */
public class MSP_RX_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_RX_CONFIG";

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

    public MSP_RX_CONFIG(int serialrx_provider, int maxcheck, int PWM_RANGE_MIDDLE, int mincheck, int spektrum_sat_bind, int rx_min_usec, int rx_max_usec, int rcInterpolation, int rcInterpolationInterval, int airModeActivateThreshold, int zero, int zero1, int zero2, int fpvCamAngleDegrees, int receiverType) {
        Debuger.checkDebugMode();
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

    public MSP_RX_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        serialrx_provider = reader.readUint8();
        maxcheck = reader.readUint16();
        PWM_RANGE_MIDDLE = reader.readUint16();
        mincheck = reader.readUint16();
        spektrum_sat_bind = reader.readUint8();
        rx_min_usec = reader.readUint16();
        rx_max_usec = reader.readUint16();
        rcInterpolation = reader.readUint8();
        rcInterpolationInterval = reader.readUint8();
        airModeActivateThreshold = reader.readUint16();
        zero = reader.readUint8();
        zero1 = reader.readInt32();
        zero2 = reader.readUint8();
        fpvCamAngleDegrees = reader.readUint8();
        receiverType = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RX_CONFIG{" + "serialrx_provider=" + serialrx_provider +
                ", maxcheck=" + maxcheck +
                ", pwmRangeMiddle=" + PWM_RANGE_MIDDLE +
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
