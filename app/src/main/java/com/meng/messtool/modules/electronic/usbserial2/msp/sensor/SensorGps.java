package com.meng.messtool.modules.electronic.usbserial2.msp.sensor;

import com.meng.messtool.modules.electronic.usbserial2.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp.sensor
 *@author  清梦
 *@date    2024/8/20 16:38
 */
public class SensorGps implements MspSeriallizeable<SensorGps> {
    private int instance_uint8; // sensor instance number to support multi-sensor setups
    private int gpsWeek_uint16; // GPS week, 0xFFFF if not available
    private long msTOW_uint32;
    private int fixType_uint8;
    private int satellitesInView_uint8;
    private int horizontalPosAccuracy_uint16; // [cm]
    private int verticalPosAccuracy_uint16;   // [cm]
    private int horizontalVelAccuracy_uint16; // [cm/s]
    private int hdop_uint16;
    private int longitude_int32;
    private int latitude_int32;
    private int mslAltitude_int32; // cm
    private int nedVelNorth_int32; // cm/s
    private int nedVelEast_int32;
    private int nedVelDown_int32;
    private int groundCourse_uint16; // deg * 100, 0..36000
    private int trueYaw_uint16;      // deg * 100, values of 0..36000 are valid. 65535 = no data available
    private int year_uint16;
    private int month_uint8;
    private int day_uint8;
    private int hour_uint8;
    private int min_uint8;
    private int sec_uint8;

    public int getInstance_uint8() {
        return instance_uint8;
    }

    public void setInstance_uint8(int instance_uint8) {
        this.instance_uint8 = instance_uint8 & 0xFF;
    }

    public int getGpsWeek_uint16() {
        return gpsWeek_uint16;
    }

    public void setGpsWeek_uint16(int gpsWeek_uint16) {
        this.gpsWeek_uint16 = gpsWeek_uint16 & 0xFFFF;
    }

    public long getMsTOW_uint32() {
        return msTOW_uint32;
    }

    public void setMsTOW_uint32(long msTOW_uint32) {
        this.msTOW_uint32 = msTOW_uint32 & 0xFFFFFFFFL;
    }

    public int getFixType_uint8() {
        return fixType_uint8;
    }

    public void setFixType_uint8(int fixType_uint8) {
        this.fixType_uint8 = fixType_uint8 & 0xFF;
    }

    public int getSatellitesInView_uint8() {
        return satellitesInView_uint8;
    }

    public void setSatellitesInView_uint8(int satellitesInView_uint8) {
        this.satellitesInView_uint8 = satellitesInView_uint8 & 0xFF;
    }

    public int getHorizontalPosAccuracy_uint16() {
        return horizontalPosAccuracy_uint16;
    }

    public void setHorizontalPosAccuracy_uint16(int horizontalPosAccuracy_uint16) {
        this.horizontalPosAccuracy_uint16 = horizontalPosAccuracy_uint16 & 0xFFFF;
    }

    public int getVerticalPosAccuracy_uint16() {
        return verticalPosAccuracy_uint16;
    }

    public void setVerticalPosAccuracy_uint16(int verticalPosAccuracy_uint16) {
        this.verticalPosAccuracy_uint16 = verticalPosAccuracy_uint16 & 0xFFFF;
    }

    public int getHorizontalVelAccuracy_uint16() {
        return horizontalVelAccuracy_uint16;
    }

    public void setHorizontalVelAccuracy_uint16(int horizontalVelAccuracy_uint16) {
        this.horizontalVelAccuracy_uint16 = horizontalVelAccuracy_uint16 & 0xFFFF;
    }

    public int getHdop_uint16() {
        return hdop_uint16;
    }

    public void setHdop_uint16(int hdop_uint16) {
        this.hdop_uint16 = hdop_uint16 & 0xFFFF;
    }

    public int getLongitude_int32() {
        return longitude_int32;
    }

    public void setLongitude_int32(int longitude_int32) {
        this.longitude_int32 = longitude_int32;
    }

    public int getLatitude_int32() {
        return latitude_int32;
    }

    public void setLatitude_int32(int latitude_int32) {
        this.latitude_int32 = latitude_int32;
    }

    public int getMslAltitude_int32() {
        return mslAltitude_int32;
    }

    public void setMslAltitude_int32(int mslAltitude_int32) {
        this.mslAltitude_int32 = mslAltitude_int32;
    }

    public int getNedVelNorth_int32() {
        return nedVelNorth_int32;
    }

    public void setNedVelNorth_int32(int nedVelNorth_int32) {
        this.nedVelNorth_int32 = nedVelNorth_int32;
    }

    public int getNedVelEast_int32() {
        return nedVelEast_int32;
    }

    public void setNedVelEast_int32(int nedVelEast_int32) {
        this.nedVelEast_int32 = nedVelEast_int32;
    }

    public int getNedVelDown_int32() {
        return nedVelDown_int32;
    }

    public void setNedVelDown_int32(int nedVelDown_int32) {
        this.nedVelDown_int32 = nedVelDown_int32;
    }

    public int getGroundCourse_uint16() {
        return groundCourse_uint16;
    }

    public void setGroundCourse_uint16(int groundCourse_uint16) {
        this.groundCourse_uint16 = groundCourse_uint16 & 0xFFFF;
    }

    public int getTrueYaw_uint16() {
        return trueYaw_uint16;
    }

    public void setTrueYaw_uint16(int trueYaw_uint16) {
        this.trueYaw_uint16 = trueYaw_uint16 & 0xFFFF;
    }

    public int getYear_uint16() {
        return year_uint16;
    }

    public void setYear_uint16(int year_uint16) {
        this.year_uint16 = year_uint16 & 0xFFFF;
    }

    public int getMonth_uint8() {
        return month_uint8;
    }

    public void setMonth_uint8(int month_uint8) {
        this.month_uint8 = month_uint8 & 0xFF;
    }

    public int getDay_uint8() {
        return day_uint8;
    }

    public void setDay_uint8(int day_uint8) {
        this.day_uint8 = day_uint8 & 0xFF;
    }

    public int getHour_uint8() {
        return hour_uint8;
    }

    public void setHour_uint8(int hour_uint8) {
        this.hour_uint8 = hour_uint8 & 0xFF;
    }

    public int getMin_uint8() {
        return min_uint8;
    }

    public void setMin_uint8(int min_uint8) {
        this.min_uint8 = min_uint8 & 0xFF;
    }

    public int getSec_uint8() {
        return sec_uint8;
    }

    public void setSec_uint8(int sec_uint8) {
        this.sec_uint8 = sec_uint8 & 0xFF;
    }

    @Override
    public byte[] encode() {
        return new DatapackWriter(true)
                .writeUint8(getInstance_uint8())
                .writeUint16(getGpsWeek_uint16())
                .writeUint32(getMsTOW_uint32())
                .writeUint8(getFixType_uint8())
                .writeUint8(getSatellitesInView_uint8())
                .writeUint16(getHorizontalPosAccuracy_uint16())
                .writeUint16(getVerticalPosAccuracy_uint16())
                .writeUint16(getHorizontalVelAccuracy_uint16())
                .writeUint16(getHdop_uint16())
                .writeInt32(getLongitude_int32())
                .writeInt32(getLatitude_int32())
                .writeInt32(getMslAltitude_int32())
                .writeInt32(getNedVelNorth_int32())
                .writeInt32(getNedVelEast_int32())
                .writeInt32(getNedVelDown_int32())
                .writeUint16(getGroundCourse_uint16())
                .writeUint16(getTrueYaw_uint16())
                .writeUint16(getYear_uint16())
                .writeUint8(getMonth_uint8())
                .writeUint8(getDay_uint8())
                .writeUint8(getHour_uint8())
                .writeUint8(getMin_uint8())
                .writeUint8(getSec_uint8())
                .encode();
    }

    @Override
    public void decode(byte[] seriallized, int offset) {
        DatapackReader reader = new DatapackReader(seriallized, true);
        reader.setOffset(offset);
        setInstance_uint8(reader.readUint8());
        setGpsWeek_uint16(reader.readUint16());
        setMsTOW_uint32(reader.readUint32());
        setFixType_uint8(reader.readUint8());
        setSatellitesInView_uint8(reader.readUint8());
        setHorizontalPosAccuracy_uint16(reader.readUint16());
        setVerticalPosAccuracy_uint16(reader.readUint16());
        setHorizontalVelAccuracy_uint16(reader.readUint16());
        setHdop_uint16(reader.readUint16());
        setLongitude_int32(reader.readInt32());
        setLatitude_int32(reader.readInt32());
        setMslAltitude_int32(reader.readInt32());
        setNedVelNorth_int32(reader.readInt32());
        setNedVelEast_int32(reader.readInt32());
        setNedVelDown_int32(reader.readInt32());
        setGroundCourse_uint16(reader.readUint16());
        setTrueYaw_uint16(reader.readUint16());
        setYear_uint16(reader.readUint16());
        setMonth_uint8(reader.readUint8());
        setDay_uint8(reader.readUint8());
        setHour_uint8(reader.readUint8());
        setMin_uint8(reader.readUint8());
        setSec_uint8(reader.readUint8());
    }

    @Override
    public SensorGps deepCopy() {
        SensorGps gps = new SensorGps();
        gps.setInstance_uint8(getInstance_uint8());
        gps.setGpsWeek_uint16(getGpsWeek_uint16());
        gps.setMsTOW_uint32(getMsTOW_uint32());
        gps.setFixType_uint8(getFixType_uint8());
        gps.setSatellitesInView_uint8(getSatellitesInView_uint8());
        gps.setHorizontalPosAccuracy_uint16(getHorizontalVelAccuracy_uint16());
        gps.setVerticalPosAccuracy_uint16(getVerticalPosAccuracy_uint16());
        gps.setHorizontalVelAccuracy_uint16(getHorizontalVelAccuracy_uint16());
        gps.setHdop_uint16(getHdop_uint16());
        gps.setLongitude_int32(getLongitude_int32());
        gps.setLatitude_int32(getLatitude_int32());
        gps.setMslAltitude_int32(getMslAltitude_int32());
        gps.setNedVelNorth_int32(getNedVelNorth_int32());
        gps.setNedVelEast_int32(getNedVelEast_int32());
        gps.setNedVelDown_int32(getNedVelDown_int32());
        gps.setGroundCourse_uint16(getGroundCourse_uint16());
        gps.setTrueYaw_uint16(getTrueYaw_uint16());
        gps.setYear_uint16(getYear_uint16());
        gps.setMonth_uint8(getMonth_uint8());
        gps.setDay_uint8(getDay_uint8());
        gps.setHour_uint8(getHour_uint8());
        gps.setMin_uint8(getMin_uint8());
        gps.setSec_uint8(getSec_uint8());
        return gps;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorGps{");
        sb.append("instance_uint8=").append(instance_uint8);
        sb.append(", gpsWeek_uint16=").append(gpsWeek_uint16);
        sb.append(", msTOW_uint32=").append(msTOW_uint32);
        sb.append(", fixType_uint8=").append(fixType_uint8);
        sb.append(", satellitesInView_uint8=").append(satellitesInView_uint8);
        sb.append(", horizontalPosAccuracy_uint16=").append(horizontalPosAccuracy_uint16);
        sb.append(", verticalPosAccuracy_uint16=").append(verticalPosAccuracy_uint16);
        sb.append(", horizontalVelAccuracy_uint16=").append(horizontalVelAccuracy_uint16);
        sb.append(", hdop_uint16=").append(hdop_uint16);
        sb.append(", longitude_int32=").append(longitude_int32);
        sb.append(", latitude_int32=").append(latitude_int32);
        sb.append(", mslAltitude_int32=").append(mslAltitude_int32);
        sb.append(", nedVelNorth_int32=").append(nedVelNorth_int32);
        sb.append(", nedVelEast_int32=").append(nedVelEast_int32);
        sb.append(", nedVelDown_int32=").append(nedVelDown_int32);
        sb.append(", groundCourse_uint16=").append(groundCourse_uint16);
        sb.append(", trueYaw_uint16=").append(trueYaw_uint16);
        sb.append(", year_uint16=").append(year_uint16);
        sb.append(", month_uint8=").append(month_uint8);
        sb.append(", day_uint8=").append(day_uint8);
        sb.append(", hour_uint8=").append(hour_uint8);
        sb.append(", min_uint8=").append(min_uint8);
        sb.append(", sec_uint8=").append(sec_uint8);
        sb.append('}');
        return sb.toString();
    }

//    uint8_t instance; // sensor instance number to support multi-sensor setups
//    uint16_t gpsWeek; // GPS week, 0xFFFF if not available
//    uint32_t msTOW;
//    uint8_t fixType;
//    uint8_t satellitesInView;
//    uint16_t horizontalPosAccuracy; // [cm]
//    uint16_t verticalPosAccuracy;   // [cm]
//    uint16_t horizontalVelAccuracy; // [cm/s]
//    uint16_t hdop;
//    int32_t longitude;
//    int32_t latitude;
//    int32_t mslAltitude; // cm
//    int32_t nedVelNorth; // cm/s
//    int32_t nedVelEast;
//    int32_t nedVelDown;
//    uint16_t groundCourse; // deg * 100, 0..36000
//    uint16_t trueYaw;      // deg * 100, values of 0..36000 are valid. 65535 = no data available
//    uint16_t year;
//    uint8_t month;
//    uint8_t day;
//    uint8_t hour;
//    uint8_t min;
//    uint8_t sec;


}
