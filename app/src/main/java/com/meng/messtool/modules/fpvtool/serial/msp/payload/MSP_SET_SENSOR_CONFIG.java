package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_SENSOR_CONFIG implements IEncodeable {

    public int acc_hardware;
    public int baro_hardware;
    public int mag_hardware;
    public int pitot_hardware;
    public int rangefinder_hardware;
    public int opflow_hardware;

    public MSP_SET_SENSOR_CONFIG(int acc_hardware, int baro_hardware, int mag_hardware, int pitot_hardware, int rangefinder_hardware, int opflow_hardware) {
        this.acc_hardware = acc_hardware;
        this.baro_hardware = baro_hardware;
        this.mag_hardware = mag_hardware;
        this.pitot_hardware = pitot_hardware;
        this.rangefinder_hardware = rangefinder_hardware;
        this.opflow_hardware = opflow_hardware;
    }

    public MSP_SET_SENSOR_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(acc_hardware)
                .writeUint8(baro_hardware)
                .writeUint8(mag_hardware)
                .writeUint8(pitot_hardware)
                .writeUint8(rangefinder_hardware)
                .writeUint8(opflow_hardware);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_SENSOR_CONFIG{" + "acc_hardware=" + acc_hardware +
                ", baro_hardware=" + baro_hardware +
                ", mag_hardware=" + mag_hardware +
                ", pitot_hardware=" + pitot_hardware +
                ", rangefinder_hardware=" + rangefinder_hardware +
                ", opflow_hardware=" + opflow_hardware +
                '}';
    }
}
