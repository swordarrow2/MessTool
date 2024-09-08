package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 19:47
 */
public class MSP_MODE_RANGES implements IDecodeable {

    private static final String TAG = "MSP_MODE_RANGES";

    public MSP_MODE_RANGES_ITEM[] msp_mode_ranges_items = new MSP_MODE_RANGES_ITEM[40];

    public MSP_MODE_RANGES(MSP_MODE_RANGES_ITEM[] msp_mode_ranges_items) {
        Debuger.checkDebugMode();
        this.msp_mode_ranges_items = msp_mode_ranges_items;
    }

    public MSP_MODE_RANGES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_mode_ranges_items.length; i++) {
            msp_mode_ranges_items[i] = new MSP_MODE_RANGES_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_MODE_RANGES{" + "msp_mode_ranges_items=" + Arrays.toString(msp_mode_ranges_items) + '}';
    }

    public static class MSP_MODE_RANGES_ITEM {

        public int permanentId;
        public int auxChannelIndex;
        public int startStep;
        public int endStep;

        public MSP_MODE_RANGES_ITEM(int permanentId, int auxChannelIndex, int startStep, int endStep) {
            Debuger.checkDebugMode();
            this.permanentId = permanentId;
            this.auxChannelIndex = auxChannelIndex;
            this.startStep = startStep;
            this.endStep = endStep;
        }

        @Override
        public String toString() {
            return "MSP_MODE_RANGES_ITEM{" + "permanentId=" + permanentId +
                    ", auxChannelIndex=" + auxChannelIndex +
                    ", startStep=" + startStep +
                    ", endStep=" + endStep +
                    '}';
        }
    }
}
