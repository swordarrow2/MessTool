package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_LED_STRIP_CONFIG implements IEncodeable {
    //todo bit mask
    public int[] msp_led_strip_config_items = new int[128];

    public MSP_SET_LED_STRIP_CONFIG(int[] msp_led_strip_config_items) {
        this.msp_led_strip_config_items = msp_led_strip_config_items;
    }

    public MSP_SET_LED_STRIP_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (int config : msp_led_strip_config_items) {
            writer.writeInt32(config);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_LED_STRIP_CONFIG{" + "msp_led_strip_config_items=" + Arrays.toString(msp_led_strip_config_items) + '}';
    }
}
