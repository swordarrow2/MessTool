package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:43
 */
public class MSP_RC implements IDecodeable {

    private static final String TAG = "MSP_RC";

    public int[] rxGetChannelValue = new int[16];

    public MSP_RC(int[] rxGetChannelValue) {
        Debuger.checkDebugMode();
        this.rxGetChannelValue = rxGetChannelValue;
    }

    public MSP_RC(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < rxGetChannelValue.length; i++) {
            rxGetChannelValue[i] = reader.readUint16();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RC{" + "rxGetChannelValue=" + Arrays.toString(rxGetChannelValue) + '}';
    }
}
