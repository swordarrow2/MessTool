package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_RSSI_CONFIG implements IEncodeable {

    public int channel;

    public MSP_SET_RSSI_CONFIG(int channel) {
        this.channel = channel;
    }

    public MSP_SET_RSSI_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(channel);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RSSI_CONFIG{" + "channel=" + channel + '}';
    }
}
