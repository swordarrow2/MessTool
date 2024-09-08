package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:24
 */
public class MSP_SET_MOTOR implements IEncodeable {

    public int[] motor = new int[8];

    public MSP_SET_MOTOR(int[] motor) {
        this.motor = motor;
    }

    public MSP_SET_MOTOR() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (int m : motor) {
            writer.writeUint8(m);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_MOTOR{" + "motor=" + Arrays.toString(motor) + '}';
    }
}
