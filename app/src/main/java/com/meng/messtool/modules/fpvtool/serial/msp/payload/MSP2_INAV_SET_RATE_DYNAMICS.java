package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:35
 */
public class MSP2_INAV_SET_RATE_DYNAMICS implements IEncodeable {

    public int sensitivityCenter;
    public int sensitivityEnd;
    public int correctionCenter;
    public int correctionEnd;
    public int weightCenter;
    public int weightEnd;

    public MSP2_INAV_SET_RATE_DYNAMICS(int sensitivityCenter, int sensitivityEnd, int correctionCenter, int correctionEnd, int weightCenter, int weightEnd) {
        this.sensitivityCenter = sensitivityCenter;
        this.sensitivityEnd = sensitivityEnd;
        this.correctionCenter = correctionCenter;
        this.correctionEnd = correctionEnd;
        this.weightCenter = weightCenter;
        this.weightEnd = weightEnd;
    }

    public MSP2_INAV_SET_RATE_DYNAMICS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(sensitivityCenter)
                .writeUint8(sensitivityEnd)
                .writeUint8(correctionCenter)
                .writeUint8(correctionEnd)
                .writeUint8(weightCenter)
                .writeUint8(weightEnd);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_RATE_DYNAMICS{" + "sensitivityCenter=" + sensitivityCenter +
                ", sensitivityEnd=" + sensitivityEnd +
                ", correctionCenter=" + correctionCenter +
                ", correctionEnd=" + correctionEnd +
                ", weightCenter=" + weightCenter +
                ", weightEnd=" + weightEnd +
                '}';
    }
}
