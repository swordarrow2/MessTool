package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:03
 */
public class MSP_VOLTAGE_METER_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_VOLTAGE_METER_CONFI";

    public int scale;
    public int cellMin;
    public int cellMax;
    public int cellWarning;

    public MSP_VOLTAGE_METER_CONFIG(int scale, int cellMin, int cellMax, int cellWarning) {
        Debuger.checkDebugMode();
        this.scale = scale;
        this.cellMin = cellMin;
        this.cellMax = cellMax;
        this.cellWarning = cellWarning;
    }

    public MSP_VOLTAGE_METER_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        scale = reader.readUint8();
        cellMin = reader.readUint8();
        cellMax = reader.readUint8();
        cellWarning = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_VOLTAGE_METER_CONFIG{" + "scale=" + scale +
                ", cellMin=" + cellMin +
                ", cellMax=" + cellMax +
                ", cellWarning=" + cellWarning +
                '}';
    }
}
