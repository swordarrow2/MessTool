package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:43
 */
public class MSP_MOTOR_PINS implements IDecodeable {

    private static final String TAG = "MSP_MOTOR_PINS";

    public int[] pin = new int[8];

    public MSP_MOTOR_PINS(int[] pin) {
        Debuger.checkDebugMode();
        this.pin = pin;
    }

    public MSP_MOTOR_PINS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < pin.length; i++) {
            pin[i] = reader.readUint8();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_MOTOR_PINS{" + "pin=" + Arrays.toString(pin) + '}';
    }
}
