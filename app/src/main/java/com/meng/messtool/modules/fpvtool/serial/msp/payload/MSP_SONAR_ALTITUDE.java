package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:19
 */
public class MSP_SONAR_ALTITUDE implements IDecodeable {

    private static final String TAG = "MSP_SONAR_ALTITUDE";

    public int altitude;

    public MSP_SONAR_ALTITUDE(int altitude) {
        Debuger.checkDebugMode();
        this.altitude = altitude;
    }

    public MSP_SONAR_ALTITUDE(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        altitude = reader.readInt32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SONAR_ALTITUDE{" + "altitude=" + altitude + '}';
    }
}
