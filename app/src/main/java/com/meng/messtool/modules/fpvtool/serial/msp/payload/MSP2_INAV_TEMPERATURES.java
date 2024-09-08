package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:46
 */
public class MSP2_INAV_TEMPERATURES implements IDecodeable {

    private static final String TAG = "MSP2_INAV_TEMPERATURES";

    public int[] msp2_inav_temperatures_items = new int[8];

    public MSP2_INAV_TEMPERATURES(int[] msp2_inav_temperatures_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_temperatures_items = msp2_inav_temperatures_items;
    }

    public MSP2_INAV_TEMPERATURES(byte[] data) {
        decode(data);
    }

    @Override
    public String toString() {
        return "MSP2_INAV_TEMPERATURES{" + "msp2_inav_temperatures_items=" + Arrays.toString(msp2_inav_temperatures_items) + '}';
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_temperatures_items.length; i++) {
            msp2_inav_temperatures_items[i] = reader.readInt16();
        }
        reader.checkFinish();
    }
}
