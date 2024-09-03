package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:27
 */
public class MSP2_COMMON_SERIAL_CONFIG {

    public int identifier;
    public int functionMask;
    public int msp_baudrateIndex;
    public int gps_baudrateIndex;
    public int telemetry_baudrateIndex;
    public int peripheral_baudrateIndex;

    public MSP2_COMMON_SERIAL_CONFIG(int identifier, int functionMask, int msp_baudrateIndex, int gps_baudrateIndex, int telemetry_baudrateIndex, int peripheral_baudrateIndex) {
        this.identifier = identifier;
        this.functionMask = functionMask;
        this.msp_baudrateIndex = msp_baudrateIndex;
        this.gps_baudrateIndex = gps_baudrateIndex;
        this.telemetry_baudrateIndex = telemetry_baudrateIndex;
        this.peripheral_baudrateIndex = peripheral_baudrateIndex;
    }
}
