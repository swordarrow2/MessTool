package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:24
 */
public class MSP_SET_SERVO_CONFIGURATION implements IEncodeable {

    public int index;
    public int min;
    public int max;
    public int middle;
    public int rate;
    @InavIgnore
    public int fixed_u8_0_1;
    @InavIgnore
    public int fixed_u8_0_2;
    @InavIgnore
    public int fixed_255;
    @InavIgnore
    public int fixed_u32_0;

    public MSP_SET_SERVO_CONFIGURATION(int index, int min, int max, int middle, int rate, int fixed_u8_0_1, int fixed_u8_0_2, int fixed_255, int fixed_u32_0) {
        this.index = index;
        this.min = min;
        this.max = max;
        this.middle = middle;
        this.rate = rate;
        this.fixed_u8_0_1 = fixed_u8_0_1;
        this.fixed_u8_0_2 = fixed_u8_0_2;
        this.fixed_255 = fixed_255;
        this.fixed_u32_0 = fixed_u32_0;
    }

    public MSP_SET_SERVO_CONFIGURATION() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint16(min)
                .writeUint16(max)
                .writeUint16(middle)
                .writeUint8(rate)
                .writeUint8(fixed_u8_0_1)
                .writeUint8(fixed_u8_0_2)
                .writeUint8(fixed_255)
                .writeInt32(fixed_u32_0);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_SERVO_CONFIGURATION{" + "index=" + index +
                ", min=" + min +
                ", max=" + max +
                ", middle=" + middle +
                ", rate=" + rate +
                ", fixed_u8_0_1=" + fixed_u8_0_1 +
                ", fixed_u8_0_2=" + fixed_u8_0_2 +
                ", fixed_255=" + fixed_255 +
                ", fixed_u32_0=" + fixed_u32_0 +
                '}';
    }
}
