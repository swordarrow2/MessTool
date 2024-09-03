package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:01
 */
public class MSP_UID {

    public int U_ID_0;
    public int U_ID_1;
    public int U_ID_2;

    public MSP_UID(int u_ID_0, int u_ID_1, int u_ID_2) {
        U_ID_0 = u_ID_0;
        U_ID_1 = u_ID_1;
        U_ID_2 = u_ID_2;
    }
}
