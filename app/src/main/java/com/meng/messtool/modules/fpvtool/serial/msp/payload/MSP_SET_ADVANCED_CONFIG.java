package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_ADVANCED_CONFIG implements IEncodeable {


    @InavIgnore
    public int gyroSyncDenominator;
    @InavIgnore
    public int pid_process_denom;
    @InavIgnore
    public int useUnsyncedPwm;
    public int motorPwmProtocol;
    public int motorPwmRate;
    public int servoPwmRate;
    @InavIgnore
    public int gyroSync;

    public MSP_SET_ADVANCED_CONFIG(int gyroSyncDenominator, int pid_process_denom, int useUnsyncedPwm, int motorPwmProtocol, int motorPwmRate, int servoPwmRate, int gyroSync) {
        this.gyroSyncDenominator = gyroSyncDenominator;
        this.pid_process_denom = pid_process_denom;
        this.useUnsyncedPwm = useUnsyncedPwm;
        this.motorPwmProtocol = motorPwmProtocol;
        this.motorPwmRate = motorPwmRate;
        this.servoPwmRate = servoPwmRate;
        this.gyroSync = gyroSync;
    }

    public MSP_SET_ADVANCED_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(gyroSyncDenominator)
                .writeUint8(pid_process_denom)
                .writeUint8(useUnsyncedPwm)
                .writeUint8(motorPwmProtocol)
                .writeUint16(motorPwmRate)
                .writeUint16(servoPwmRate)
                .writeUint8(gyroSync);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_ADVANCED_CONFIG{" + "gyroSyncDenominator=" + gyroSyncDenominator +
                ", pid_process_denom=" + pid_process_denom +
                ", useUnsyncedPwm=" + useUnsyncedPwm +
                ", motorPwmProtocol=" + motorPwmProtocol +
                ", motorPwmRate=" + motorPwmRate +
                ", servoPwmRate=" + servoPwmRate +
                ", gyroSync=" + gyroSync +
                '}';
    }
}
