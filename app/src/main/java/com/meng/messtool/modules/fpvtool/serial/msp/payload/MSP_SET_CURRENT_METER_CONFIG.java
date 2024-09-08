package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_CURRENT_METER_CONFIG implements IEncodeable {

    public int scale;
    public int offset;
    public int type;
    public int capacity;

    public MSP_SET_CURRENT_METER_CONFIG(int scale, int offset, int type, int capacity) {
        this.scale = scale;
        this.offset = offset;
        this.type = type;
        this.capacity = capacity;
    }

    public MSP_SET_CURRENT_METER_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(scale)
                .writeUint16(offset)
                .writeUint8(type)
                .writeUint16(capacity);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_CURRENT_METER_CONFIG{" + "scale=" + scale +
                ", offset=" + offset +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }
}
