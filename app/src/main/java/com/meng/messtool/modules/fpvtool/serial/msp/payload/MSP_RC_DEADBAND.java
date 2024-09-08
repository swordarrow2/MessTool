package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:49
 */
public class MSP_RC_DEADBAND implements IDecodeable {

    private static final String TAG = "MSP_RC_DEADBAND";

    public int deadband;
    public int yaw_deadband;
    public int alt_hold_deadband;
    public int mid_throttle_deadband;

    public MSP_RC_DEADBAND(int deadband, int yaw_deadband, int alt_hold_deadband, int mid_throttle_deadband) {
        Debuger.checkDebugMode();
        this.deadband = deadband;
        this.yaw_deadband = yaw_deadband;
        this.alt_hold_deadband = alt_hold_deadband;
        this.mid_throttle_deadband = mid_throttle_deadband;
    }

    public MSP_RC_DEADBAND(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        deadband = reader.readUint8();
        yaw_deadband = reader.readUint8();
        alt_hold_deadband = reader.readUint8();
        mid_throttle_deadband = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RC_DEADBAND{" + "deadband=" + deadband +
                ", yaw_deadband=" + yaw_deadband +
                ", alt_hold_deadband=" + alt_hold_deadband +
                ", mid_throttle_deadband=" + mid_throttle_deadband +
                '}';
    }
}
