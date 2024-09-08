package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:12
 */
public class MSP_RTC implements IDecodeable {

    private static final String TAG = "MSP_RTC";

    public int seconds;
    public int millis;

    public MSP_RTC(int seconds, int millis) {
        Debuger.checkDebugMode();
        this.seconds = seconds;
        this.millis = millis;
    }

    public MSP_RTC(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        seconds = reader.readInt32();
        millis = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RTC{" + "seconds=" + seconds +
                ", millis=" + millis +
                '}';
    }
}
