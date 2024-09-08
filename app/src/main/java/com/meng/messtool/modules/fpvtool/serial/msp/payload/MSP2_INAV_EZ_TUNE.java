package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:49
 */
public class MSP2_INAV_EZ_TUNE implements IDecodeable {

    private static final String TAG = "MSP2_INAV_EZ_TUNE";

    public int enabled;
    public int filterHz;
    public int axisRatio;
    public int response;
    public int damping;
    public int stability;
    public int aggressiveness;
    public int rate;
    public int expo;

    public MSP2_INAV_EZ_TUNE(int enabled, int filterHz, int axisRatio, int response, int damping, int stability, int aggressiveness, int rate, int expo) {
        Debuger.checkDebugMode();
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

    public MSP2_INAV_EZ_TUNE(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        enabled = reader.readUint8();
        filterHz = reader.readUint16();
        axisRatio = reader.readUint8();
        response = reader.readUint8();
        damping = reader.readUint8();
        stability = reader.readUint8();
        aggressiveness = reader.readUint8();
        rate = reader.readUint8();
        expo = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_EZ_TUNE{" + "enabled=" + enabled +
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
