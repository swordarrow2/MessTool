package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP_SET_3D implements IEncodeable {

    public int deadband_low;
    public int deadband_high;
    public int neutral;

    public MSP_SET_3D(int deadband_low, int deadband_high, int neutral) {
        this.deadband_low = deadband_low;
        this.deadband_high = deadband_high;
        this.neutral = neutral;
    }

    public MSP_SET_3D() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(deadband_low)
                .writeUint16(deadband_high)
                .writeUint16(neutral);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_3D{" + "deadband_low=" + deadband_low +
                ", deadband_high=" + deadband_high +
                ", neutral=" + neutral +
                '}';
    }
}
