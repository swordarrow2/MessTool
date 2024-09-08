package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:19
 */
public class MSP2_INAV_MIXER implements IDecodeable {

    private static final String TAG = "MSP2_INAV_MIXER";

    public int motorDirectionInverted;
    public int yaw_jump_prevention_limit;
    public int motorstopOnLow;
    public int platformType;
    public int hasFlaps;
    public int appliedMixerPreset;
    public int maxSupportedMotors;
    public int maxSupportedServos;

    public MSP2_INAV_MIXER(int motorDirectionInverted, int yaw_jump_prevention_limit, int motorstopOnLow, int platformType, int hasFlaps, int appliedMixerPreset, int maxSupportedMotors, int maxSupportedServos) {
        Debuger.checkDebugMode();
        this.motorDirectionInverted = motorDirectionInverted;
        this.yaw_jump_prevention_limit = yaw_jump_prevention_limit;
        this.motorstopOnLow = motorstopOnLow;
        this.platformType = platformType;
        this.hasFlaps = hasFlaps;
        this.appliedMixerPreset = appliedMixerPreset;
        this.maxSupportedMotors = maxSupportedMotors;
        this.maxSupportedServos = maxSupportedServos;
    }

    public MSP2_INAV_MIXER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        motorDirectionInverted = reader.readInt8();
        yaw_jump_prevention_limit = reader.readInt8();
        motorstopOnLow = reader.readInt8();//bool
        platformType = reader.readUint8();
        hasFlaps = reader.readInt8();//bool
        appliedMixerPreset = reader.readInt16();
        maxSupportedMotors = reader.readInt8();
        maxSupportedServos = reader.readInt8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_MIXER{" + "motorDirectionInverted=" + motorDirectionInverted +
                ", yaw_jump_prevention_limit=" + yaw_jump_prevention_limit +
                ", motorstopOnLow=" + motorstopOnLow +
                ", platformType=" + platformType +
                ", hasFlaps=" + hasFlaps +
                ", appliedMixerPreset=" + appliedMixerPreset +
                ", maxSupportedMotors=" + maxSupportedMotors +
                ", maxSupportedServos=" + maxSupportedServos +
                '}';
    }
}
