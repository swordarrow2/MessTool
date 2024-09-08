package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 18:52
 */
public class MSP2_PID implements IDecodeable {

    private static final String TAG = "MSP2_PID";

    /* PID              MC      FW
    PID_ROLL,       //   +       +
    PID_PITCH,      //   +       +
    PID_YAW,        //   +       +
    PID_POS_Z,      //   +       +
    PID_POS_XY,     //   +       +
    PID_VEL_XY,     //   +       n/a
    PID_SURFACE,    //   n/a     n/a
    PID_LEVEL,      //   +       +
    PID_HEADING,    //   +       +
    PID_VEL_Z,      //   +       n/a
    PID_POS_HEADING,//   n/a     +*/


    public MSP2_PID_ITEM[] msp2_pid_items = new MSP2_PID_ITEM[9];

    public MSP2_PID(MSP2_PID_ITEM[] msp2_pid_items) {
        this.msp2_pid_items = msp2_pid_items;
    }

    public MSP2_PID(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);

        for (int i = 0; i < msp2_pid_items.length; i++) {
            msp2_pid_items[i] = new MSP2_PID_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_PID{" + "msp2_pid_items=" + Arrays.toString(msp2_pid_items) + '}';
    }

    public static class MSP2_PID_ITEM {

        public int p;
        public int i;
        public int d;
        public int ff;

        public MSP2_PID_ITEM(int p, int i, int d, int ff) {
            this.p = p;
            this.i = i;
            this.d = d;
            this.ff = ff;
        }

        @Override
        public String toString() {
            return "MSP2_PID_ITEM{" + "p=" + p +
                    ", LED_MODE_COUNT=" + i +
                    ", d=" + d +
                    ", ff=" + ff +
                    '}';
        }
    }
}
