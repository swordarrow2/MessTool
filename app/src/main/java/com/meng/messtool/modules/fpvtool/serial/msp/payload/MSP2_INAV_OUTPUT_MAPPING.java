package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:37
 */
public class MSP2_INAV_OUTPUT_MAPPING implements IDecodeable {

    private static final String TAG = "MSP2_INAV_OUTPUT_MAPPING";

    public MSP2_INAV_OUTPUT_MAPPING_ITEM[] msp2_inav_output_mapping_items;

    public MSP2_INAV_OUTPUT_MAPPING(MSP2_INAV_OUTPUT_MAPPING_ITEM[] msp2_inav_output_mapping_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_output_mapping_items = msp2_inav_output_mapping_items;
    }

    public MSP2_INAV_OUTPUT_MAPPING(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_output_mapping_items.length; i++) {
            msp2_inav_output_mapping_items[i] = new MSP2_INAV_OUTPUT_MAPPING_ITEM(reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OUTPUT_MAPPING{" + "msp2_inav_output_mapping_items=" + Arrays.toString(msp2_inav_output_mapping_items) + '}';
    }

    public class MSP2_INAV_OUTPUT_MAPPING_ITEM {

        public int usageFlags;

        public MSP2_INAV_OUTPUT_MAPPING_ITEM(int usageFlags) {
            this.usageFlags = usageFlags;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_OUTPUT_MAPPING_ITEM{" + "usageFlags=" + usageFlags + '}';
        }
    }
}
