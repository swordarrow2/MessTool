package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP2_COMMON_SET_MOTOR_MIXER implements IEncodeable {

    public int index;
    public int throttle;
    public int roll;
    public int pitch;
    public int yaw;

    public MSP2_COMMON_SET_MOTOR_MIXER(int index, int throttle, int roll, int pitch, int yaw) {
        this.index = index;
        this.throttle = throttle;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public MSP2_COMMON_SET_MOTOR_MIXER() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint16(throttle)
                .writeUint16(roll)
                .writeUint16(pitch)
                .writeUint16(yaw);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_SET_MOTOR_MIXER{" + "index=" + index +
                ", throttle=" + throttle +
                ", roll=" + roll +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}
