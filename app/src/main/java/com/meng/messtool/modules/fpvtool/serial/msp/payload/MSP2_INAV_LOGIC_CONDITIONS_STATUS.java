package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:37
 */
public class MSP2_INAV_LOGIC_CONDITIONS_STATUS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_LOGIC_CONDITIONS_STATUS";

    public int[] logicConditionGetValue = new int[64];

    public MSP2_INAV_LOGIC_CONDITIONS_STATUS(int[] logicConditionGetValue) {
        Debuger.checkDebugMode();
        this.logicConditionGetValue = logicConditionGetValue;
    }

    public MSP2_INAV_LOGIC_CONDITIONS_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < logicConditionGetValue.length; i++) {
            logicConditionGetValue[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_LOGIC_CONDITIONS_STATUS{" + "logicConditionGetValue=" + Arrays.toString(logicConditionGetValue) + '}';
    }
}
