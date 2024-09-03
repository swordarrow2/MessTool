package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:51
 */
public class MSP_NAV_STATUS {

    public int mode;
    public int state;
    public int activeWpAction;
    public int activeWpNumber;
    public int error;
    public int getHeadingHoldTarget;

    public MSP_NAV_STATUS(int mode, int state, int activeWpAction, int activeWpNumber, int error, int getHeadingHoldTarget) {
        this.mode = mode;
        this.state = state;
        this.activeWpAction = activeWpAction;
        this.activeWpNumber = activeWpNumber;
        this.error = error;
        this.getHeadingHoldTarget = getHeadingHoldTarget;
    }
}
