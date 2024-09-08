package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:23
 */
public class MSP_SET_ADJUSTMENT_RANGE implements IEncodeable {
    @MspParam(max = 19)
    public int rangeIndex;
    @MspParam(max = 3)
    public int adjustmentIndex;
    public int auxChannelIndex;
    // steps are 25 apart
    // a value of 0 corresponds to a channel value of 900 or less
    // a value of 48 corresponds to a channel value of 2100 or more
    // 48 steps between 900 and 1200
    public int rangeStartStep;
    public int rangeEndStep;

    public int adjustmentFunction;
    public int auxSwitchChannelIndex;

    public MSP_SET_ADJUSTMENT_RANGE(int rangeIndex, int adjustmentIndex, int auxChannelIndex, int rangeStartStep, int rangeEndStep, int adjustmentFunction, int auxSwitchChannelIndex) {
        this.rangeIndex = rangeIndex;
        this.adjustmentIndex = adjustmentIndex;
        this.auxChannelIndex = auxChannelIndex;
        this.rangeStartStep = rangeStartStep;
        this.rangeEndStep = rangeEndStep;
        this.adjustmentFunction = adjustmentFunction;
        this.auxSwitchChannelIndex = auxSwitchChannelIndex;
    }

    public MSP_SET_ADJUSTMENT_RANGE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(rangeIndex)
                .writeUint8(adjustmentIndex)
                .writeUint8(auxChannelIndex)
                .writeUint8(rangeStartStep)
                .writeUint8(rangeEndStep)
                .writeUint8(adjustmentFunction)
                .writeUint8(auxSwitchChannelIndex);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_ADJUSTMENT_RANGE{" + "rangeIndex=" + rangeIndex +
                ", adjustmentIndex=" + adjustmentIndex +
                ", auxChannelIndex=" + auxChannelIndex +
                ", rangeStartStep=" + rangeStartStep +
                ", rangeEndStep=" + rangeEndStep +
                ", adjustmentFunction=" + adjustmentFunction +
                ", auxSwitchChannelIndex=" + auxSwitchChannelIndex +
                '}';
    }
}
