package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:50
 */
public class MSP_ADVANCED_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_ADVANCED_CONFIG";

    public int gyroSyncDenominator;
    public int pid_process_denom;
    public int useUnsyncedPwm;
    public int motorPwmProtocol;
    public int motorPwmRate;
    public int servoPwmRate;
    public int gyroSync;

    public MSP_ADVANCED_CONFIG(byte[] data) {
        decode(data);
    }

    public MSP_ADVANCED_CONFIG(int gyroSyncDenominator, int pid_process_denom, int useUnsyncedPwm, int motorPwmProtocol, int motorPwmRate, int servoPwmRate, int gyroSync) {
        Debuger.checkDebugMode();
        this.gyroSyncDenominator = gyroSyncDenominator;
        this.pid_process_denom = pid_process_denom;
        this.useUnsyncedPwm = useUnsyncedPwm;
        this.motorPwmProtocol = motorPwmProtocol;
        this.motorPwmRate = motorPwmRate;
        this.servoPwmRate = servoPwmRate;
        this.gyroSync = gyroSync;
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        gyroSyncDenominator = reader.readUint8();
        pid_process_denom = reader.readUint8();
        useUnsyncedPwm = reader.readUint8();
        motorPwmProtocol = reader.readUint8();
        motorPwmRate = reader.readUint16();
        servoPwmRate = reader.readUint16();
        gyroSync = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ADVANCED_CONFIG{" + "gyroSyncDenominator=" + gyroSyncDenominator +
                ", pid_process_denom=" + pid_process_denom +
                ", useUnsyncedPwm=" + useUnsyncedPwm +
                ", motorPwmProtocol=" + motorPwmProtocol +
                ", motorPwmRate=" + motorPwmRate +
                ", servoPwmRate=" + servoPwmRate +
                ", gyroSync=" + gyroSync +
                '}';
    }
}
