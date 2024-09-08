package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_INAV_SET_MC_BRAKING implements IEncodeable {

    public int braking_speed_threshold;
    public int braking_disengage_speed;
    public int braking_timeout;
    public int braking_boost_factor;
    public int braking_boost_timeout;
    public int braking_boost_speed_threshold;
    public int braking_boost_disengage_speed;
    public int braking_bank_angle;

    public MSP2_INAV_SET_MC_BRAKING(int braking_speed_threshold, int braking_disengage_speed, int braking_timeout, int braking_boost_factor, int braking_boost_timeout, int braking_boost_speed_threshold, int braking_boost_disengage_speed, int braking_bank_angle) {
        this.braking_speed_threshold = braking_speed_threshold;
        this.braking_disengage_speed = braking_disengage_speed;
        this.braking_timeout = braking_timeout;
        this.braking_boost_factor = braking_boost_factor;
        this.braking_boost_timeout = braking_boost_timeout;
        this.braking_boost_speed_threshold = braking_boost_speed_threshold;
        this.braking_boost_disengage_speed = braking_boost_disengage_speed;
        this.braking_bank_angle = braking_bank_angle;
    }

    public MSP2_INAV_SET_MC_BRAKING() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(braking_speed_threshold)
                .writeUint16(braking_disengage_speed)
                .writeUint16(braking_timeout)
                .writeUint8(braking_boost_factor)
                .writeUint16(braking_timeout)
                .writeUint16(braking_boost_speed_threshold)
                .writeUint16(braking_boost_disengage_speed)
                .writeUint8(braking_bank_angle);

        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_MC_BRAKING{" + "braking_speed_threshold=" + braking_speed_threshold +
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
