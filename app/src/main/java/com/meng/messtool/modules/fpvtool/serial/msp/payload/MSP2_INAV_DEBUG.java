package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:00
 */
public class MSP2_INAV_DEBUG {

    public int debug1;
    public int debug2;
    public int debug3;
    public int debug4;
    public int debug5;
    public int debug6;
    public int debug7;
    public int debug8;

    public MSP2_INAV_DEBUG(int debug1, int debug2, int debug3, int debug4, int debug5, int debug6, int debug7, int debug8) {
        this.debug1 = debug1;
        this.debug2 = debug2;
        this.debug3 = debug3;
        this.debug4 = debug4;
        this.debug5 = debug5;
        this.debug6 = debug6;
        this.debug7 = debug7;
        this.debug8 = debug8;
    }
}
