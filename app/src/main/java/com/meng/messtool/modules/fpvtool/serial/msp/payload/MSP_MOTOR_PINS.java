package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:43
 */
public class MSP_MOTOR_PINS {

    public int pin1;
    public int pin2;
    public int pin3;
    public int pin4;
    public int pin5;
    public int pin6;
    public int pin7;
    public int pin8;

    public MSP_MOTOR_PINS(int pin1, int pin2, int pin3, int pin4, int pin5, int pin6, int pin7, int pin8) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.pin3 = pin3;
        this.pin4 = pin4;
        this.pin5 = pin5;
        this.pin6 = pin6;
        this.pin7 = pin7;
        this.pin8 = pin8;
    }
}
