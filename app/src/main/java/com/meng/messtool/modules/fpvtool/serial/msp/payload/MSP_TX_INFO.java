package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:12
 */
public class MSP_TX_INFO implements IDecodeable {

    private static final String TAG = "MSP_TX_INFO";

    public int getRSSISource;
    public int rtcDateTimeIsSet;

    public MSP_TX_INFO(int getRSSISource, int rtcDateTimeIsSet) {
        Debuger.checkDebugMode();
        this.getRSSISource = getRSSISource;
        this.rtcDateTimeIsSet = rtcDateTimeIsSet;
    }

    public MSP_TX_INFO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        getRSSISource = reader.readUint8();
        rtcDateTimeIsSet = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_TX_INFO{" + "getRSSISource=" + getRSSISource +
                ", rtcDateTimeIsSet=" + rtcDateTimeIsSet +
                '}';
    }
}
