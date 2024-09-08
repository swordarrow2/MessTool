package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:09
 */
public class MSP_BOXIDS implements IDecodeable {

    private static final String TAG = "MSP_BOXIDS";

    public int[] permanentId;

    public MSP_BOXIDS(int[] permanentId) {
        Debuger.checkDebugMode();
        this.permanentId = permanentId;
    }

    public MSP_BOXIDS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        permanentId = new int[data.length];
        for (int i = 0; i < permanentId.length; i++) {
            permanentId[i] = reader.readUint8();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_BOXIDS{" + "permanentId=" + Arrays.toString(permanentId) + '}';
    }
}
