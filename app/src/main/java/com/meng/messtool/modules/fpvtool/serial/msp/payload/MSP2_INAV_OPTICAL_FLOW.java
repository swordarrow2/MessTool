package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:30
 */
public class MSP2_INAV_OPTICAL_FLOW implements IDecodeable {

    private static final String TAG = "MSP2_INAV_OPTICAL_FLOW";

    public int rawQuality;
    public int flowRateX;
    public int flowRateY;
    public int bodyRateX;
    public int bodyRateY;

    public MSP2_INAV_OPTICAL_FLOW(int rawQuality, int flowRateX, int flowRateY, int bodyRateX, int bodyRateY) {
        Debuger.checkDebugMode();
        this.rawQuality = rawQuality;
        this.flowRateX = flowRateX;
        this.flowRateY = flowRateY;
        this.bodyRateX = bodyRateX;
        this.bodyRateY = bodyRateY;
    }

    public MSP2_INAV_OPTICAL_FLOW(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        rawQuality = reader.readUint8();
        flowRateX = reader.readUint16();
        flowRateY = reader.readUint16();
        bodyRateX = reader.readUint16();
        bodyRateY = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OPTICAL_FLOW{" + "rawQuality=" + rawQuality +
                ", flowRateX=" + flowRateX +
                ", flowRateY=" + flowRateY +
                ", bodyRateX=" + bodyRateX +
                ", bodyRateY=" + bodyRateY +
                '}';
    }
}
