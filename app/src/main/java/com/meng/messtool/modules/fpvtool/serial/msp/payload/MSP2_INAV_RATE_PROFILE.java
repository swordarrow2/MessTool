package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:09
 */
public class MSP2_INAV_RATE_PROFILE implements IDecodeable {

    private static final String TAG = "MSP2_INAV_RATE_PROFILE";

    public int throttleRcMid8;
    public int throttleRcExpo8;
    public int throttleDynPID;
    public int throttlePa_breakpoint;
    public int stabilizedRcExpo8;
    public int stabilizedRcYawExpo8;
    public int[] stabilizedRates = new int[3];
    public int manualRcExpo8;
    public int manualRcYawExpo8;
    public int[] manualRates = new int[3];

    public MSP2_INAV_RATE_PROFILE(int throttleRcMid8, int throttleRcExpo8, int throttleDynPID, int throttlePa_breakpoint, int stabilizedRcExpo8, int stabilizedRcYawExpo8, int[] stabilizedRates, int manualRcExpo8, int manualRcYawExpo8, int[] manualRates) {
        this.throttleRcMid8 = throttleRcMid8;
        this.throttleRcExpo8 = throttleRcExpo8;
        this.throttleDynPID = throttleDynPID;
        this.throttlePa_breakpoint = throttlePa_breakpoint;
        this.stabilizedRcExpo8 = stabilizedRcExpo8;
        this.stabilizedRcYawExpo8 = stabilizedRcYawExpo8;
        this.stabilizedRates = stabilizedRates;
        this.manualRcExpo8 = manualRcExpo8;
        this.manualRcYawExpo8 = manualRcYawExpo8;
        this.manualRates = manualRates;
    }

    public MSP2_INAV_RATE_PROFILE(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        throttleRcMid8 = reader.readUint8();
        throttleRcExpo8 = reader.readUint8();
        throttleDynPID = reader.readUint8();
        throttlePa_breakpoint = reader.readUint16();
        stabilizedRcExpo8 = reader.readUint8();
        stabilizedRcYawExpo8 = reader.readUint8();
        for (int i = 0; i < stabilizedRates.length; i++) {
            stabilizedRates[i] = reader.readUint8();
        }
        manualRcExpo8 = reader.readUint8();
        manualRcYawExpo8 = reader.readUint8();
        for (int i = 0; i < manualRates.length; i++) {
            manualRates[i] = reader.readUint8();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_RATE_PROFILE{" + "throttleRcMid8=" + throttleRcMid8 +
                ", throttleRcExpo8=" + throttleRcExpo8 +
                ", throttleDynPID=" + throttleDynPID +
                ", throttlePa_breakpoint=" + throttlePa_breakpoint +
                ", stabilizedRcExpo8=" + stabilizedRcExpo8 +
                ", stabilizedRcYawExpo8=" + stabilizedRcYawExpo8 +
                ", stabilizedRates=" + Arrays.toString(stabilizedRates) +
                ", manualRcExpo8=" + manualRcExpo8 +
                ", manualRcYawExpo8=" + manualRcYawExpo8 +
                ", manualRates=" + Arrays.toString(manualRates) +
                '}';
    }
}
