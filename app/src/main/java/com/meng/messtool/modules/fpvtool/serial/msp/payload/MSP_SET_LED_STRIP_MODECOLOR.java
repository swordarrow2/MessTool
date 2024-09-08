package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_LED_STRIP_MODECOLOR implements IEncodeable {

    public MSP_LED_STRIP_MODECOLOR.MSP_LED_STRIP_MODECOLOR_ITEM[][] msp_led_strip_modecolor_items = new MSP_LED_STRIP_MODECOLOR.MSP_LED_STRIP_MODECOLOR_ITEM[6][6];
    public MSP_LED_STRIP_MODECOLOR.LED_SPECIAL_COLOR[] led_special_colors = new MSP_LED_STRIP_MODECOLOR.LED_SPECIAL_COLOR[9];

    public MSP_SET_LED_STRIP_MODECOLOR(MSP_LED_STRIP_MODECOLOR.MSP_LED_STRIP_MODECOLOR_ITEM[][] msp_led_strip_modecolor_items, MSP_LED_STRIP_MODECOLOR.LED_SPECIAL_COLOR[] led_special_colors) {
        this.msp_led_strip_modecolor_items = msp_led_strip_modecolor_items;
        this.led_special_colors = led_special_colors;
    }

    public MSP_SET_LED_STRIP_MODECOLOR() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (MSP_LED_STRIP_MODECOLOR.MSP_LED_STRIP_MODECOLOR_ITEM[] array : msp_led_strip_modecolor_items) {
            for (MSP_LED_STRIP_MODECOLOR.MSP_LED_STRIP_MODECOLOR_ITEM item : array) {
                writer.writeUint8(item.i)
                        .writeUint8(item.j)
                        .writeUint8(item.color);
            }
        }
        for (MSP_LED_STRIP_MODECOLOR.LED_SPECIAL_COLOR color : led_special_colors) {
            writer.writeUint8(color.LED_MODE_COUNT)
                    .writeUint8(color.j)
                    .writeUint8(color.color);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_LED_STRIP_MODECOLOR{" + "msp_led_strip_modecolor_items=" + Arrays.toString(msp_led_strip_modecolor_items) +
                ", led_special_colors=" + Arrays.toString(led_special_colors) +
                '}';
    }
}
