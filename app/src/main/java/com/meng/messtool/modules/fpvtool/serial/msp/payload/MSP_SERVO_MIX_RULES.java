package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:30
 */
public class MSP_SERVO_MIX_RULES implements IDecodeable {

    private static final String TAG = "MSP_SERVO_MIX_RULES";

    public MSP_SERVO_MIX_RULES_ITEM[] mspServoMixRulesItems = new MSP_SERVO_MIX_RULES_ITEM[32];

    public MSP_SERVO_MIX_RULES(MSP_SERVO_MIX_RULES_ITEM[] mspServoMixRulesItems) {
        Debuger.checkDebugMode();
        this.mspServoMixRulesItems = mspServoMixRulesItems;
    }

    public MSP_SERVO_MIX_RULES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < mspServoMixRulesItems.length; i++) {
            mspServoMixRulesItems[i] = new MSP_SERVO_MIX_RULES_ITEM(reader.readUint8(), reader.readUint8(), reader.readInt16(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SERVO_MIX_RULES{" + "mspServoMixRulesItems=" + Arrays.toString(mspServoMixRulesItems) + '}';
    }

    public static class MSP_SERVO_MIX_RULES_ITEM {
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

        public MSP_SERVO_MIX_RULES_ITEM(int targetChannel, int inputSource, int rate, int speed, int min, int max, int box) {
            this.targetChannel = targetChannel;
            this.inputSource = inputSource;
            this.rate = rate;
            this.speed = speed;
            this.min = min;
            this.max = max;
            this.box = box;
        }

        @Override
        public String toString() {
            return "MSP_SERVO_MIX_RULES_ITEM{" + "targetChannel=" + targetChannel +
                    ", inputSource=" + inputSource +
                    ", rate=" + rate +
                    ", speed=" + speed +
                    ", min=" + min +
                    ", max=" + max +
                    ", box=" + box +
                    '}';
        }
    }
}
