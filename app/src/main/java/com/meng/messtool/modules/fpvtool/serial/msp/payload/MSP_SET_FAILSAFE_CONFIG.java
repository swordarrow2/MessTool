package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_FAILSAFE_CONFIG implements IEncodeable {

    public int failsafe_delay;
    public int failsafe_off_delay;
    public int failsafe_throttle;
    @InavIgnore
    public int failsafe_kill_switch;
    public int failsafe_throttle_low_delay;
    public int failsafe_procedure;
    public int failsafe_recovery_delay;
    public int failsafe_fw_roll_angle;
    public int failsafe_fw_pitch_angle;
    public int failsafe_fw_yaw_rate;
    public int failsafe_stick_motion_threshold;
    public int failsafe_min_distance;
    public int failsafe_min_distance_procedure;

    public MSP_SET_FAILSAFE_CONFIG(int failsafe_delay, int failsafe_off_delay, int failsafe_throttle, int failsafe_kill_switch, int failsafe_throttle_low_delay, int failsafe_procedure, int failsafe_recovery_delay, int failsafe_fw_roll_angle, int failsafe_fw_pitch_angle, int failsafe_fw_yaw_rate, int failsafe_stick_motion_threshold, int failsafe_min_distance, int failsafe_min_distance_procedure) {
        this.failsafe_delay = failsafe_delay;
        this.failsafe_off_delay = failsafe_off_delay;
        this.failsafe_throttle = failsafe_throttle;
        this.failsafe_kill_switch = failsafe_kill_switch;
        this.failsafe_throttle_low_delay = failsafe_throttle_low_delay;
        this.failsafe_procedure = failsafe_procedure;
        this.failsafe_recovery_delay = failsafe_recovery_delay;
        this.failsafe_fw_roll_angle = failsafe_fw_roll_angle;
        this.failsafe_fw_pitch_angle = failsafe_fw_pitch_angle;
        this.failsafe_fw_yaw_rate = failsafe_fw_yaw_rate;
        this.failsafe_stick_motion_threshold = failsafe_stick_motion_threshold;
        this.failsafe_min_distance = failsafe_min_distance;
        this.failsafe_min_distance_procedure = failsafe_min_distance_procedure;
    }

    public MSP_SET_FAILSAFE_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(failsafe_delay)
                .writeUint8(failsafe_off_delay)
                .writeUint16(failsafe_throttle)
                .writeUint8(failsafe_kill_switch)
                .writeUint16(failsafe_throttle_low_delay)
                .writeUint8(failsafe_procedure)
                .writeUint8(failsafe_recovery_delay)
                .writeUint16(failsafe_fw_roll_angle)
                .writeUint16(failsafe_fw_pitch_angle)
                .writeUint16(failsafe_fw_yaw_rate)
                .writeUint16(failsafe_stick_motion_threshold)
                .writeUint16(failsafe_min_distance)
                .writeUint8(failsafe_min_distance_procedure);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_FAILSAFE_CONFIG{" + "failsafe_delay=" + failsafe_delay +
                ", failsafe_off_delay=" + failsafe_off_delay +
                ", failsafe_throttle=" + failsafe_throttle +
                ", failsafe_kill_switch=" + failsafe_kill_switch +
                ", failsafe_throttle_low_delay=" + failsafe_throttle_low_delay +
                ", failsafe_procedure=" + failsafe_procedure +
                ", failsafe_recovery_delay=" + failsafe_recovery_delay +
                ", failsafe_fw_roll_angle=" + failsafe_fw_roll_angle +
                ", failsafe_fw_pitch_angle=" + failsafe_fw_pitch_angle +
                ", failsafe_fw_yaw_rate=" + failsafe_fw_yaw_rate +
                ", failsafe_stick_motion_threshold=" + failsafe_stick_motion_threshold +
                ", failsafe_min_distance=" + failsafe_min_distance +
                ", failsafe_min_distance_procedure=" + failsafe_min_distance_procedure +
                '}';
    }
}
