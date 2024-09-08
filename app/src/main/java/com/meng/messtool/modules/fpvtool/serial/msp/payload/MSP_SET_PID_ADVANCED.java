package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_PID_ADVANCED implements IEncodeable {

    @InavIgnore
    public int rollPitchItermIgnoreRate;
    @InavIgnore
    public int yawItermIgnoreRate;
    @InavIgnore
    public int yaw_p_limit;
    @InavIgnore
    public int deltaMethod;
    @InavIgnore
    public int vbatPidCompensation;
    @InavIgnore
    public int setpointRelaxRatio;
    @InavIgnore
    public int zero;
    public int pidSumLimit;
    @InavIgnore
    public int itermThrottleGain;
    public int axisAccelerationLimitRollPitch;
    public int axisAccelerationLimitYaw;

    public MSP_SET_PID_ADVANCED(int rollPitchItermIgnoreRate, int yawItermIgnoreRate, int yaw_p_limit, int deltaMethod, int vbatPidCompensation, int setpointRelaxRatio, int zero, int pidSumLimit, int itermThrottleGain, int axisAccelerationLimitRollPitch, int axisAccelerationLimitYaw) {
        this.rollPitchItermIgnoreRate = rollPitchItermIgnoreRate;
        this.yawItermIgnoreRate = yawItermIgnoreRate;
        this.yaw_p_limit = yaw_p_limit;
        this.deltaMethod = deltaMethod;
        this.vbatPidCompensation = vbatPidCompensation;
        this.setpointRelaxRatio = setpointRelaxRatio;
        this.zero = zero;
        this.pidSumLimit = pidSumLimit;
        this.itermThrottleGain = itermThrottleGain;
        this.axisAccelerationLimitRollPitch = axisAccelerationLimitRollPitch;
        this.axisAccelerationLimitYaw = axisAccelerationLimitYaw;
    }

    public MSP_SET_PID_ADVANCED() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(rollPitchItermIgnoreRate)
                .writeUint16(yawItermIgnoreRate)
                .writeUint16(yaw_p_limit)
                .writeUint8(deltaMethod)
                .writeUint8(vbatPidCompensation)
                .writeUint8(setpointRelaxRatio)
                .writeUint8(zero)
                .writeUint16(pidSumLimit)
                .writeUint8(itermThrottleGain)
                .writeUint16(axisAccelerationLimitRollPitch)
                .writeUint16(axisAccelerationLimitYaw);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_PID_ADVANCED{" + "rollPitchItermIgnoreRate=" + rollPitchItermIgnoreRate +
                ", yawItermIgnoreRate=" + yawItermIgnoreRate +
                ", yaw_p_limit=" + yaw_p_limit +
                ", deltaMethod=" + deltaMethod +
                ", vbatPidCompensation=" + vbatPidCompensation +
                ", setpointRelaxRatio=" + setpointRelaxRatio +
                ", zero=" + zero +
                ", pidSumLimit=" + pidSumLimit +
                ", itermThrottleGain=" + itermThrottleGain +
                ", axisAccelerationLimitRollPitch=" + axisAccelerationLimitRollPitch +
                ", axisAccelerationLimitYaw=" + axisAccelerationLimitYaw +
                '}';
    }
}
