package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_RTC implements IEncodeable {

    public int secs;
    public int millis;

    public MSP_SET_RTC(int secs, int millis) {
        this.secs = secs;
        this.millis = millis;
    }

    public MSP_SET_RTC() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeInt32(secs)
                .writeUint16(millis);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RTC{" + "secs=" + secs +
                ", millis=" + millis +
                '}';
    }
}
