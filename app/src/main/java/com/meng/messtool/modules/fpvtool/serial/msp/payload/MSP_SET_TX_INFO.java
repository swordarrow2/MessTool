package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_TX_INFO implements IEncodeable {

    public int getRSSISource;

    public MSP_SET_TX_INFO(int getRSSISource) {
        this.getRSSISource = getRSSISource;
    }

    public MSP_SET_TX_INFO() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(getRSSISource);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_TX_INFO{" + "getRSSISource=" + getRSSISource + '}';
    }
}
