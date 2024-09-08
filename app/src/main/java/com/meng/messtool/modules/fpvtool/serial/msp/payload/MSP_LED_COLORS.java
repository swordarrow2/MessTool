package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:31
 */
public class MSP_LED_COLORS implements IDecodeable {

    private static final String TAG = "MSP_LED_COLORS";

    public MSP_LED_COLORS_ITEM[] msp_led_colors_items = new MSP_LED_COLORS_ITEM[16];

    public MSP_LED_COLORS(MSP_LED_COLORS_ITEM[] msp_led_colors_items) {
        Debuger.checkDebugMode();
        this.msp_led_colors_items = msp_led_colors_items;
    }

    public MSP_LED_COLORS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_led_colors_items.length; i++) {
            msp_led_colors_items[i] = new MSP_LED_COLORS_ITEM(reader.readUint16(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_LED_COLORS{" + "msp_led_colors_items=" + Arrays.toString(msp_led_colors_items) + '}';
    }

    public static class MSP_LED_COLORS_ITEM {

        public int h;
        public int s;
        public int v;

        public MSP_LED_COLORS_ITEM(int h, int s, int v) {
            this.h = h;
            this.s = s;
            this.v = v;
        }

        @Override
        public String toString() {
            return "MSP_LED_COLORS_ITEM{" + "h=" + h +
                    ", s=" + s +
                    ", v=" + v +
                    '}';
        }
    }
}
