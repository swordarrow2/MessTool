package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_INAV_PID implements IEncodeable {
    @InavIgnore
    public int zero;
    @InavIgnore
    public int zero1;
    @InavIgnore
    public int zero2;
    public int heading_hold_rate_limit;
    @InavIgnore
    public int HEADING_HOLD_ERROR_LPF_FREQ;
    @InavIgnore
    public int yaw_jump_prevention_limit;
    public int gyro_lpf;
    public int acc_lpf_hz;
    @InavReserve
    public int reserved1;
    @InavReserve
    public int reserved2;
    @InavReserve
    public int reserved3;
    @InavReserve
    public int reserved4;

    public MSP_SET_INAV_PID(int zero, int zero1, int zero2, int heading_hold_rate_limit, int HEADING_HOLD_ERROR_LPF_FREQ, int yaw_jump_prevention_limit, int gyro_lpf, int acc_lpf_hz, int reserved1, int reserved2, int reserved3, int reserved4) {
        this.zero = zero;
        this.zero1 = zero1;
        this.zero2 = zero2;
        this.heading_hold_rate_limit = heading_hold_rate_limit;
        this.HEADING_HOLD_ERROR_LPF_FREQ = HEADING_HOLD_ERROR_LPF_FREQ;
        this.yaw_jump_prevention_limit = yaw_jump_prevention_limit;
        this.gyro_lpf = gyro_lpf;
        this.acc_lpf_hz = acc_lpf_hz;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
        this.reserved3 = reserved3;
        this.reserved4 = reserved4;
    }

    public MSP_SET_INAV_PID() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(zero)
                .writeUint16(zero1)
                .writeUint16(zero2)
                .writeUint8(heading_hold_rate_limit)
                .writeUint8(HEADING_HOLD_ERROR_LPF_FREQ)
                .writeUint16(yaw_jump_prevention_limit)
                .writeUint8(gyro_lpf)
                .writeUint8(acc_lpf_hz)
                .writeUint8(reserved1)
                .writeUint8(reserved2)
                .writeUint8(reserved3)
                .writeUint8(reserved4);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_INAV_PID{" + "zero=" + zero +
                ", zero1=" + zero1 +
                ", zero2=" + zero2 +
                ", heading_hold_rate_limit=" + heading_hold_rate_limit +
                ", HEADING_HOLD_ERROR_LPF_FREQ=" + HEADING_HOLD_ERROR_LPF_FREQ +
                ", yaw_jump_prevention_limit=" + yaw_jump_prevention_limit +
                ", gyro_lpf=" + gyro_lpf +
                ", acc_lpf_hz=" + acc_lpf_hz +
                ", reserved1=" + reserved1 +
                ", reserved2=" + reserved2 +
                ", reserved3=" + reserved3 +
                ", reserved4=" + reserved4 +
                '}';
    }
}
