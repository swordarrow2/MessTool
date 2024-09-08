package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:03
 */
public class MSP_RC_TUNING implements IDecodeable {

    private static final String TAG = "MSP_RC_TUNING";

    public int _100;
    public int stabilizedRcExpo8;
    public int[] stabilizedRates = new int[3];
    public int throttleDynPID;
    public int throttleRcMid8;
    public int throttleRcExpo8;
    public int throttlePa_breakpoint;
    public int stabilizedRcYawExpo8;

    public MSP_RC_TUNING(int _100, int stabilizedRcExpo8, int[] stabilizedRates, int throttleDynPID, int throttleRcMid8, int throttleRcExpo8, int throttlePa_breakpoint, int stabilizedRcYawExpo8) {
        this._100 = _100;
        this.stabilizedRcExpo8 = stabilizedRcExpo8;
        this.stabilizedRates = stabilizedRates;
        this.throttleDynPID = throttleDynPID;
        this.throttleRcMid8 = throttleRcMid8;
        this.throttleRcExpo8 = throttleRcExpo8;
        this.throttlePa_breakpoint = throttlePa_breakpoint;
        this.stabilizedRcYawExpo8 = stabilizedRcYawExpo8;
    }

    public MSP_RC_TUNING(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);

        _100 = reader.readUint8();
        stabilizedRcExpo8 = reader.readUint8();
        for (int i = 0; i < stabilizedRates.length; i++) {
            stabilizedRates[i] = reader.readUint8();
        }
        throttleDynPID = reader.readUint8();
        throttleRcMid8 = reader.readUint8();
        throttleRcExpo8 = reader.readUint8();
        throttlePa_breakpoint = reader.readUint16();
        stabilizedRcYawExpo8 = reader.readUint8();
    }

    @Override
    public String toString() {
        return "MSP_RC_TUNING{" + "_100=" + _100 +
                ", stabilizedRcExpo8=" + stabilizedRcExpo8 +
                ", stabilizedRates=" + Arrays.toString(stabilizedRates) +
                ", throttleDynPID=" + throttleDynPID +
                ", throttleRcMid8=" + throttleRcMid8 +
                ", throttleRcExpo8=" + throttleRcExpo8 +
                ", throttlePa_breakpoint=" + throttlePa_breakpoint +
                ", stabilizedRcYawExpo8=" + stabilizedRcYawExpo8 +
                '}';
    }
}
