package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:02
 */
public class MSP_BOARD_ALIGNMENT implements IDecodeable {

    private static final String TAG = "MSP_BOARD_ALIGNMENT";

    public int rollDeciDegrees;
    public int pitchDeciDegrees;
    public int yawDeciDegrees;

    public MSP_BOARD_ALIGNMENT(int rollDeciDegrees, int pitchDeciDegrees, int yawDeciDegrees) {
        Debuger.checkDebugMode();
        this.rollDeciDegrees = rollDeciDegrees;
        this.pitchDeciDegrees = pitchDeciDegrees;
        this.yawDeciDegrees = yawDeciDegrees;
    }

    public MSP_BOARD_ALIGNMENT(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        rollDeciDegrees = reader.readInt16();
        pitchDeciDegrees = reader.readInt16();
        yawDeciDegrees = reader.readInt16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_BOARD_ALIGNMENT{" + "rollDeciDegrees=" + rollDeciDegrees +
                ", pitchDeciDegrees=" + pitchDeciDegrees +
                ", yawDeciDegrees=" + yawDeciDegrees +
                '}';
    }
}
