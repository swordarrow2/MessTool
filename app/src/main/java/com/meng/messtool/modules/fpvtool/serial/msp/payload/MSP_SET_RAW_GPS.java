package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_SET_RAW_GPS implements IEncodeable {

    public int fixType;
    public int numSat;
    public int lat;
    public int lon;
    public int alt;
    public int groundSpeed;

    public MSP_SET_RAW_GPS(int fixType, int numSat, int lat, int lon, int alt, int groundSpeed) {
        this.fixType = fixType;
        this.numSat = numSat;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.groundSpeed = groundSpeed;
    }

    public MSP_SET_RAW_GPS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(fixType)
                .writeUint8(numSat)
                .writeInt32(lat)
                .writeInt32(lon)
                .writeUint16(alt)
                .writeUint16(groundSpeed);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RAW_GPS{" + "fixType=" + fixType +
                ", numSat=" + numSat +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                ", groundSpeed=" + groundSpeed +
                '}';
    }
}
