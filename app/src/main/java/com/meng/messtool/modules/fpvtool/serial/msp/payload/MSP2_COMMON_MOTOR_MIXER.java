package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:05
 */
public class MSP2_COMMON_MOTOR_MIXER {
    public int throttle;
    public int roll;
    public int pitch;

    public MSP2_COMMON_MOTOR_MIXER(int throttle, int roll, int pitch, int yaw) {
        this.throttle = throttle;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public int yaw;
}
