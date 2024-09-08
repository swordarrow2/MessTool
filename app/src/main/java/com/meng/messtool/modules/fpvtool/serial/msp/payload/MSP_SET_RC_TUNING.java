package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:23
 */
public class MSP_SET_RC_TUNING implements IEncodeable {

    @InavIgnore
    public int _100;
    public int stabilizedRcExpo8;
    public int[] stabilizedRates = new int[3];
    public int throttleDynPID;
    public int throttleRcMid8;
    public int throttleRcExpo8;
    public int throttlePa_breakpoint;
    public int stabilizedRcYawExpo8;

    public MSP_SET_RC_TUNING(int _100, int stabilizedRcExpo8, int[] stabilizedRates, int throttleDynPID, int throttleRcMid8, int throttleRcExpo8, int throttlePa_breakpoint, int stabilizedRcYawExpo8) {
        this._100 = _100;
        this.stabilizedRcExpo8 = stabilizedRcExpo8;
        this.stabilizedRates = stabilizedRates;
        this.throttleDynPID = throttleDynPID;
        this.throttleRcMid8 = throttleRcMid8;
        this.throttleRcExpo8 = throttleRcExpo8;
        this.throttlePa_breakpoint = throttlePa_breakpoint;
        this.stabilizedRcYawExpo8 = stabilizedRcYawExpo8;
    }

    public MSP_SET_RC_TUNING() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(_100)
                .writeUint8(stabilizedRcExpo8);
        for (int i : stabilizedRates) {
            writer.writeUint8(i);
        }
        writer.writeUint8(throttleDynPID)
                .writeUint8(throttleRcMid8)
                .writeUint8(throttleRcExpo8)
                .writeUint16(throttlePa_breakpoint)
                .writeUint8(stabilizedRcYawExpo8);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_RC_TUNING{" + "_100=" + _100 +
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
