package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_VOLTAGE_METER_CONFIG implements IEncodeable {

    public int scale;
    public int cellMin;
    public int cellMax;
    public int cellWarning;

    public MSP_SET_VOLTAGE_METER_CONFIG(int scale, int cellMin, int cellMax, int cellWarning) {
        this.scale = scale;
        this.cellMin = cellMin;
        this.cellMax = cellMax;
        this.cellWarning = cellWarning;
    }

    public MSP_SET_VOLTAGE_METER_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(scale)
                .writeUint8(cellMin)
                .writeUint8(cellMax)
                .writeUint8(cellWarning);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_VOLTAGE_METER_CONFIG{" + "scale=" + scale +
                ", cellMin=" + cellMin +
                ", cellMax=" + cellMax +
                ", cellWarning=" + cellWarning +
                '}';
    }
}
