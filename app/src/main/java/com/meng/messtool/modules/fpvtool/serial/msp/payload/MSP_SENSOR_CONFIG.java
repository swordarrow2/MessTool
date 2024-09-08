package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:57
 */
public class MSP_SENSOR_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_SENSOR_CONFIG";

    public int acc_hardware;
    public int baro_hardware;
    public int mag_hardware;
    public int pitot_hardware;
    public int rangefinder_hardware;
    public int opflow_hardware;

    public MSP_SENSOR_CONFIG(int acc_hardware, int baro_hardware, int mag_hardware, int pitot_hardware, int rangefinder_hardware, int opflow_hardware) {
        Debuger.checkDebugMode();
        this.acc_hardware = acc_hardware;
        this.baro_hardware = baro_hardware;
        this.mag_hardware = mag_hardware;
        this.pitot_hardware = pitot_hardware;
        this.rangefinder_hardware = rangefinder_hardware;
        this.opflow_hardware = opflow_hardware;
    }

    public MSP_SENSOR_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        acc_hardware = reader.readInt8();
        baro_hardware = reader.readInt8();
        mag_hardware = reader.readInt8();
        pitot_hardware = reader.readInt8();
        rangefinder_hardware = reader.readInt8();
        opflow_hardware = reader.readInt8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SENSOR_CONFIG{" + "acc_hardware=" + acc_hardware +
                ", baro_hardware=" + baro_hardware +
                ", mag_hardware=" + mag_hardware +
                ", pitot_hardware=" + pitot_hardware +
                ", rangefinder_hardware=" + rangefinder_hardware +
                ", opflow_hardware=" + opflow_hardware +
                '}';
    }
}
