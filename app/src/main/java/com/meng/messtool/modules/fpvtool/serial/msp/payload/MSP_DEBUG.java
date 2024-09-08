package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:58
 */
public class MSP_DEBUG implements IDecodeable {

    private static final String TAG = "MSP_DEBUG";

    public int[] debug = new int[4];

    public MSP_DEBUG(int[] debug) {
        Debuger.checkDebugMode();
        this.debug = debug;
    }

    public MSP_DEBUG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < debug.length; i++) {
            debug[i] = reader.readUint16();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_DEBUG{" + "debug=" + Arrays.toString(debug) + '}';
    }
}
