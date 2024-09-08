package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:05
 */
public class MSP2_COMMON_MOTOR_MIXER implements IDecodeable {

    private static final String TAG = "MSP2_COMMON_MOTOR_MIXER";

    public MSP2_COMMON_MOTOR_MIXER_ITEM[] msp2_common_motor_mixer_items = new MSP2_COMMON_MOTOR_MIXER_ITEM[12];

    public MSP2_COMMON_MOTOR_MIXER(MSP2_COMMON_MOTOR_MIXER_ITEM[] msp2_common_motor_mixer_items) {
        Debuger.checkDebugMode();
        this.msp2_common_motor_mixer_items = msp2_common_motor_mixer_items;
    }

    public MSP2_COMMON_MOTOR_MIXER(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_common_motor_mixer_items.length; i++) {
            msp2_common_motor_mixer_items[i] = new MSP2_COMMON_MOTOR_MIXER_ITEM(reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_MOTOR_MIXER{" + "msp2_common_motor_mixer_items=" + Arrays.toString(msp2_common_motor_mixer_items) + '}';
    }

    public static class MSP2_COMMON_MOTOR_MIXER_ITEM {

        public int throttle;
        public int roll;
        public int pitch;
        public int yaw;


        public MSP2_COMMON_MOTOR_MIXER_ITEM(int throttle, int roll, int pitch, int yaw) {
            this.throttle = throttle;
            this.roll = roll;
            this.pitch = pitch;
            this.yaw = yaw;
        }

        @Override
        public String toString() {
            return "MSP2_COMMON_MOTOR_MIXER_ITEM{" + "throttle=" + throttle +
                    ", roll=" + roll +
                    ", pitch=" + pitch +
                    ", yaw=" + yaw +
                    '}';
        }
    }
}
