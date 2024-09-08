package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:41
 */
public class MSP_LED_STRIP_MODECOLOR implements IDecodeable {

    private static final String TAG = "MSP_LED_STRIP_MODECOLOR";

    public MSP_LED_STRIP_MODECOLOR_ITEM[][] msp_led_strip_modecolor_items = new MSP_LED_STRIP_MODECOLOR_ITEM[6][6];
    public LED_SPECIAL_COLOR[] led_special_colors = new LED_SPECIAL_COLOR[9];

    public MSP_LED_STRIP_MODECOLOR(MSP_LED_STRIP_MODECOLOR_ITEM[][] msp_led_strip_modecolor_items, LED_SPECIAL_COLOR[] led_special_colors) {
        Debuger.checkDebugMode();
        this.msp_led_strip_modecolor_items = msp_led_strip_modecolor_items;
        this.led_special_colors = led_special_colors;
    }

    public MSP_LED_STRIP_MODECOLOR(byte[] data) {
        decode(data);
    }

    @Override
    public String toString() {
        return "MSP_LED_STRIP_MODECOLOR{" + "msp_led_strip_modecolor_items=" + Arrays.toString(msp_led_strip_modecolor_items) + '}';
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_led_strip_modecolor_items.length; i++) {
            for (int ii = 0; ii < msp_led_strip_modecolor_items[i].length; ii++) {
                msp_led_strip_modecolor_items[i][ii] = new MSP_LED_STRIP_MODECOLOR_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint8());
            }
        }
        for (int i = 0; i < led_special_colors.length; i++) {
            led_special_colors[i] = new LED_SPECIAL_COLOR(reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    public static class MSP_LED_STRIP_MODECOLOR_ITEM {

        private static final String TAG = "LED_DIRECTION";

        public int i;
        public int j;
        public int color;

        public MSP_LED_STRIP_MODECOLOR_ITEM(int i, int j, int color) {
            this.i = i;
            this.j = j;
            this.color = color;
        }

        @Override
        public String toString() {
            return "LED_DIRECTION{" + "LED_MODE_COUNT=" + i +
                    ", j=" + j +
                    ", color=" + color +
                    '}';
        }
    }

    public static class LED_SPECIAL_COLOR {

        private static final String TAG = "LED_SPECIAL_COLOR";

        public int LED_MODE_COUNT;
        public int j;
        public int color;

        public LED_SPECIAL_COLOR(int LED_MODE_COUNT, int j, int color) {
            this.LED_MODE_COUNT = LED_MODE_COUNT;
            this.j = j;
            this.color = color;
        }

        @Override
        public String toString() {
            return "LED_SPECIAL_COLOR{" + "LED_MODE_COUNT=" + LED_MODE_COUNT +
                    ", j=" + j +
                    ", color=" + color +
                    '}';
        }
    }


}
