package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:48
 */
public class MSP_RAW_GPS implements IDecodeable {

    private static final String TAG = "MSP_RAW_GPS";

    public int fixType;
    public int numSat;
    public int lat;
    public int lon;
    public int altMeters;
    public int groundSpeed;
    public int groundCourse;
    public int hdop;

    public MSP_RAW_GPS(int fixType, int numSat, int lat, int lon, int altMeters, int groundSpeed, int groundCourse, int hdop) {
        Debuger.checkDebugMode();
        this.fixType = fixType;
        this.numSat = numSat;
        this.lat = lat;
        this.lon = lon;
        this.altMeters = altMeters;
        this.groundSpeed = groundSpeed;
        this.groundCourse = groundCourse;
        this.hdop = hdop;
    }

    public MSP_RAW_GPS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        fixType = reader.readUint8();
        numSat = reader.readUint8();
        lat = reader.readInt32();
        lon = reader.readInt32();
        altMeters = reader.readInt16();
        groundSpeed = reader.readInt16();
        groundCourse = reader.readInt16();
        hdop = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RAW_GPS{" + "fixType=" + fixType +
                ", numSat=" + numSat +
                ", lat=" + lat +
                ", lon=" + lon +
                ", altMeters=" + altMeters +
                ", groundSpeed=" + groundSpeed +
                ", groundCourse=" + groundCourse +
                ", hdop=" + hdop +
                '}';
    }
}
