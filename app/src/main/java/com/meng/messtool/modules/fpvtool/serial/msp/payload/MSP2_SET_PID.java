package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:01
 */
public class MSP2_SET_PID implements IEncodeable {

    public MSP2_PID.MSP2_PID_ITEM[] msp2_pid_items = new MSP2_PID.MSP2_PID_ITEM[9];

    public MSP2_SET_PID(MSP2_PID.MSP2_PID_ITEM[] msp2_pid_items) {
        this.msp2_pid_items = msp2_pid_items;
    }

    public MSP2_SET_PID() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (MSP2_PID.MSP2_PID_ITEM item : msp2_pid_items) {
            writer.writeUint8(item.p)
                    .writeUint8(item.i)
                    .writeUint8(item.d)
                    .writeUint8(item.ff);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_SET_PID{" + "msp2_pid_items=" + Arrays.toString(msp2_pid_items) + '}';
    }
}
