package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:02
 */
public class MSP_BOARD_ALIGNMENT {

    public int rollDeciDegrees;
    public int pitchDeciDegrees;
    public int yawDeciDegrees;

    public MSP_BOARD_ALIGNMENT(int rollDeciDegrees, int pitchDeciDegrees, int yawDeciDegrees) {
        this.rollDeciDegrees = rollDeciDegrees;
        this.pitchDeciDegrees = pitchDeciDegrees;
        this.yawDeciDegrees = yawDeciDegrees;
    }
}
