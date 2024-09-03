package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:49
 */
public class MSP_COMP_GPS {

    public int GPS_distanceToHome;
    public int GPS_directionToHome;
    public int gpsHeartbeat;

    public MSP_COMP_GPS(int GPS_distanceToHome, int GPS_directionToHome, int gpsHeartbeat) {
        this.GPS_distanceToHome = GPS_distanceToHome;
        this.GPS_directionToHome = GPS_directionToHome;
        this.gpsHeartbeat = gpsHeartbeat;
    }
}
