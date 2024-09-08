package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP_SET_SERVO_MIX_RULE implements IEncodeable {

    public int index;
    public int targetChannel;
    public int inputSource;
    public int rate;
    public int speed;
    @InavIgnore
    public int min;
    @InavIgnore
    public int max;
    @InavIgnore
    public int box;

    public MSP_SET_SERVO_MIX_RULE(int index, int targetChannel, int inputSource, int rate, int speed, int min, int max, int box) {
        this.index = index;
        this.targetChannel = targetChannel;
        this.inputSource = inputSource;
        this.rate = rate;
        this.speed = speed;
        this.min = min;
        this.max = max;
        this.box = box;
    }

    public MSP_SET_SERVO_MIX_RULE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(targetChannel)
                .writeUint8(inputSource)
                .writeUint16(rate)
                .writeUint8(speed)
                .writeUint8(min)
                .writeUint8(max)
                .writeUint8(box);
        return writer.encode();
    }

    @Override
    public String toString() {
        String sb = "MSP_SET_SERVO_MIX_RULE{" + "index=" + index +
                ", targetChannel=" + targetChannel +
                ", inputSource=" + inputSource +
                ", rate=" + rate +
                ", speed=" + speed +
                ", min=" + min +
                ", max=" + max +
                ", box=" + box +
                '}';
        return sb;
    }
}
