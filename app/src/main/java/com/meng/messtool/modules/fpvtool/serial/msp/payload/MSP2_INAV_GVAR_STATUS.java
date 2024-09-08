package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:38
 */
public class MSP2_INAV_GVAR_STATUS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_GVAR_STATUS";

    public int[] globalVariables = new int[8];

    public MSP2_INAV_GVAR_STATUS(int[] globalVariables) {
        Debuger.checkDebugMode();
        this.globalVariables = globalVariables;
    }

    public MSP2_INAV_GVAR_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < globalVariables.length; i++) {
            globalVariables[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_GVAR_STATUS{" + "globalVariables=" + Arrays.toString(globalVariables) + '}';
    }
}
