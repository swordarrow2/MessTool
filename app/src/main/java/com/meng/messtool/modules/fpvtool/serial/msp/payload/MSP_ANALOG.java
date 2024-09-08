package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:37
 */
public class MSP_ANALOG implements IDecodeable {

    private static final String TAG = "MSP_ANALOG";

    public int getBatteryVoltage;
    public int getMAhDrawn;
    public int getRSSI;
    public int getAmperage;

    public MSP_ANALOG(byte[] data) {
        decode(data);
    }

    public MSP_ANALOG(int getBatteryVoltage, int getMAhDrawn, int getRSSI, int getAmperage) {
        Debuger.checkDebugMode();
        this.getBatteryVoltage = getBatteryVoltage;
        this.getMAhDrawn = getMAhDrawn;
        this.getRSSI = getRSSI;
        this.getAmperage = getAmperage;
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        getBatteryVoltage = reader.readUint8();
        getMAhDrawn = reader.readUint16();
        getRSSI = reader.readUint16();
        getAmperage = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ANALOG{" + "getBatteryVoltage=" + getBatteryVoltage +
                ", getMAhDrawn=" + getMAhDrawn +
                ", getRSSI=" + getRSSI +
                ", getAmperage=" + getAmperage +
                '}';
    }
}
