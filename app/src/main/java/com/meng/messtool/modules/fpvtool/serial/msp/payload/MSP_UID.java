package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:01
 */
public class MSP_UID implements IDecodeable {

    private static final String TAG = "MSP_UID";

    public int[] U_ID = new int[3];

    public MSP_UID(int[] u_ID) {
        Debuger.checkDebugMode();
        U_ID = u_ID;
    }

    public MSP_UID(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < U_ID.length; i++) {
            U_ID[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_UID{" + "U_ID=" + Arrays.toString(U_ID) + '}';
    }
}
