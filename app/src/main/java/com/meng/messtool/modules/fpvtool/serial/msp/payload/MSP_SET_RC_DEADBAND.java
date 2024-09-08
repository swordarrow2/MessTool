package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP_SET_RC_DEADBAND implements IEncodeable {

    public int deadband;
    public int yaw_deadband;
    public int alt_hold_deadband;
    public int mid_throttle_deadband;

    public MSP_SET_RC_DEADBAND(int deadband, int yaw_deadband, int alt_hold_deadband, int mid_throttle_deadband) {
        this.deadband = deadband;
        this.yaw_deadband = yaw_deadband;
        this.alt_hold_deadband = alt_hold_deadband;
        this.mid_throttle_deadband = mid_throttle_deadband;
    }

    public MSP_SET_RC_DEADBAND() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(deadband)
                .writeUint8(yaw_deadband)
                .writeUint8(alt_hold_deadband)
                .writeUint16(mid_throttle_deadband);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RC_DEADBAND{" + "deadband=" + deadband +
                ", yaw_deadband=" + yaw_deadband +
                ", alt_hold_deadband=" + alt_hold_deadband +
                ", mid_throttle_deadband=" + mid_throttle_deadband +
                '}';
    }
}
