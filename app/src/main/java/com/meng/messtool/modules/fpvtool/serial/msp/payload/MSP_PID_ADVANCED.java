package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:53
 */
public class MSP_PID_ADVANCED implements IDecodeable {

    private static final String TAG = "MSP_PID_ADVANCED";

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

    public MSP_PID_ADVANCED(int rollPitchItermIgnoreRate, int yawItermIgnoreRate, int yaw_p_limit, int deltaMethod, int vbatPidCompensation, int setpointRelaxRatio, int zero, int pidSumLimit, int itermThrottleGain, int axisAccelerationLimitRollPitch, int axisAccelerationLimitYaw) {
        Debuger.checkDebugMode();
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

    public MSP_PID_ADVANCED(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        rollPitchItermIgnoreRate = reader.readUint16();
        yawItermIgnoreRate = reader.readUint16();
        yaw_p_limit = reader.readUint16();
        deltaMethod = reader.readUint8();
        vbatPidCompensation = reader.readUint8();
        setpointRelaxRatio = reader.readUint8();
        zero = reader.readUint8();
        pidSumLimit = reader.readUint16();
        itermThrottleGain = reader.readUint8();
        axisAccelerationLimitRollPitch = reader.readUint16();
        axisAccelerationLimitYaw = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_PID_ADVANCED{" + "rollPitchItermIgnoreRate=" + rollPitchItermIgnoreRate +
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
