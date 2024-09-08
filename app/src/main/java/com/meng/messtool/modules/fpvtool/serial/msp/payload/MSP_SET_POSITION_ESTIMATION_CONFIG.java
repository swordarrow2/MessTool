package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:27
 */
public class MSP_SET_POSITION_ESTIMATION_CONFIG implements IEncodeable {

    public int w_z_baro_p;
    public int w_z_gps_p;
    public int w_z_gps_v;
    public int w_xy_gps_p;
    public int w_xy_gps_v;
    public int gpsMinSats;
    public int use_gps_velned;

    public MSP_SET_POSITION_ESTIMATION_CONFIG(int w_z_baro_p, int w_z_gps_p, int w_z_gps_v, int w_xy_gps_p, int w_xy_gps_v, int gpsMinSats, int use_gps_velned) {
        this.w_z_baro_p = w_z_baro_p;
        this.w_z_gps_p = w_z_gps_p;
        this.w_z_gps_v = w_z_gps_v;
        this.w_xy_gps_p = w_xy_gps_p;
        this.w_xy_gps_v = w_xy_gps_v;
        this.gpsMinSats = gpsMinSats;
        this.use_gps_velned = use_gps_velned;
    }

    public MSP_SET_POSITION_ESTIMATION_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(w_z_baro_p)
                .writeUint16(w_z_gps_p)
                .writeUint16(w_z_gps_v)
                .writeUint16(w_xy_gps_p)
                .writeUint16(w_xy_gps_v)
                .writeUint8(gpsMinSats)
                .writeUint8(use_gps_velned);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_POSITION_ESTIMATION_CONFIG{" + "w_z_baro_p=" + w_z_baro_p +
                ", w_z_gps_p=" + w_z_gps_p +
                ", w_z_gps_v=" + w_z_gps_v +
                ", w_xy_gps_p=" + w_xy_gps_p +
                ", w_xy_gps_v=" + w_xy_gps_v +
                ", gpsMinSats=" + gpsMinSats +
                ", use_gps_velned=" + use_gps_velned +
                '}';
    }
}
