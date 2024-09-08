package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:24
 */
public class MSP2_INAV_SET_BATTERY_CONFIG implements IEncodeable {

    public int scale;
    public int voltageSource;
    public int cells;
    public int cellDetect;
    public int cellMin;
    public int cellMax;
    public int cellWarning;

    public MSP2_INAV_SET_BATTERY_CONFIG(int scale, int voltageSource, int cells, int cellDetect, int cellMin, int cellMax, int cellWarning) {
        this.scale = scale;
        this.voltageSource = voltageSource;
        this.cells = cells;
        this.cellDetect = cellDetect;
        this.cellMin = cellMin;
        this.cellMax = cellMax;
        this.cellWarning = cellWarning;
    }

    public MSP2_INAV_SET_BATTERY_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(scale)
                .writeUint8(voltageSource)
                .writeUint8(cells)
                .writeUint16(cellDetect)
                .writeUint16(cellMin)
                .writeUint16(cellMax)
                .writeUint16(cellWarning);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_BATTERY_CONFIG{" + "scale=" + scale +
                ", voltageSource=" + voltageSource +
                ", cells=" + cells +
                ", cellDetect=" + cellDetect +
                ", cellMin=" + cellMin +
                ", cellMax=" + cellMax +
                ", cellWarning=" + cellWarning +
                '}';
    }
}
