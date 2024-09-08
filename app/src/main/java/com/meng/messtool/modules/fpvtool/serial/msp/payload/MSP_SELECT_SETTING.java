package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 16:58
 */
public class MSP_SELECT_SETTING implements IEncodeable {

    public int index;

    public MSP_SELECT_SETTING(int index) {
        this.index = index;
    }

    public MSP_SELECT_SETTING() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SELECT_SETTING{" + "index=" + index + '}';
    }
}
