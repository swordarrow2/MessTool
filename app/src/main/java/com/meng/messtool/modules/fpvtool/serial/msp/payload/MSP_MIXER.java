package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:05
 */
public class MSP_MIXER implements IDecodeable {

    private static final String TAG = "MSP_MIXER";
    @MspParam(note = "INAV7 mixerMode no longer supported, send 3 (QuadX) as fallback")
    public int mixerMode;

    public MSP_MIXER(int mixerMode) {
        Debuger.checkDebugMode();
        this.mixerMode = mixerMode;
    }

    public MSP_MIXER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        mixerMode = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_MIXER{" + "mixerMode=" + mixerMode + '}';
    }
}
