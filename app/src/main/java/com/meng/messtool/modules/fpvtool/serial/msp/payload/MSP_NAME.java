package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:17
 */
public class MSP_NAME implements IDecodeable {

    private static final String TAG = "MSP_NAME";

    public String craftName;

    public MSP_NAME(String craftName) {
        Debuger.checkDebugMode();
        this.craftName = craftName;
    }

    public MSP_NAME(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        craftName = reader.readCppString(16);
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_NAME{" + "craftName='" + craftName + '\'' + '}';
    }
}
