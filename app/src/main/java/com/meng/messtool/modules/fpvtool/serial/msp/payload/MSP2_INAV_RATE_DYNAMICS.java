package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:50
 */
public class MSP2_INAV_RATE_DYNAMICS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_RATE_DYNAMICS";

    public int sensitivityCenter;
    public int sensitivityEnd;
    public int correctionCenter;
    public int correctionEnd;
    public int weightCenter;
    public int weightEnd;

    public MSP2_INAV_RATE_DYNAMICS(int sensitivityCenter, int sensitivityEnd, int correctionCenter, int correctionEnd, int weightCenter, int weightEnd) {
        Debuger.checkDebugMode();
        this.sensitivityCenter = sensitivityCenter;
        this.sensitivityEnd = sensitivityEnd;
        this.correctionCenter = correctionCenter;
        this.correctionEnd = correctionEnd;
        this.weightCenter = weightCenter;
        this.weightEnd = weightEnd;
    }

    public MSP2_INAV_RATE_DYNAMICS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        sensitivityCenter = reader.readUint8();
        sensitivityEnd = reader.readUint8();
        correctionCenter = reader.readUint8();
        correctionEnd = reader.readUint8();
        weightCenter = reader.readUint8();
        weightEnd = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_RATE_DYNAMICS{" + "sensitivityCenter=" + sensitivityCenter +
                ", sensitivityEnd=" + sensitivityEnd +
                ", correctionCenter=" + correctionCenter +
                ", correctionEnd=" + correctionEnd +
                ", weightCenter=" + weightCenter +
                ", weightEnd=" + weightEnd +
                '}';
    }
}
