package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:05
 */
public class MSP_FW_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_FW_CONFIG";

    public int cruise_throttle;
    public int min_throttle;
    public int max_throttle;
    public int max_bank_angle;
    public int max_climb_angle;
    public int max_dive_angle;
    public int pitch_to_throttle;
    public int loiter_radius;

    public MSP_FW_CONFIG(int cruise_throttle, int min_throttle, int max_throttle, int max_bank_angle, int max_climb_angle, int max_dive_angle, int pitch_to_throttle, int loiter_radius) {
        Debuger.checkDebugMode();
        this.cruise_throttle = cruise_throttle;
        this.min_throttle = min_throttle;
        this.max_throttle = max_throttle;
        this.max_bank_angle = max_bank_angle;
        this.max_climb_angle = max_climb_angle;
        this.max_dive_angle = max_dive_angle;
        this.pitch_to_throttle = pitch_to_throttle;
        this.loiter_radius = loiter_radius;
    }

    public MSP_FW_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        cruise_throttle = reader.readUint16();
        min_throttle = reader.readUint16();
        max_throttle = reader.readUint16();
        max_bank_angle = reader.readUint8();
        max_climb_angle = reader.readUint8();
        max_dive_angle = reader.readUint8();
        pitch_to_throttle = reader.readUint8();
        loiter_radius = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_FW_CONFIG{" + "cruise_throttle=" + cruise_throttle +
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
