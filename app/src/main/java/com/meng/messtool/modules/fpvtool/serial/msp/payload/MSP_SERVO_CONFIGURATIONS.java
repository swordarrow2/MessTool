package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:21
 */
public class MSP_SERVO_CONFIGURATIONS implements IDecodeable {

    private static final String TAG = "MSP_SERVO_CONFIGURATION";

    public MSP_SERVO_CONFIGURATIONS_ITEM[] msp_servo_configurations_items = new MSP_SERVO_CONFIGURATIONS_ITEM[16];

    public MSP_SERVO_CONFIGURATIONS(MSP_SERVO_CONFIGURATIONS_ITEM[] msp_servo_configurations_items) {
        Debuger.checkDebugMode();
        this.msp_servo_configurations_items = msp_servo_configurations_items;
    }

    public MSP_SERVO_CONFIGURATIONS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_servo_configurations_items.length; i++) {
            msp_servo_configurations_items[i] = new MSP_SERVO_CONFIGURATIONS_ITEM(reader.readInt16(), reader.readInt16(), reader.readInt16(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readInt32());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SERVO_CONFIGURATIONS{" + "msp_servo_configurations_items=" + Arrays.toString(msp_servo_configurations_items) + '}';
    }

    public static class MSP_SERVO_CONFIGURATIONS_ITEM {

        public int min;
        public int max;
        public int middle;
        public int rate;
        @InavIgnore
        public int fixed_u8_0_1;
        @InavIgnore
        public int fixed_u8_0_2;
        @InavIgnore
        public int fixed_255;
        @InavIgnore
        public int fixed_u32_0;

        public MSP_SERVO_CONFIGURATIONS_ITEM(int min, int max, int middle, int rate, int fixed_u8_0_1, int fixed_u8_0_2, int fixed_255, int fixed_u32_0) {
            this.min = min;
            this.max = max;
            this.middle = middle;
            this.rate = rate;
            this.fixed_u8_0_1 = fixed_u8_0_1;
            this.fixed_u8_0_2 = fixed_u8_0_2;
            this.fixed_255 = fixed_255;
            this.fixed_u32_0 = fixed_u32_0;
        }

        @Override
        public String toString() {
            return "MSP_SERVO_CONFIGURATIONS_ITEM{" + "min=" + min +
                    ", max=" + max +
                    ", middle=" + middle +
                    ", rate=" + rate +
                    ", fixed_u8_0_1=" + fixed_u8_0_1 +
                    ", fixed_u8_0_2=" + fixed_u8_0_2 +
                    ", fixed_255=" + fixed_255 +
                    ", fixed_u32_0=" + fixed_u32_0 +
                    '}';
        }
    }
}
