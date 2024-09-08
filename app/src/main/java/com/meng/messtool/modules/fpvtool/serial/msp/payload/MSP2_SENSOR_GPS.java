package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;


/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 10:34
 */
public class MSP2_SENSOR_GPS implements IDecodeable {

    public int instance; // sensor instance number to support multi-sensor setups
    public int gpsWeek; // GPS week, 0xFFFF if not available
    public long msTOW;
    public int fixType;
    public int satellitesInView;
    public int horizontalPosAccuracy; // [cm]
    public int verticalPosAccuracy;   // [cm]
    public int horizontalVelAccuracy; // [cm/s]
    public int hdop;
    public int longitude;
    public int latitude;
    public int mslAltitude; // cm
    public int nedVelNorth; // cm/s
    public int nedVelEast;
    public int nedVelDown;
    public int groundCourse; // deg * 100, 0..36000
    public int trueYaw;      // deg * 100, values of 0..36000 are valid. 65535 = no data available
    public int year;
    public int month;
    public int day;
    public int hour;
    public int min;
    public int sec;

    public MSP2_SENSOR_GPS(int instance, int gpsWeek, long msTOW, int fixType, int satellitesInView, int horizontalPosAccuracy, int verticalPosAccuracy, int horizontalVelAccuracy, int hdop, int longitude, int latitude, int mslAltitude, int nedVelNorth, int nedVelEast, int nedVelDown, int groundCourse, int trueYaw, int year, int month, int day, int hour, int min, int sec) {
        this.instance = instance;
        this.gpsWeek = gpsWeek;
        this.msTOW = msTOW;
        this.fixType = fixType;
        this.satellitesInView = satellitesInView;
        this.horizontalPosAccuracy = horizontalPosAccuracy;
        this.verticalPosAccuracy = verticalPosAccuracy;
        this.horizontalVelAccuracy = horizontalVelAccuracy;
        this.hdop = hdop;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mslAltitude = mslAltitude;
        this.nedVelNorth = nedVelNorth;
        this.nedVelEast = nedVelEast;
        this.nedVelDown = nedVelDown;
        this.groundCourse = groundCourse;
        this.trueYaw = trueYaw;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public MSP2_SENSOR_GPS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        instance = reader.readUint8();
        gpsWeek = reader.readUint16();
        msTOW = reader.readUint32();
        fixType = reader.readUint8();
        satellitesInView = reader.readUint8();
        horizontalPosAccuracy = reader.readUint16();
        verticalPosAccuracy = reader.readUint16();
        horizontalVelAccuracy = reader.readUint16();
        hdop = reader.readUint16();
        longitude = reader.readInt32();
        latitude = reader.readInt32();
        mslAltitude = reader.readInt32();
        nedVelNorth = reader.readInt32();
        nedVelEast = reader.readInt32();
        nedVelDown = reader.readInt32();
        groundCourse = reader.readUint16();
        trueYaw = reader.readUint16();
        year = reader.readUint16();
        month = reader.readUint8();
        day = reader.readUint8();
        hour = reader.readUint8();
        min = reader.readUint8();
        sec = reader.readUint8();
        reader.checkFinish();
    }
}
