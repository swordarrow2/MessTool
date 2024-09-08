package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP2_COMMON_SET_SERIAL_CONFIG implements IEncodeable {

    public int identifier;
    public int functionMask;
    public int msp_baudrateIndex;
    public int gps_baudrateIndex;
    public int telemetry_baudrateIndex;
    public int peripheral_baudrateIndex;

    public MSP2_COMMON_SET_SERIAL_CONFIG(int identifier, int functionMask, int msp_baudrateIndex, int gps_baudrateIndex, int telemetry_baudrateIndex, int peripheral_baudrateIndex) {
        this.identifier = identifier;
        this.functionMask = functionMask;
        this.msp_baudrateIndex = msp_baudrateIndex;
        this.gps_baudrateIndex = gps_baudrateIndex;
        this.telemetry_baudrateIndex = telemetry_baudrateIndex;
        this.peripheral_baudrateIndex = peripheral_baudrateIndex;
    }

    public MSP2_COMMON_SET_SERIAL_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(identifier)
                .writeInt32(functionMask)
                .writeUint8(msp_baudrateIndex)
                .writeUint8(gps_baudrateIndex)
                .writeUint8(telemetry_baudrateIndex)
                .writeUint8(peripheral_baudrateIndex);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_SET_SERIAL_CONFIG{" + "identifier=" + identifier +
                ", functionMask=" + functionMask +
                ", msp_baudrateIndex=" + msp_baudrateIndex +
                ", gps_baudrateIndex=" + gps_baudrateIndex +
                ", telemetry_baudrateIndex=" + telemetry_baudrateIndex +
                ", peripheral_baudrateIndex=" + peripheral_baudrateIndex +
                '}';
    }
}
