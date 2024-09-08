package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:44
 */
public class MSP_LOOP_TIME implements IDecodeable {

    private static final String TAG = "MSP_LOOP_TIME";

    public int looptime;

    public MSP_LOOP_TIME(int looptime) {
        Debuger.checkDebugMode();
        this.looptime = looptime;
    }

    public MSP_LOOP_TIME(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        looptime = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_LOOP_TIME{" + "looptime=" + looptime + '}';
    }
}
