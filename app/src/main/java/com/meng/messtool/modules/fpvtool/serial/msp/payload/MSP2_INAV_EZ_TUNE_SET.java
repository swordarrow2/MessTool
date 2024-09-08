package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:35
 */
public class MSP2_INAV_EZ_TUNE_SET implements IEncodeable {

    public int enabled;
    public int filterHz;
    public int axisRatio;
    public int response;
    public int damping;
    public int stability;
    public int aggressiveness;
    public int rate;
    public int expo;

    public MSP2_INAV_EZ_TUNE_SET(int enabled, int filterHz, int axisRatio, int response, int damping, int stability, int aggressiveness, int rate, int expo) {
        this.enabled = enabled;
        this.filterHz = filterHz;
        this.axisRatio = axisRatio;
        this.response = response;
        this.damping = damping;
        this.stability = stability;
        this.aggressiveness = aggressiveness;
        this.rate = rate;
        this.expo = expo;
    }

    public MSP2_INAV_EZ_TUNE_SET() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(enabled)
                .writeUint16(filterHz)
                .writeUint8(axisRatio)
                .writeUint8(axisRatio)
                .writeUint8(response)
                .writeUint8(damping)
                .writeUint8(stability)
                .writeUint8(aggressiveness)
                .writeUint8(rate)
                .writeUint8(expo);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_EZ_TUNE_SET{" + "enabled=" + enabled +
                ", filterHz=" + filterHz +
                ", axisRatio=" + axisRatio +
                ", response=" + response +
                ", damping=" + damping +
                ", stability=" + stability +
                ", aggressiveness=" + aggressiveness +
                ", rate=" + rate +
                ", expo=" + expo +
                '}';
    }
}
