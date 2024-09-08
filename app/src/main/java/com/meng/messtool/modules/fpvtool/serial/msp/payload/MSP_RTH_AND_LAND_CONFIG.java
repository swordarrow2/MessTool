package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:00
 */
public class MSP_RTH_AND_LAND_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_RTH_AND_LAND_CONFIG";

    public int min_rth_distance;
    public int rth_climb_first;
    public int rth_climb_ignore_emerg;
    public int rth_tail_first;
    public int rth_allow_landing;
    public int rth_alt_control_mode;
    public int rth_abort_threshold;
    public int rth_altitude;
    public int land_minalt_vspd;
    public int land_maxalt_vspd;
    public int land_slowdown_minalt;
    public int land_slowdown_maxalt;
    public int emerg_descent_rate;

    public MSP_RTH_AND_LAND_CONFIG(int min_rth_distance, int rth_climb_first, int rth_climb_ignore_emerg, int rth_tail_first, int rth_allow_landing, int rth_alt_control_mode, int rth_abort_threshold, int rth_altitude, int land_minalt_vspd, int land_maxalt_vspd, int land_slowdown_minalt, int land_slowdown_maxalt, int emerg_descent_rate) {
        Debuger.checkDebugMode();
        this.min_rth_distance = min_rth_distance;
        this.rth_climb_first = rth_climb_first;
        this.rth_climb_ignore_emerg = rth_climb_ignore_emerg;
        this.rth_tail_first = rth_tail_first;
        this.rth_allow_landing = rth_allow_landing;
        this.rth_alt_control_mode = rth_alt_control_mode;
        this.rth_abort_threshold = rth_abort_threshold;
        this.rth_altitude = rth_altitude;
        this.land_minalt_vspd = land_minalt_vspd;
        this.land_maxalt_vspd = land_maxalt_vspd;
        this.land_slowdown_minalt = land_slowdown_minalt;
        this.land_slowdown_maxalt = land_slowdown_maxalt;
        this.emerg_descent_rate = emerg_descent_rate;
    }

    public MSP_RTH_AND_LAND_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        min_rth_distance = reader.readUint16();
        rth_climb_first = reader.readUint8();
        rth_climb_ignore_emerg = reader.readUint8();
        rth_tail_first = reader.readUint8();
        rth_allow_landing = reader.readUint8();
        rth_alt_control_mode = reader.readUint8();
        rth_abort_threshold = reader.readUint16();
        rth_altitude = reader.readUint16();
        land_minalt_vspd = reader.readUint16();
        land_maxalt_vspd = reader.readUint16();
        land_slowdown_minalt = reader.readUint16();
        land_slowdown_maxalt = reader.readUint16();
        emerg_descent_rate = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RTH_AND_LAND_CONFIG{" + "min_rth_distance=" + min_rth_distance +
                ", rth_climb_first=" + rth_climb_first +
                ", rth_climb_ignore_emerg=" + rth_climb_ignore_emerg +
                ", rth_tail_first=" + rth_tail_first +
                ", rth_allow_landing=" + rth_allow_landing +
                ", rth_alt_control_mode=" + rth_alt_control_mode +
                ", rth_abort_threshold=" + rth_abort_threshold +
                ", rth_altitude=" + rth_altitude +
                ", land_minalt_vspd=" + land_minalt_vspd +
                ", land_maxalt_vspd=" + land_maxalt_vspd +
                ", land_slowdown_minalt=" + land_slowdown_minalt +
                ", land_slowdown_maxalt=" + land_slowdown_maxalt +
                ", emerg_descent_rate=" + emerg_descent_rate +
                '}';
    }
}
