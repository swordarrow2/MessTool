package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:52
 */
public class MSP2_PID {

    /* PID              MC      FW
    PID_ROLL,       //   +       +
    PID_PITCH,      //   +       +
    PID_YAW,        //   +       +
    PID_POS_Z,      //   +       +
    PID_POS_XY,     //   +       +
    PID_VEL_XY,     //   +       n/a
    PID_SURFACE,    //   n/a     n/a
    PID_LEVEL,      //   +       +
    PID_HEADING,    //   +       +
    PID_VEL_Z,      //   +       n/a
    PID_POS_HEADING,//   n/a     +*/

    public int p;
    public int i;
    public int d;
    public int ff;

    public MSP2_PID(int p, int i, int d, int ff) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.ff = ff;
    }

}
