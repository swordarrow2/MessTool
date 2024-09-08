package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:03
 */
public class MSP_ACTIVEBOXES implements IDecodeable {

    private static final String TAG = "MSP_ACTIVEBOXES";

    public long mspBoxModeFlags;

    public MSP_ACTIVEBOXES(long mspBoxModeFlags) {
        Debuger.checkDebugMode();
        this.mspBoxModeFlags = mspBoxModeFlags;
    }

    public MSP_ACTIVEBOXES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        mspBoxModeFlags = reader.readInt64();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ACTIVEBOXES{" + "mspBoxModeFlags=" + mspBoxModeFlags + '}';
    }
}
