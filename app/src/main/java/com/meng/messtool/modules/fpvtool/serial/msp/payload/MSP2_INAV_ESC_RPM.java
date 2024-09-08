package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:47
 */
public class MSP2_INAV_ESC_RPM implements IDecodeable {

    private static final String TAG = "MSP2_INAV_ESC_RPM";

    public int[] rpms;

    public MSP2_INAV_ESC_RPM(int[] rpms) {
        Debuger.checkDebugMode();
        this.rpms = rpms;
    }

    public MSP2_INAV_ESC_RPM(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        rpms = new int[data.length / 4];
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < rpms.length; i++) {
            rpms[i] = reader.readInt32();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_ESC_RPM{" + "rpms=" + Arrays.toString(rpms) + '}';
    }
}
