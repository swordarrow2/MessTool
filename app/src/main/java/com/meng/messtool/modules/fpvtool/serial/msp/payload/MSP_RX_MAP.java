package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:25
 */
public class MSP_RX_MAP {

    public int rcmap1;
    public int rcmap2;
    public int rcmap3;
    public int rcmap4;

    public MSP_RX_MAP(int rcmap1, int rcmap2, int rcmap3, int rcmap4) {
        this.rcmap1 = rcmap1;
        this.rcmap2 = rcmap2;
        this.rcmap3 = rcmap3;
        this.rcmap4 = rcmap4;
    }
}
