package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;


/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 10:34
 */
public class MSP2_SENSOR_BAROMETER implements IDecodeable {

    public int instance;
    public long timeMs;
    public float pressurePa;
    public int temp; // centi-degrees C

    public MSP2_SENSOR_BAROMETER(int instance, long timeMs, float pressurePa, int temp) {
        Debuger.checkDebugMode();
        this.instance = instance;
        this.timeMs = timeMs;
        this.pressurePa = pressurePa;
        this.temp = temp;
    }

    public MSP2_SENSOR_BAROMETER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        instance = reader.readUint8();
        timeMs = reader.readUint32();
        pressurePa = reader.readFloat();
        temp = reader.readInt16();
        reader.checkFinish();
    }
}
