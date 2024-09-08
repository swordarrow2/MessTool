package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:41
 */
public class MSP2_INAV_MC_BRAKING implements IDecodeable {

    private static final String TAG = "MSP2_INAV_MC_BRAKING";

    public int braking_speed_threshold;
    public int braking_disengage_speed;
    public int braking_timeout;
    public int braking_boost_factor;
    public int braking_boost_timeout;
    public int braking_boost_speed_threshold;
    public int braking_boost_disengage_speed;
    public int braking_bank_angle;

    public MSP2_INAV_MC_BRAKING(int braking_speed_threshold, int braking_disengage_speed, int braking_timeout, int braking_boost_factor, int braking_boost_timeout, int braking_boost_speed_threshold, int braking_boost_disengage_speed, int braking_bank_angle) {
        Debuger.checkDebugMode();
        this.braking_speed_threshold = braking_speed_threshold;
        this.braking_disengage_speed = braking_disengage_speed;
        this.braking_timeout = braking_timeout;
        this.braking_boost_factor = braking_boost_factor;
        this.braking_boost_timeout = braking_boost_timeout;
        this.braking_boost_speed_threshold = braking_boost_speed_threshold;
        this.braking_boost_disengage_speed = braking_boost_disengage_speed;
        this.braking_bank_angle = braking_bank_angle;
    }

    public MSP2_INAV_MC_BRAKING(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        braking_speed_threshold = reader.readUint16();
        braking_disengage_speed = reader.readUint16();
        braking_timeout = reader.readUint16();
        braking_boost_factor = reader.readUint8();
        braking_boost_timeout = reader.readUint16();
        braking_boost_speed_threshold = reader.readUint16();
        braking_boost_disengage_speed = reader.readUint16();
        braking_bank_angle = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_MC_BRAKING{" + "braking_speed_threshold=" + braking_speed_threshold +
                ", braking_disengage_speed=" + braking_disengage_speed +
                ", braking_timeout=" + braking_timeout +
                ", braking_boost_factor=" + braking_boost_factor +
                ", braking_boost_timeout=" + braking_boost_timeout +
                ", braking_boost_speed_threshold=" + braking_boost_speed_threshold +
                ", braking_boost_disengage_speed=" + braking_boost_disengage_speed +
                ", braking_bank_angle=" + braking_bank_angle +
                '}';
    }
}
