package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:12
 */
public class MSP_ATTITUDE implements IDecodeable {

    private static final String TAG = "MSP_ATTITUDE";

    public int roll;
    public int pitch;
    public int yaw;

    public MSP_ATTITUDE(int roll, int pitch, int yaw) {
        Debuger.checkDebugMode();
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public MSP_ATTITUDE(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        roll = reader.readInt16();
        pitch = reader.readInt16();
        yaw = reader.readInt16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ATTITUDE{" + "roll=" + roll +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}
