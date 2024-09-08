package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_COMMON_SET_TZ implements IEncodeable {

    public int tz_offset;
    public int tz_automatic_dst = Integer.MAX_VALUE;

    public MSP2_COMMON_SET_TZ(int tz_offset) {
        this.tz_offset = tz_offset;
    }

    public MSP2_COMMON_SET_TZ(int tz_offset, int tz_automatic_dst) {
        this.tz_offset = tz_offset;
        this.tz_automatic_dst = tz_automatic_dst;
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeInt16(tz_offset);
        if (tz_automatic_dst != Integer.MAX_VALUE) {
            writer.writeUint8(tz_automatic_dst);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_SET_TZ{" + "tz_offset=" + tz_offset +
                ", tz_automatic_dst=" + tz_automatic_dst +
                '}';
    }
}
