package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:28
 */
public class MSP_ALTITUDE {

    public int getEstimatedActualPosition;
    public int getEstimatedActualVelocity;
    public int baroGetLatestAltitude;

    public MSP_ALTITUDE(int getEstimatedActualPosition, int getEstimatedActualVelocity, int baroGetLatestAltitude) {
        this.getEstimatedActualPosition = getEstimatedActualPosition;
        this.getEstimatedActualVelocity = getEstimatedActualVelocity;
        this.baroGetLatestAltitude = baroGetLatestAltitude;
    }
}
