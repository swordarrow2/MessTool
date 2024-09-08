package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:24
 */
public class MSP_RSSI_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_RSSI_CONFIG";

    public int rssi_channel;

    public MSP_RSSI_CONFIG(int rssi_channel) {
        Debuger.checkDebugMode();
        this.rssi_channel = rssi_channel;
    }

    public MSP_RSSI_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        rssi_channel = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RSSI_CONFIG{" + "rssi_channel=" + rssi_channel + '}';
    }
}
