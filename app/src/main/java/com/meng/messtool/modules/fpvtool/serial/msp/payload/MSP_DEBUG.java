package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:58
 */
public class MSP_DEBUG {

    public int debug1;
    public int debug2;
    public int debug3;
    public int debug4;

    public MSP_DEBUG(int debug1, int debug2, int debug3, int debug4) {
        this.debug1 = debug1;
        this.debug2 = debug2;
        this.debug3 = debug3;
        this.debug4 = debug4;
    }
}
