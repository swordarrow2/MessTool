package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:09
 */
public class MSP2_INAV_RATE_PROFILE {

    public int throttleRcMid8;
    public int throttleRcExpo8;
    public int throttleDynPID;
    public int throttlePa_breakpoint;
    public int stabilizedRcExpo8;
    public int stabilizedRcYawExpo8;
    public int stabilizedRates1;
    public int stabilizedRates2;
    public int stabilizedRates3;
    public int manualRcExpo8;
    public int manualRcYawExpo8;
    public int manualRates1;
    public int manualRates2;
    public int manualRates3;

    public MSP2_INAV_RATE_PROFILE(int throttleRcMid8, int throttleRcExpo8, int throttleDynPID, int throttlePa_breakpoint, int stabilizedRcExpo8, int stabilizedRcYawExpo8, int stabilizedRates1, int stabilizedRates2, int stabilizedRates3, int manualRcExpo8, int manualRcYawExpo8, int manualRates1, int manualRates2, int manualRates3) {
        this.throttleRcMid8 = throttleRcMid8;
        this.throttleRcExpo8 = throttleRcExpo8;
        this.throttleDynPID = throttleDynPID;
        this.throttlePa_breakpoint = throttlePa_breakpoint;
        this.stabilizedRcExpo8 = stabilizedRcExpo8;
        this.stabilizedRcYawExpo8 = stabilizedRcYawExpo8;
        this.stabilizedRates1 = stabilizedRates1;
        this.stabilizedRates2 = stabilizedRates2;
        this.stabilizedRates3 = stabilizedRates3;
        this.manualRcExpo8 = manualRcExpo8;
        this.manualRcYawExpo8 = manualRcYawExpo8;
        this.manualRates1 = manualRates1;
        this.manualRates2 = manualRates2;
        this.manualRates3 = manualRates3;
    }
}
