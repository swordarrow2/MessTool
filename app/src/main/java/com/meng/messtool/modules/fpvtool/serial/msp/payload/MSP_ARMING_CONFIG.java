package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:53
 */
public class MSP_ARMING_CONFIG {

    public int zero;
    public int disarm_kill_switch;

    public MSP_ARMING_CONFIG(int zero, int disarm_kill_switch) {
        this.zero = zero;
        this.disarm_kill_switch = disarm_kill_switch;
    }
}
