package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:04
 */
public class MSP_CURRENT_METER_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_CURRENT_METER_CONFIG";

    public int scale;
    public int offset;
    public int type;
    public int capacity;

    public MSP_CURRENT_METER_CONFIG(int scale, int offset, int type, int capacity) {
        Debuger.checkDebugMode();
        this.scale = scale;
        this.offset = offset;
        this.type = type;
        this.capacity = capacity;
    }

    public MSP_CURRENT_METER_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        scale = reader.readInt16();
        offset = reader.readInt16();
        type = reader.readUint8();
        capacity = reader.readInt32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_CURRENT_METER_CONFIG{" + "scale=" + scale +
                ", offset=" + offset +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }
}
