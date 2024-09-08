package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:35
 */
public class MSP2_INAV_LED_STRIP_CONFIG_EX implements IDecodeable {

    private static final String TAG = "MSP2_INAV_LED_STRIP_CONFIG_EX";

    public MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[] msp2_inav_led_strip_config_ex_items = new MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[128];

    public MSP2_INAV_LED_STRIP_CONFIG_EX(MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM[] msp2_inav_led_strip_config_ex_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_led_strip_config_ex_items = msp2_inav_led_strip_config_ex_items;
    }

    public MSP2_INAV_LED_STRIP_CONFIG_EX(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_led_strip_config_ex_items.length; i++) {
            int led_position = reader.readUint8();
            int led_function = reader.readUint8();
            int led_overlay = reader.readUint8();
            int pak1 = reader.readUint8();
            int pak2 = reader.readUint8();
            int led_color = pak1 & 0b1111;
            int led_direction = ((((pak1 >>> 4) & 0b1111) << 2) | pak2 & 0b11) & 0b111111;
            int led_params = (pak2 >>> 2) & 0b111111;
            msp2_inav_led_strip_config_ex_items[i] = new MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM(led_position, led_function, led_overlay, led_color, led_direction, led_params);
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_LED_STRIP_CONFIG_EX{" + "msp2_inav_led_strip_config_ex_items=" + Arrays.toString(msp2_inav_led_strip_config_ex_items) + '}';
    }

    public class MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM {

        public int led_position;// 8bit
        public int led_function;// 8bit
        public int led_overlay;// 8bit
        public int led_color;// 4bit
        public int led_direction;// 6bit
        public int led_params;// 6bit

        public MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM(int led_position, int led_function, int led_overlay, int led_color, int led_direction, int led_params) {
            this.led_position = led_position;
            this.led_function = led_function;
            this.led_overlay = led_overlay;
            this.led_color = led_color;
            this.led_direction = led_direction;
            this.led_params = led_params;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_LED_STRIP_CONFIG_EX_ITEM{" + "led_position=" + led_position +
                    ", led_function=" + led_function +
                    ", led_overlay=" + led_overlay +
                    ", led_color=" + led_color +
                    ", led_direction=" + led_direction +
                    ", led_params=" + led_params +
                    '}';
        }
    }
}
