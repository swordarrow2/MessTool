package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_FILTER_CONFIG implements IEncodeable {

    public int gyro_main_lpf_hz;
    public int dterm_lpf_hz;
    public int yaw_lpf_hz;
    @InavIgnore
    public int gyro_notch_hz;
    @InavIgnore
    public int gyro_notch_cutoff;
    @InavIgnore
    public int dterm_notch_hz;
    @InavIgnore
    public int dterm_notch_cutoff;
    @InavIgnore
    public int gyro_soft_notch_hz_2;
    @InavIgnore
    public int gyro_soft_notch_cutoff_2;
    public int acc_notch_hz;
    public int acc_notch_cutoff;
    @InavIgnore
    public int gyro_stage2_lowpass_hz;

    public MSP_SET_FILTER_CONFIG(int gyro_main_lpf_hz, int dterm_lpf_hz, int yaw_lpf_hz, int gyro_notch_hz, int gyro_notch_cutoff, int dterm_notch_hz, int dterm_notch_cutoff, int gyro_soft_notch_hz_2, int gyro_soft_notch_cutoff_2, int acc_notch_hz, int acc_notch_cutoff, int gyro_stage2_lowpass_hz) {
        this.gyro_main_lpf_hz = gyro_main_lpf_hz;
        this.dterm_lpf_hz = dterm_lpf_hz;
        this.yaw_lpf_hz = yaw_lpf_hz;
        this.gyro_notch_hz = gyro_notch_hz;
        this.gyro_notch_cutoff = gyro_notch_cutoff;
        this.dterm_notch_hz = dterm_notch_hz;
        this.dterm_notch_cutoff = dterm_notch_cutoff;
        this.gyro_soft_notch_hz_2 = gyro_soft_notch_hz_2;
        this.gyro_soft_notch_cutoff_2 = gyro_soft_notch_cutoff_2;
        this.acc_notch_hz = acc_notch_hz;
        this.acc_notch_cutoff = acc_notch_cutoff;
        this.gyro_stage2_lowpass_hz = gyro_stage2_lowpass_hz;
    }

    public MSP_SET_FILTER_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(gyro_main_lpf_hz)
                .writeUint16(dterm_lpf_hz)
                .writeUint16(yaw_lpf_hz)
                .writeUint16(gyro_notch_hz)
                .writeUint16(gyro_notch_cutoff)
                .writeUint16(dterm_notch_hz)
                .writeUint16(dterm_notch_cutoff)
                .writeUint16(gyro_soft_notch_hz_2)
                .writeUint16(gyro_soft_notch_cutoff_2)
                .writeUint16(acc_notch_hz)
                .writeUint16(acc_notch_cutoff)
                .writeUint16(gyro_stage2_lowpass_hz);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_FILTER_CONFIG{" + "gyro_main_lpf_hz=" + gyro_main_lpf_hz +
                ", dterm_lpf_hz=" + dterm_lpf_hz +
                ", yaw_lpf_hz=" + yaw_lpf_hz +
                ", gyro_notch_hz=" + gyro_notch_hz +
                ", gyro_notch_cutoff=" + gyro_notch_cutoff +
                ", dterm_notch_hz=" + dterm_notch_hz +
                ", dterm_notch_cutoff=" + dterm_notch_cutoff +
                ", gyro_soft_notch_hz_2=" + gyro_soft_notch_hz_2 +
                ", gyro_soft_notch_cutoff_2=" + gyro_soft_notch_cutoff_2 +
                ", acc_notch_hz=" + acc_notch_hz +
                ", acc_notch_cutoff=" + acc_notch_cutoff +
                ", gyro_stage2_lowpass_hz=" + gyro_stage2_lowpass_hz +
                '}';
    }
}
