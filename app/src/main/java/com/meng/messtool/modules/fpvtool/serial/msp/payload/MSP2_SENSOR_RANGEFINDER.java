package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 10:31
 */
public class MSP2_SENSOR_RANGEFINDER implements IDecodeable {

    public int quality;    // [0;255]
    public int distanceMm; // Negative value for out of range

    public MSP2_SENSOR_RANGEFINDER(int quality, int distanceMm) {
        Debuger.checkDebugMode();
        this.quality = quality;
        this.distanceMm = distanceMm;
    }

    public MSP2_SENSOR_RANGEFINDER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        quality = reader.readUint8();
        distanceMm = reader.readInt32();
        reader.checkFinish();
    }
}
