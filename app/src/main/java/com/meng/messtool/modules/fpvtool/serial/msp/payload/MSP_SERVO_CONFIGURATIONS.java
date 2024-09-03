package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:21
 */
public class MSP_SERVO_CONFIGURATIONS {
    public int min;
    public int max;
    public int middle;
    public int rate;
    public int fixed_u8_0_1;
    public int fixed_u8_0_2;
    public int fixed_255;
    public int fixed_u32_0;

    public MSP_SERVO_CONFIGURATIONS(int min, int max, int middle, int rate, int fixed_u8_0_1, int fixed_u8_0_2, int fixed_255, int fixed_u32_0) {
        this.min = min;
        this.max = max;
        this.middle = middle;
        this.rate = rate;
        this.fixed_u8_0_1 = fixed_u8_0_1;
        this.fixed_u8_0_2 = fixed_u8_0_2;
        this.fixed_255 = fixed_255;
        this.fixed_u32_0 = fixed_u32_0;
    }
}
