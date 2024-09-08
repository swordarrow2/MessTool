package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:01
 */
public class MSP_SET_MODE_RANGE implements IEncodeable {

    public int index;
    public int permanentId;
    public int auxChannelIndex;
    public int startStep;
    public int endStep;

    public MSP_SET_MODE_RANGE(int index, int permanentId, int auxChannelIndex, int startStep, int endStep) {
        this.index = index;
        this.permanentId = permanentId;
        this.auxChannelIndex = auxChannelIndex;
        this.startStep = startStep;
        this.endStep = endStep;
    }

    public MSP_SET_MODE_RANGE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(permanentId)
                .writeUint8(auxChannelIndex)
                .writeUint8(startStep)
                .writeUint8(endStep);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_MODE_RANGE{" + "index=" + index +
                ", permanentId=" + permanentId +
                ", auxChannelIndex=" + auxChannelIndex +
                ", startStep=" + startStep +
                ", endStep=" + endStep +
                '}';
    }
}
