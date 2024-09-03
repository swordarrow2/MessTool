package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:23
 */
public class MSP_FAILSAFE_CONFIG {

    public int failsafe_delay;
    public int failsafe_off_delay;
    public int failsafe_throttle;
    public int zero;
    public int failsafe_throttle_low_delay;
    public int failsafe_procedure;
    public int failsafe_recovery_delay;
    public int failsafe_fw_roll_angle;
    public int failsafe_fw_pitch_angle;
    public int failsafe_fw_yaw_rate;
    public int failsafe_stick_motion_threshold;
    public int failsafe_min_distance;
    public int failsafe_min_distance_procedure;

    public MSP_FAILSAFE_CONFIG(int failsafe_delay, int failsafe_off_delay, int failsafe_throttle, int zero, int failsafe_throttle_low_delay, int failsafe_procedure, int failsafe_recovery_delay, int failsafe_fw_roll_angle, int failsafe_fw_pitch_angle, int failsafe_fw_yaw_rate, int failsafe_stick_motion_threshold, int failsafe_min_distance, int failsafe_min_distance_procedure) {
        this.failsafe_delay = failsafe_delay;
        this.failsafe_off_delay = failsafe_off_delay;
        this.failsafe_throttle = failsafe_throttle;
        this.zero = zero;
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
}
