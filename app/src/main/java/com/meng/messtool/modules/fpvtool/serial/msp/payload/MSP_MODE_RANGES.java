package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 19:47
 */
public class MSP_MODE_RANGES {

    public int permanentId;
    public int auxChannelIndex;
    public int startStep;
    public int endStep;

    public MSP_MODE_RANGES(int permanentId, int auxChannelIndex, int startStep, int endStep) {
        this.permanentId = permanentId;
        this.auxChannelIndex = auxChannelIndex;
        this.startStep = startStep;
        this.endStep = endStep;
    }
}
