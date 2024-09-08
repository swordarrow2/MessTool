package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP2_COMMON_SET_RADAR_POS implements IEncodeable {

    @MspParam(note = "Radar poi number", min = 0, max = 3)
    public int index;
    public int state;// 0=undefined, 1=armed, 2=lost
    public int lat;// lat 10E7
    public int lon;// lon 10E7
    public int alt;// altitude (cm)
    public int heading;// °
    public int speed;// cm/s
    public int lq;// Link quality, from 0 to 4

    public MSP2_COMMON_SET_RADAR_POS(int index, int state, int lat, int lon, int alt, int heading, int speed, int lq) {
        this.index = index;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.heading = heading;
        this.speed = speed;
        this.lq = lq;
    }

    public MSP2_COMMON_SET_RADAR_POS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(state)
                .writeInt32(lat)
                .writeInt32(lon)
                .writeInt32(alt)
                .writeUint16(heading)
                .writeUint16(speed)
                .writeUint8(lq);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_SET_RADAR_POS{" + "index=" + index +
                ", state=" + state +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                ", heading=" + heading +
                ", speed=" + speed +
                ", lq=" + lq +
                '}';
    }
}
