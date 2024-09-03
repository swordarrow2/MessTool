package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:57
 */
public class MSP_GPSSTATISTICS {

    public int lastMessageDt;
    public int errors;
    public int timeouts;
    public int packetCount;
    public int hdop;
    public int eph;
    public int epv;

    public MSP_GPSSTATISTICS(int lastMessageDt, int errors, int timeouts, int packetCount, int hdop, int eph, int epv) {
        this.lastMessageDt = lastMessageDt;
        this.errors = errors;
        this.timeouts = timeouts;
        this.packetCount = packetCount;
        this.hdop = hdop;
        this.eph = eph;
        this.epv = epv;
    }
}
