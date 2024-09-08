package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP2_INAV_SET_SERVO_MIXER implements IEncodeable {

    public int index;
    public int targetChannel;
    public int inputSource;
    public int rate;
    public int speed;
    public int conditionId;

    public MSP2_INAV_SET_SERVO_MIXER(int index, int targetChannel, int inputSource, int rate, int speed, int conditionId) {
        this.index = index;
        this.targetChannel = targetChannel;
        this.inputSource = inputSource;
        this.rate = rate;
        this.speed = speed;
        this.conditionId = conditionId;
    }

    public MSP2_INAV_SET_SERVO_MIXER() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(targetChannel)
                .writeUint8(inputSource)
                .writeUint16(rate)
                .writeUint8(speed)
                .writeUint8(conditionId);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_SERVO_MIXER{" + "index=" + index +
                ", targetChannel=" + targetChannel +
                ", inputSource=" + inputSource +
                ", rate=" + rate +
                ", speed=" + speed +
                ", conditionId=" + conditionId +
                '}';
    }
}
