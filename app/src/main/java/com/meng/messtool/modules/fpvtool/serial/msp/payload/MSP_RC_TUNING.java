package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:03
 */
public class MSP_RC_TUNING {

    public int _100;
    public int stabilizedRcExpo8;
    public int stabilizedRates1;
    public int stabilizedRates2;
    public int stabilizedRates3;
    public int throttleDynPID;
    public int throttleRcMid8;
    public int throttleRcExpo8;
    public int throttlePa_breakpoint;
    public int stabilizedRcYawExpo8;

    public MSP_RC_TUNING(int _100, int stabilizedRcExpo8, int stabilizedRates1, int stabilizedRates2, int stabilizedRates3, int throttleDynPID, int throttleRcMid8, int throttleRcExpo8, int throttlePa_breakpoint, int stabilizedRcYawExpo8) {
        this._100 = _100;
        this.stabilizedRcExpo8 = stabilizedRcExpo8;
        this.stabilizedRates1 = stabilizedRates1;
        this.stabilizedRates2 = stabilizedRates2;
        this.stabilizedRates3 = stabilizedRates3;
        this.throttleDynPID = throttleDynPID;
        this.throttleRcMid8 = throttleRcMid8;
        this.throttleRcExpo8 = throttleRcExpo8;
        this.throttlePa_breakpoint = throttlePa_breakpoint;
        this.stabilizedRcYawExpo8 = stabilizedRcYawExpo8;
    }
}
