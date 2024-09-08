package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:53
 */
public class MSP_ARMING_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_ARMING_CONFIG";

    public int zero;
    public int disarm_kill_switch;

    public MSP_ARMING_CONFIG(byte[] data) {
        decode(data);
    }

    public MSP_ARMING_CONFIG(int zero, int disarm_kill_switch) {
        Debuger.checkDebugMode();
        this.zero = zero;
        this.disarm_kill_switch = disarm_kill_switch;
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        zero = reader.readUint8();
        disarm_kill_switch = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ARMING_CONFIG{" + "min_throttle=" + zero +
                ", disarm_kill_switch=" + disarm_kill_switch +
                '}';
    }
}
