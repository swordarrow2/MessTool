package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:52
 */
public class MSP_GPSSVINFO {

    public int one;
    public int zero0;
    public int zero1;
    public int hdopDevide100_0;
    public int hdopDevide100_1;

    public MSP_GPSSVINFO(int one, int zero0, int zero1, int hdopDevide100_0, int hdopDevide100_1) {
        this.one = one;
        this.zero0 = zero0;
        this.zero1 = zero1;
        this.hdopDevide100_0 = hdopDevide100_0;
        this.hdopDevide100_1 = hdopDevide100_1;
    }
}
