package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:00
 */
public class MSP_SERVO implements IDecodeable {

    private static final String TAG = "MSP_SERVO";

    public int[] servos = new int[16];

    public MSP_SERVO(int[] servos) {
        Debuger.checkDebugMode();
        this.servos = servos;
    }

    public MSP_SERVO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < servos.length; i++) {
            servos[i] = reader.readUint16();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SERVO{" + "servos=" + Arrays.toString(servos) + '}';
    }
}
