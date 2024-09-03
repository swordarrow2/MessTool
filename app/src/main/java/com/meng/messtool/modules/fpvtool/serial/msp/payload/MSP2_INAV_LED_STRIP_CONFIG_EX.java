package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:35
 */
public class MSP2_INAV_LED_STRIP_CONFIG_EX {

    public int led_position;// 8bit
    public int led_function;// 8bit
    public int led_overlay;// 8bit
    public int led_color;// 4bit
    public int led_direction;// 6bit
    public int led_params;// 6bit

    public MSP2_INAV_LED_STRIP_CONFIG_EX(int led_position, int led_function, int led_overlay, int led_color, int led_direction, int led_params) {
        this.led_position = led_position;
        this.led_function = led_function;
        this.led_overlay = led_overlay;
        this.led_color = led_color;
        this.led_direction = led_direction;
        this.led_params = led_params;
    }
}
