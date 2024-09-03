package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 19:52
 */
public class MSP_ADJUSTMENT_RANGES {
    public int adjustmentIndex;
    public int auxChannelIndex;
    public int rangeStartStep;
    public int rangeEndStep;
    public int adjustmentFunction;
    public int auxSwitchChannelIndex;

    public MSP_ADJUSTMENT_RANGES(int adjustmentIndex, int auxChannelIndex, int rangeStartStep, int rangeEndStep, int adjustmentFunction, int auxSwitchChannelIndex) {
        this.adjustmentIndex = adjustmentIndex;
        this.auxChannelIndex = auxChannelIndex;
        this.rangeStartStep = rangeStartStep;
        this.rangeEndStep = rangeEndStep;
        this.adjustmentFunction = adjustmentFunction;
        this.auxSwitchChannelIndex = auxSwitchChannelIndex;
    }
}
