package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:48
 */
public class MSP_RAW_GPS {

    public int fixType;
    public int numSat;
    public int lat;
    public int lon;
    public int altMeters;
    public int groundSpeed;
    public int groundCourse;
    public int hdop;

    public MSP_RAW_GPS(int fixType, int numSat, int lat, int lon, int altMeters, int groundSpeed, int groundCourse, int hdop) {
        this.fixType = fixType;
        this.numSat = numSat;
        this.lat = lat;
        this.lon = lon;
        this.altMeters = altMeters;
        this.groundSpeed = groundSpeed;
        this.groundCourse = groundCourse;
        this.hdop = hdop;
    }
}
