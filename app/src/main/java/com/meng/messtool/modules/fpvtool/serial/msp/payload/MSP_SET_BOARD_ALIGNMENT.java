package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:30
 */
public class MSP_SET_BOARD_ALIGNMENT implements IEncodeable {

    public int rollDeciDegrees;
    public int pitchDeciDegrees;
    public int yawDeciDegrees;

    public MSP_SET_BOARD_ALIGNMENT(int rollDeciDegrees, int pitchDeciDegrees, int yawDeciDegrees) {
        this.rollDeciDegrees = rollDeciDegrees;
        this.pitchDeciDegrees = pitchDeciDegrees;
        this.yawDeciDegrees = yawDeciDegrees;
    }

    public MSP_SET_BOARD_ALIGNMENT() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(rollDeciDegrees)
                .writeUint16(pitchDeciDegrees)
                .writeUint16(yawDeciDegrees);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_BOARD_ALIGNMENT{" + "rollDeciDegrees=" + rollDeciDegrees +
                ", pitchDeciDegrees=" + pitchDeciDegrees +
                ", yawDeciDegrees=" + yawDeciDegrees +
                '}';
    }
}
