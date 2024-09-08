package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:42
 */
public class MSP2_INAV_SERVO_MIXER implements IDecodeable {

    private static final String TAG = "MSP2_INAV_SERVO_MIXER";

    public MSP2_INAV_SERVO_MIXER_ITEM[] msp2_inav_servo_mixer_item = new MSP2_INAV_SERVO_MIXER_ITEM[1];

    public MSP2_INAV_SERVO_MIXER(MSP2_INAV_SERVO_MIXER_ITEM[] msp2_inav_servo_mixer_item) {
        Debuger.checkDebugMode();
        this.msp2_inav_servo_mixer_item = msp2_inav_servo_mixer_item;
    }

    public MSP2_INAV_SERVO_MIXER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_servo_mixer_item.length; i++) {
            msp2_inav_servo_mixer_item[i] = new MSP2_INAV_SERVO_MIXER_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint16(), reader.readUint8(), reader.readUint8());
        }
        if (data.length % (2 * 16 * 6) != 0) {
            if (Debuger.isDebugMode()) {
                throw new IllegalStateException("data.length % (2 * 16 * 6) != 0");
            }
        }
        if (data.length > 2 * 16 * 6) {
            //todo muilty profile
        } else {

        }
        reader.checkFinish();
        // assert only 1: MAX_MIXER_PROFILE_COUNT
        //not check end
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SERVO_MIXER{" + "msp2_inav_servo_mixer_item=" + Arrays.toString(msp2_inav_servo_mixer_item) + '}';
    }

    public static class MSP2_INAV_SERVO_MIXER_ITEM {
        public int targetChannel;
        public int inputSource;
        public int rate;
        public int speed;
        public int conditionId;

        public MSP2_INAV_SERVO_MIXER_ITEM(int targetChannel, int inputSource, int rate, int speed, int conditionId) {
            this.targetChannel = targetChannel;
            this.inputSource = inputSource;
            this.rate = rate;
            this.speed = speed;
            this.conditionId = conditionId;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_SERVO_MIXER_ITEM{" + "targetChannel=" + targetChannel +
                    ", inputSource=" + inputSource +
                    ", rate=" + rate +
                    ", speed=" + speed +
                    ", conditionId=" + conditionId +
                    '}';
        }
    }
}
