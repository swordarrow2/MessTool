package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:19
 */
public class MSP2_INAV_AIR_SPEED implements IDecodeable {

    private static final String TAG = "MSP2_INAV_AIR_SPEED";

    public int getAirspeedEstimate;

    public MSP2_INAV_AIR_SPEED(int getAirspeedEstimate) {
        Debuger.checkDebugMode();
        this.getAirspeedEstimate = getAirspeedEstimate;
    }

    public MSP2_INAV_AIR_SPEED(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        getAirspeedEstimate = reader.readInt32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_AIR_SPEED{" + "getAirspeedEstimate=" + getAirspeedEstimate + '}';
    }
}
