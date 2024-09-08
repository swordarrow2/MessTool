package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:25
 */
public class MSP_RX_MAP implements IDecodeable {

    private static final String TAG = "MSP_RX_MAP";

    public int[] rcmap = new int[4];

    public MSP_RX_MAP(int[] rcmap) {
        Debuger.checkDebugMode();
        this.rcmap = rcmap;
    }

    public MSP_RX_MAP(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < rcmap.length; i++) {
            rcmap[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RX_MAP{" + "rcmap=" + Arrays.toString(rcmap) + '}';
    }
}
