package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_SET_FEATURE implements IEncodeable {

    public int featureSet;

    public MSP_SET_FEATURE(int featureSet) {
        this.featureSet = featureSet;
    }

    public MSP_SET_FEATURE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeInt32(featureSet);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_FEATURE{" + "featureSet=" + featureSet + '}';
    }
}
