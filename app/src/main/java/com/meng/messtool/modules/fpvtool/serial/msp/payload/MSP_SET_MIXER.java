package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_MIXER implements IEncodeable {

    @MspParam(note = "INAV7 mixerMode no longer supported, send 3 (QuadX) as fallback")
    public int mixerMode;

    public MSP_SET_MIXER(int mixerMode) {
        this.mixerMode = mixerMode;
    }

    public MSP_SET_MIXER() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(mixerMode);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_MIXER{" + "mixerMode=" + mixerMode + '}';
    }
}
