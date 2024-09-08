package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:02
 */
public class MSP_FEATURE implements IDecodeable {

    private static final String TAG = "MSP_FEATURE";

    public int featureMask;

    public MSP_FEATURE(int featureMask) {
        Debuger.checkDebugMode();
        this.featureMask = featureMask;
    }

    public MSP_FEATURE(byte[] data) {
        decode(data);
    }

    //todo feature mask
    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        featureMask = reader.readInt32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_FEATURE{" + "featureMask=" + featureMask + '}';
    }
}
