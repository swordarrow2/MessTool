package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:48
 */
public class MSP_3D implements IDecodeable {

    private static final String TAG = "MSP_3D";

    public int deadband_low;
    public int deadband_high;
    public int neutral;

    public MSP_3D(int deadband_low, int deadband_high, int neutral) {
        Debuger.checkDebugMode();
        this.deadband_low = deadband_low;
        this.deadband_high = deadband_high;
        this.neutral = neutral;
    }

    public MSP_3D(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        deadband_low = reader.readUint16();
        deadband_high = reader.readUint16();
        neutral = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_3D{" + "deadband_low=" + deadband_low +
                ", deadband_high=" + deadband_high +
                ", neutral=" + neutral +
                '}';
    }
}
