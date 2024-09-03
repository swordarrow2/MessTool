package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:30
 */
public class MSP_SERVO_MIX_RULES {

    public int targetChannel;
    public int inputSource;
    public int rate;
    public int speed;
    public int fixed_u8_0_1;
    public int fixed_u8_100;
    public int fixed_u8_0_2;

    public MSP_SERVO_MIX_RULES(int targetChannel, int inputSource, int rate, int speed, int fixed_u8_0_1, int fixed_u8_100, int fixed_u8_0_2) {
        this.targetChannel = targetChannel;
        this.inputSource = inputSource;
        this.rate = rate;
        this.speed = speed;
        this.fixed_u8_0_1 = fixed_u8_0_1;
        this.fixed_u8_100 = fixed_u8_100;
        this.fixed_u8_0_2 = fixed_u8_0_2;
    }
}
