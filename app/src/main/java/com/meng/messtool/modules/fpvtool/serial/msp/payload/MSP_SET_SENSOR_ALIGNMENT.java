package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_SENSOR_ALIGNMENT implements IEncodeable {

    @InavIgnore
    public int gyro_align;
    @InavIgnore
    public int acc_align;
    public int mag_align;
    public int opflow_align;

    public MSP_SET_SENSOR_ALIGNMENT(int gyro_align, int acc_align, int mag_align, int opflow_align) {
        this.gyro_align = gyro_align;
        this.acc_align = acc_align;
        this.mag_align = mag_align;
        this.opflow_align = opflow_align;
    }

    public MSP_SET_SENSOR_ALIGNMENT() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(gyro_align)
                .writeUint8(acc_align)
                .writeUint8(mag_align)
                .writeUint8(opflow_align);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_SENSOR_ALIGNMENT{" + "gyro_align=" + gyro_align +
                ", acc_align=" + acc_align +
                ", mag_align=" + mag_align +
                ", opflow_align=" + opflow_align +
                '}';
    }
}
