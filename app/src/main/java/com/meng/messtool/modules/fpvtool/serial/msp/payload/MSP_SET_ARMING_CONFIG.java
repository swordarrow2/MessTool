package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:00
 */
public class MSP_SET_ARMING_CONFIG implements IEncodeable {

    public boolean disarm_kill_switch;

    public MSP_SET_ARMING_CONFIG(boolean disarm_kill_switch) {
        this.disarm_kill_switch = disarm_kill_switch;
    }

    public MSP_SET_ARMING_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(disarm_kill_switch ? 1 : 0);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_ARMING_CONFIG{" + "disarm_kill_switch=" + disarm_kill_switch + '}';
    }
}
