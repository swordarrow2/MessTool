package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 9:12
 */
public class MSP_REBOOT implements IDecodeable {

    private static final String TAG = "MSP_REBOOT";


    public MSP_REBOOT(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
    }

    @Override
    public String toString() {
        return "MSP_REBOOT{}";
    }
}
