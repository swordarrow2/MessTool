package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP2_INAV_SET_LED_STRIP_CONFIG_EX implements IEncodeable {

    public int index;
    public int led_position;
    public int led_function;
    public int led_overlay;
    public int led_color;
    public int led_direction;
    public int led_params;
    public MSP2_INAV_LED_STRIP_CONFIG_EX.MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[] msp2_inav_led_strip_config_ex_items = new MSP2_INAV_LED_STRIP_CONFIG_EX.MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[128];

    public MSP2_INAV_SET_LED_STRIP_CONFIG_EX(int index, int led_position, int led_function, int led_overlay, int led_color, int led_direction, int led_params, MSP2_INAV_LED_STRIP_CONFIG_EX.MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[] msp2_inav_led_strip_config_ex_items) {
        this.index = index;
        this.led_position = led_position;
        this.led_function = led_function;
        this.led_overlay = led_overlay;
        this.led_color = led_color;
        this.led_direction = led_direction;
        this.led_params = led_params;
        this.msp2_inav_led_strip_config_ex_items = msp2_inav_led_strip_config_ex_items;
    }

    public MSP2_INAV_SET_LED_STRIP_CONFIG_EX() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index).writeUint8(led_position).writeUint8(led_function).writeUint8(led_overlay);
        int pack1 = (led_color & 0b1111) | ((led_direction & 0b1111) << 4);
        int pack2 = ((led_direction >>> 4) & 0b11) | led_params << 2;
        writer.writeUint8(pack1).writeUint8(pack2);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_LED_STRIP_CONFIG_EX{" + "index=" + index +
                ", led_position=" + led_position +
                ", led_function=" + led_function +
                ", led_overlay=" + led_overlay +
                ", led_color=" + led_color +
                ", led_direction=" + led_direction +
                ", led_params=" + led_params +
                ", msp2_inav_led_strip_config_ex_items=" + Arrays.toString(msp2_inav_led_strip_config_ex_items) +
                '}';
    }
}
