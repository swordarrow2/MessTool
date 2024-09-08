package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:43
 */
public class MSP_MOTOR implements IDecodeable {

    private static final String TAG = "MSP_MOTOR";

    public int[] motor = new int[8];

    public MSP_MOTOR(int[] motor) {
        Debuger.checkDebugMode();
        this.motor = motor;
    }

    public MSP_MOTOR(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < motor.length; i++) {
            motor[i] = reader.readUint8();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_MOTOR{" + "motor=" + Arrays.toString(motor) + '}';
    }
}
