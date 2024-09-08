package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:00
 */
public class MSP2_INAV_DEBUG implements IDecodeable {

    private static final String TAG = "MSP2_INAV_DEBUG";

    public int[] debug = new int[8];

    public MSP2_INAV_DEBUG(int[] debug) {
        Debuger.checkDebugMode();
        this.debug = debug;
    }

    public MSP2_INAV_DEBUG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < debug.length; i++) {
            debug[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_DEBUG{" + "debug=" + Arrays.toString(debug) + '}';
    }
}
