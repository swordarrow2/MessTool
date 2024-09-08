package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:35
 */
public class MSP2_INAV_SET_SAFEHOME implements IEncodeable {

    public int index;
    public int enabled;
    public int lat;
    public int lon;

    public MSP2_INAV_SET_SAFEHOME(int index, int enabled, int lat, int lon) {
        this.index = index;
        this.enabled = enabled;
        this.lat = lat;
        this.lon = lon;
    }

    public MSP2_INAV_SET_SAFEHOME() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(enabled)
                .writeInt32(lat)
                .writeInt32(lon);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_SAFEHOME{" + "index=" + index +
                ", enabled=" + enabled +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
