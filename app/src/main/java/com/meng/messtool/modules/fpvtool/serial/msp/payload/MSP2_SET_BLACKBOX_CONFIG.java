package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:28
 */
public class MSP2_SET_BLACKBOX_CONFIG implements IEncodeable {

    public int device;
    public int rate_num;
    public int rate_denom;
    public int includeFlags;

    public MSP2_SET_BLACKBOX_CONFIG(int device, int rate_num, int rate_denom, int includeFlags) {
        this.device = device;
        this.rate_num = rate_num;
        this.rate_denom = rate_denom;
        this.includeFlags = includeFlags;
    }

    public MSP2_SET_BLACKBOX_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(device)
                .writeUint16(rate_num)
                .writeUint16(rate_denom)
                .writeInt32(includeFlags);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_SET_BLACKBOX_CONFIG{" + "device=" + device +
                ", rate_num=" + rate_num +
                ", rate_denom=" + rate_denom +
                ", includeFlags=" + includeFlags +
                '}';
    }
}
