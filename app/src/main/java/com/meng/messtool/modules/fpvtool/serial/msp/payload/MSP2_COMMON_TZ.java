package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:18
 */
public class MSP2_COMMON_TZ implements IDecodeable {

    private static final String TAG = "MSP2_COMMON_TZ";

    public int tz_offset;
    public int tz_automatic_dst;

    public MSP2_COMMON_TZ(int tz_offset, int tz_automatic_dst) {
        Debuger.checkDebugMode();
        this.tz_offset = tz_offset;
        this.tz_automatic_dst = tz_automatic_dst;
    }

    public MSP2_COMMON_TZ(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        tz_offset = reader.readUint16();
        tz_automatic_dst = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_TZ{" + "tz_offset=" + tz_offset +
                ", tz_automatic_dst=" + tz_automatic_dst +
                '}';
    }
}
