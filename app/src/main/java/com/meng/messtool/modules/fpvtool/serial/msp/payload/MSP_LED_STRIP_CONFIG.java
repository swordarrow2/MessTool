package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:33
 */
public class MSP_LED_STRIP_CONFIG implements IDecodeable {
//todo bit mask
    private static final String TAG = "MSP_LED_STRIP_CONFIG";

    public int[] msp_led_strip_config_items = new int[128];

    public MSP_LED_STRIP_CONFIG(int[] msp_led_strip_config_items) {
        Debuger.checkDebugMode();
        this.msp_led_strip_config_items = msp_led_strip_config_items;
    }

    public MSP_LED_STRIP_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public String toString() {
        return "MSP_LED_STRIP_CONFIG{" + "msp_led_strip_config_items=" + Arrays.toString(msp_led_strip_config_items) + '}';
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_led_strip_config_items.length; i++) {
            msp_led_strip_config_items[i] = reader.readInt32();
        }
        reader.checkFinish();
    }
}
