package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:27
 */
public class MSP_SET_FW_CONFIG implements IEncodeable {

    public int cruise_throttle;
    public int min_throttle;
    public int max_throttle;
    public int max_bank_angle;
    public int max_climb_angle;
    public int max_dive_angle;
    public int pitch_to_throttle;
    public int loiter_radius;

    public MSP_SET_FW_CONFIG(int cruise_throttle, int min_throttle, int max_throttle, int max_bank_angle, int max_climb_angle, int max_dive_angle, int pitch_to_throttle, int loiter_radius) {
        this.cruise_throttle = cruise_throttle;
        this.min_throttle = min_throttle;
        this.max_throttle = max_throttle;
        this.max_bank_angle = max_bank_angle;
        this.max_climb_angle = max_climb_angle;
        this.max_dive_angle = max_dive_angle;
        this.pitch_to_throttle = pitch_to_throttle;
        this.loiter_radius = loiter_radius;
    }

    public MSP_SET_FW_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(cruise_throttle)
                .writeUint16(min_throttle)
                .writeUint16(max_throttle)
                .writeUint8(max_bank_angle)
                .writeUint8(max_climb_angle)
                .writeUint8(max_dive_angle)
                .writeUint8(pitch_to_throttle)
                .writeUint16(loiter_radius);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_FW_CONFIG{" + "cruise_throttle=" + cruise_throttle +
                ", min_throttle=" + min_throttle +
                ", max_throttle=" + max_throttle +
                ", max_bank_angle=" + max_bank_angle +
                ", max_climb_angle=" + max_climb_angle +
                ", max_dive_angle=" + max_dive_angle +
                ", pitch_to_throttle=" + pitch_to_throttle +
                ", loiter_radius=" + loiter_radius +
                '}';
    }
}
