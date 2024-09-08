package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:52
 */
public class MSP_FILTER_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_FILTER_CONFIG";

    public int gyro_main_lpf_hz;
    public int dterm_lpf_hz;
    public int yaw_lpf_hz;
    public int gyro_notch_hz;
    public int gyro_notch_cutoff;
    public int dterm_notch_hz;
    public int dterm_notch_cutoff;
    public int gyro_soft_notch_hz_2;
    public int gyro_soft_notch_cutoff_2;
    public int acc_notch_hz;
    public int acc_notch_cutoff;
    public int gyro_stage2_lowpass_hz;

    public MSP_FILTER_CONFIG(int gyro_main_lpf_hz, int dterm_lpf_hz, int yaw_lpf_hz, int gyro_notch_hz, int gyro_notch_cutoff, int dterm_notch_hz, int dterm_notch_cutoff, int gyro_soft_notch_hz_2, int gyro_soft_notch_cutoff_2, int acc_notch_hz, int acc_notch_cutoff, int gyro_stage2_lowpass_hz) {
        Debuger.checkDebugMode();
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

    public MSP_FILTER_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        gyro_main_lpf_hz = reader.readUint8();
        dterm_lpf_hz = reader.readUint16();
        yaw_lpf_hz = reader.readUint16();
        gyro_notch_hz = reader.readUint16();
        gyro_notch_cutoff = reader.readUint16();
        dterm_notch_hz = reader.readUint16();
        dterm_notch_cutoff = reader.readUint16();
        gyro_soft_notch_hz_2 = reader.readUint16();
        gyro_soft_notch_cutoff_2 = reader.readUint16();
        acc_notch_hz = reader.readUint16();
        acc_notch_cutoff = reader.readUint16();
        gyro_stage2_lowpass_hz = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_FILTER_CONFIG{" + "gyro_main_lpf_hz=" + gyro_main_lpf_hz +
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
