package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:28
 */
public class MSP_ALTITUDE implements IDecodeable {

    private static final String TAG = "MSP_ALTITUDE";

    public float getEstimatedActualPosition;
    public int getEstimatedActualVelocity;
    public int baroGetLatestAltitude;

    public MSP_ALTITUDE(byte[] data) {
        decode(data);
    }

    public MSP_ALTITUDE(int getEstimatedActualPosition, int getEstimatedActualVelocity, int baroGetLatestAltitude) {
        Debuger.checkDebugMode();
        this.getEstimatedActualPosition = getEstimatedActualPosition;
        this.getEstimatedActualVelocity = getEstimatedActualVelocity;
        this.baroGetLatestAltitude = baroGetLatestAltitude;
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        getEstimatedActualPosition = reader.readFloat();
        getEstimatedActualVelocity = reader.readInt16();
        baroGetLatestAltitude = reader.readInt32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ALTITUDE{" + "getEstimatedActualPosition=" + getEstimatedActualPosition +
                ", getEstimatedActualVelocity=" + getEstimatedActualVelocity +
                ", baroGetLatestAltitude=" + baroGetLatestAltitude +
                '}';
    }
}
