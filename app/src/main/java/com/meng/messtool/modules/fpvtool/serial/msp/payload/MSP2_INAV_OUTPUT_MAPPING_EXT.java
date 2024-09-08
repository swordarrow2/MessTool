package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:40
 */
public class MSP2_INAV_OUTPUT_MAPPING_EXT implements IDecodeable {

    private static final String TAG = "MSP2_INAV_OUTPUT_MAPPING_EXT";

    public MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM[] msp2_inav_output_mapping_ext_items;

    public MSP2_INAV_OUTPUT_MAPPING_EXT(MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM[] msp2_inav_output_mapping_ext_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_output_mapping_ext_items = msp2_inav_output_mapping_ext_items;
    }

    public MSP2_INAV_OUTPUT_MAPPING_EXT(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        msp2_inav_output_mapping_ext_items = new MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM[data.length / 2];
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_output_mapping_ext_items.length; i++) {
            msp2_inav_output_mapping_ext_items[i] = new MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM(reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OUTPUT_MAPPING_EXT{" + "msp2_inav_output_mapping_ext_items=" + Arrays.toString(msp2_inav_output_mapping_ext_items) + '}';
    }

    public static class MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM {

        public int timerId;
        public int usageFlags;

        public MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM(int timerId, int usageFlags) {
            this.timerId = timerId;
            this.usageFlags = usageFlags;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_OUTPUT_MAPPING_EXT_ITEM{" + "timerId=" + timerId +
                    ", usageFlags=" + usageFlags +
                    '}';
        }
    }
}
