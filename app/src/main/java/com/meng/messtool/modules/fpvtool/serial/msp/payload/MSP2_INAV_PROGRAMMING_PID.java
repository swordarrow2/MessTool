package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:55
 */
public class MSP2_INAV_PROGRAMMING_PID implements IDecodeable {

    private static final String TAG = "MSP2_INAV_PROGRAMMING_PID";

    public MSP2_INAV_PROGRAMMING_PID_ITEM[] msp2_inav_programming_pid_items = new MSP2_INAV_PROGRAMMING_PID_ITEM[8];

    public MSP2_INAV_PROGRAMMING_PID(MSP2_INAV_PROGRAMMING_PID_ITEM[] msp2_inav_programming_pid_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_programming_pid_items = msp2_inav_programming_pid_items;
    }

    public MSP2_INAV_PROGRAMMING_PID(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_programming_pid_items.length; i++) {
            msp2_inav_programming_pid_items[i] = new MSP2_INAV_PROGRAMMING_PID_ITEM(reader.readUint8(), reader.readUint8(), reader.readInt32(), reader.readUint8(), reader.readInt32(), reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_PROGRAMMING_PID{" + "msp2_inav_programming_pid_items=" + Arrays.toString(msp2_inav_programming_pid_items) + '}';
    }

    public static class MSP2_INAV_PROGRAMMING_PID_ITEM {

        public int enabled;
        public int setpointType;
        public int setpointValue;
        public int measurementType;
        public int measurementValue;
        public int gainsP;
        public int gainsI;
        public int gainsD;
        public int gainsFF;

        public MSP2_INAV_PROGRAMMING_PID_ITEM(int enabled, int setpointType, int setpointValue, int measurementType, int measurementValue, int gainsP, int gainsI, int gainsD, int gainsFF) {
            this.enabled = enabled;
            this.setpointType = setpointType;
            this.setpointValue = setpointValue;
            this.measurementType = measurementType;
            this.measurementValue = measurementValue;
            this.gainsP = gainsP;
            this.gainsI = gainsI;
            this.gainsD = gainsD;
            this.gainsFF = gainsFF;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_PROGRAMMING_PID_ITEM{" + "enabled=" + enabled +
                    ", setpointType=" + setpointType +
                    ", setpointValue=" + setpointValue +
                    ", measurementType=" + measurementType +
                    ", measurementValue=" + measurementValue +
                    ", gainsP=" + gainsP +
                    ", gainsI=" + gainsI +
                    ", gainsD=" + gainsD +
                    ", gainsFF=" + gainsFF +
                    '}';
        }
    }
}
