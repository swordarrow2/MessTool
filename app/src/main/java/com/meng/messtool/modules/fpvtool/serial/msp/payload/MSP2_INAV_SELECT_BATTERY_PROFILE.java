package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:33
 */
public class MSP2_INAV_SELECT_BATTERY_PROFILE implements IEncodeable {

    public int index;

    public MSP2_INAV_SELECT_BATTERY_PROFILE(int index) {
        this.index = index;
    }

    public MSP2_INAV_SELECT_BATTERY_PROFILE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SELECT_BATTERY_PROFILE{" + "index=" + index + '}';
    }
}
