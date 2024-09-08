package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:00
 */
public class MSP_SET_HEAD implements IEncodeable {

    public int head;

    public MSP_SET_HEAD(int head) {
        this.head = head;
    }

    public MSP_SET_HEAD() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(head);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_HEAD{" + "head=" + head + '}';
    }
}
