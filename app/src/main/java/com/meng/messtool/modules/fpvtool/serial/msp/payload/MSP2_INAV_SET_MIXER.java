package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_INAV_SET_MIXER implements IEncodeable {

    //todo msp datapack
    public int motorDirectionInverted;
    @InavIgnore
    public int yaw_jump_prevention_limit;
    public int motorstopOnLow;
    public int platformType;
    public int hasFlaps;
    public int appliedMixerPreset;
    @InavIgnore
    public int maxSupportedMotors;
    @InavIgnore
    public int maxSupportedServos;

    public MSP2_INAV_SET_MIXER(int motorDirectionInverted, int yaw_jump_prevention_limit, int motorstopOnLow, int platformType, int hasFlaps, int appliedMixerPreset, int maxSupportedMotors, int maxSupportedServos) {
        this.motorDirectionInverted = motorDirectionInverted;
        this.yaw_jump_prevention_limit = yaw_jump_prevention_limit;
        this.motorstopOnLow = motorstopOnLow;
        this.platformType = platformType;
        this.hasFlaps = hasFlaps;
        this.appliedMixerPreset = appliedMixerPreset;
        this.maxSupportedMotors = maxSupportedMotors;
        this.maxSupportedServos = maxSupportedServos;
    }

    public MSP2_INAV_SET_MIXER() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(motorDirectionInverted)
                .writeUint8(yaw_jump_prevention_limit)
                .writeUint8(motorstopOnLow)
                .writeUint8(platformType)
                .writeUint8(hasFlaps)
                .writeUint16(appliedMixerPreset)
                .writeUint8(maxSupportedMotors)
                .writeUint8(maxSupportedServos);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_MIXER{" + "motorDirectionInverted=" + motorDirectionInverted +
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
