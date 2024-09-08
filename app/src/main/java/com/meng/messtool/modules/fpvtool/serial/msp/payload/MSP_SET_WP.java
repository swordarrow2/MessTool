package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_SET_WP implements IEncodeable {

    public int msp_wp_no;
    public int action;
    public int lat;
    public int lon;
    public int alt;
    public int p1;
    public int p2;
    public int p3;
    public int flag;

    public MSP_SET_WP(int msp_wp_no, int action, int lat, int lon, int alt, int p1, int p2, int p3, int flag) {
        this.msp_wp_no = msp_wp_no;
        this.action = action;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.flag = flag;
    }

    public MSP_SET_WP() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(msp_wp_no)
                .writeUint8(action)
                .writeInt32(lat)
                .writeInt32(lon)
                .writeInt32(alt)
                .writeInt16(p1)
                .writeInt16(p2)
                .writeInt16(p3)
                .writeUint8(flag);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_WP{" + "msp_wp_no=" + msp_wp_no +
                ", action=" + action +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", flag=" + flag +
                '}';
    }
}
