package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:12
 */
public class MSP_ATTITUDE {

    public int roll;
    public int pitch;
    public int yaw;

    public MSP_ATTITUDE(int roll, int pitch, int yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }
}
