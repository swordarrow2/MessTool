package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:00
 */
public class MSP_SET_RAW_RC implements IEncodeable {

    public int[] rxGetChannelValue = new int[16];

    public MSP_SET_RAW_RC(int[] rxGetChannelValue) {
        this.rxGetChannelValue = rxGetChannelValue;
    }

    public MSP_SET_RAW_RC() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (int value : rxGetChannelValue) {
            writer.writeUint16(value);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RAW_RC{" + "rxGetChannelValue=" + Arrays.toString(rxGetChannelValue) + '}';
    }
}
