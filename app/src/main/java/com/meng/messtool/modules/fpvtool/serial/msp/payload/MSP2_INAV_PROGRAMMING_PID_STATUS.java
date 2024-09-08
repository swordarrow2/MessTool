package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:40
 */
public class MSP2_INAV_PROGRAMMING_PID_STATUS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_PROGRAMMING_PID_STATUS";

    public int[] programmingPidGetOutput = new int[4];

    public MSP2_INAV_PROGRAMMING_PID_STATUS(int[] programmingPidGetOutput) {
        Debuger.checkDebugMode();
        this.programmingPidGetOutput = programmingPidGetOutput;
    }

    public MSP2_INAV_PROGRAMMING_PID_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < programmingPidGetOutput.length; i++) {
            programmingPidGetOutput[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_PROGRAMMING_PID_STATUS{" + "programmingPidGetOutput=" + Arrays.toString(programmingPidGetOutput) + '}';
    }
}
