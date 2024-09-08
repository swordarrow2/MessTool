package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_LED_COLORS implements IEncodeable {

    public MSP_LED_COLORS.MSP_LED_COLORS_ITEM[] msp_led_colors_items = new MSP_LED_COLORS.MSP_LED_COLORS_ITEM[16];

    public MSP_SET_LED_COLORS(MSP_LED_COLORS.MSP_LED_COLORS_ITEM[] msp_led_colors_items) {
        this.msp_led_colors_items = msp_led_colors_items;
    }

    public MSP_SET_LED_COLORS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (MSP_LED_COLORS.MSP_LED_COLORS_ITEM item : msp_led_colors_items) {
            writer.writeUint16(item.h)
                    .writeUint8(item.s)
                    .writeUint8(item.v);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_LED_COLORS{" + "msp_led_colors_items=" + Arrays.toString(msp_led_colors_items) + '}';
    }
}
