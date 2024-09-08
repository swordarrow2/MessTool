package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:08
 */
public class MSP_POSITION_ESTIMATION_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_POSITION_ESTIMATION_CONFIG";

    public int w_z_baro_p;
    public int w_z_gps_p;
    public int w_z_gps_v;
    public int w_xy_gps_p;
    public int w_xy_gps_v;
    public int gpsMinSats;
    public int use_gps_velned;

    public MSP_POSITION_ESTIMATION_CONFIG(int w_z_baro_p, int w_z_gps_p, int w_z_gps_v, int w_xy_gps_p, int w_xy_gps_v, int gpsMinSats, int use_gps_velned) {
        Debuger.checkDebugMode();
        this.w_z_baro_p = w_z_baro_p;
        this.w_z_gps_p = w_z_gps_p;
        this.w_z_gps_v = w_z_gps_v;
        this.w_xy_gps_p = w_xy_gps_p;
        this.w_xy_gps_v = w_xy_gps_v;
        this.gpsMinSats = gpsMinSats;
        this.use_gps_velned = use_gps_velned;
    }

    public MSP_POSITION_ESTIMATION_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        w_z_baro_p = reader.readUint16();
        w_z_gps_p = reader.readUint16();
        w_z_gps_v = reader.readUint16();
        w_xy_gps_p = reader.readUint16();
        w_xy_gps_v = reader.readUint16();
        gpsMinSats = reader.readUint8();
        use_gps_velned = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_POSITION_ESTIMATION_CONFIG{" + "w_z_baro_p=" + w_z_baro_p +
                ", w_z_gps_p=" + w_z_gps_p +
                ", w_z_gps_v=" + w_z_gps_v +
                ", w_xy_gps_p=" + w_xy_gps_p +
                ", w_xy_gps_v=" + w_xy_gps_v +
                ", gpsMinSats=" + gpsMinSats +
                ", use_gps_velned=" + use_gps_velned +
                '}';
    }
}
