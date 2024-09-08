package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:50
 */
public class MSP_SENSOR_ALIGNMENT implements IDecodeable {

    private static final String TAG = "MSP_SENSOR_ALIGNMENT";

    @InavIgnore
    public int gyro_align;
    @InavIgnore
    public int acc_align;
    public int mag_align;
    public int opflow_align;

    public MSP_SENSOR_ALIGNMENT(int gyro_align, int acc_align, int mag_align, int opflow_align) {
        Debuger.checkDebugMode();
        this.gyro_align = gyro_align;
        this.acc_align = acc_align;
        this.mag_align = mag_align;
        this.opflow_align = opflow_align;
    }

    public MSP_SENSOR_ALIGNMENT(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        gyro_align = reader.readInt8();
        acc_align = reader.readInt8();
        mag_align = reader.readInt8();
        opflow_align = reader.readInt8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SENSOR_ALIGNMENT{" + "gyro_align=" + gyro_align +
                ", acc_align=" + acc_align +
                ", mag_align=" + mag_align +
                ", opflow_align=" + opflow_align +
                '}';
    }
}
