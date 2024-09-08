package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;


/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 10:34
 */
public class MSP2_SENSOR_OPTIC_FLOW implements IDecodeable {

    public int quality; // [0;255]
    public int motionX;
    public int motionY;

    public MSP2_SENSOR_OPTIC_FLOW(int quality, int motionX, int motionY) {
        Debuger.checkDebugMode();
        this.quality = quality;
        this.motionX = motionX;
        this.motionY = motionY;
    }

    public MSP2_SENSOR_OPTIC_FLOW(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        quality = reader.readUint8();
        motionX = reader.readInt32();
        motionY = reader.readInt32();
        reader.checkFinish();
    }
}
