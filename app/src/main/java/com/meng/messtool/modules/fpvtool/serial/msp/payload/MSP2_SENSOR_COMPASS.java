package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;


/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 10:34
 */
public class MSP2_SENSOR_COMPASS implements IDecodeable {

    public int instance;
    public long timeMs;
    public int magX; // mGauss, front
    public int magY; // mGauss, right
    public int magZ; // mGauss, down

    public MSP2_SENSOR_COMPASS(int instance, long timeMs, int magX_int16, int magY_int16, int magZ_int16) {
        Debuger.checkDebugMode();
        this.instance = instance;
        this.timeMs = timeMs;
        this.magX = magX_int16;
        this.magY = magY_int16;
        this.magZ = magZ_int16;
    }

    public MSP2_SENSOR_COMPASS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        instance = reader.readUint8();
        timeMs = reader.readUint32();
        magX = reader.readInt16();
        magY = reader.readInt16();
        magZ = reader.readInt16();
        reader.checkFinish();
    }
}
