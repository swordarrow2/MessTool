package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 19:52
 */
public class MSP_ADJUSTMENT_RANGES implements IDecodeable {

    private static final String TAG = "MSP_ADJUSTMENT_RANGES";

    public MSP_ADJUSTMENT_RANGES_ITEM[] msp_adjustment_ranges_items = new MSP_ADJUSTMENT_RANGES_ITEM[20];

    public MSP_ADJUSTMENT_RANGES(MSP_ADJUSTMENT_RANGES_ITEM[] msp_adjustment_ranges_items) {
        Debuger.checkDebugMode();
        this.msp_adjustment_ranges_items = msp_adjustment_ranges_items;
    }

    public MSP_ADJUSTMENT_RANGES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp_adjustment_ranges_items.length; i++) {
            msp_adjustment_ranges_items[i] = new MSP_ADJUSTMENT_RANGES_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_ADJUSTMENT_RANGES{" + "msp_adjustment_ranges_items=" + Arrays.toString(msp_adjustment_ranges_items) + '}';
    }

    public static class MSP_ADJUSTMENT_RANGES_ITEM {

        private static final String TAG = "MSP_ADJUSTMENT_RANGES";

        public int adjustmentIndex;
        public int auxChannelIndex;
        // steps are 25 apart
        // a value of 0 corresponds to a channel value of 900 or less
        // a value of 48 corresponds to a channel value of 2100 or more
        // 48 steps between 900 and 1200
        public int rangeStartStep;
        public int rangeEndStep;

        public int adjustmentFunction;
        public int auxSwitchChannelIndex;

        public MSP_ADJUSTMENT_RANGES_ITEM(int adjustmentIndex, int auxChannelIndex, int rangeStartStep, int rangeEndStep, int adjustmentFunction, int auxSwitchChannelIndex) {
            Debuger.checkDebugMode();
            this.adjustmentIndex = adjustmentIndex;
            this.auxChannelIndex = auxChannelIndex;
            this.rangeStartStep = rangeStartStep;
            this.rangeEndStep = rangeEndStep;
            this.adjustmentFunction = adjustmentFunction;
            this.auxSwitchChannelIndex = auxSwitchChannelIndex;
        }

        @Override
        public String toString() {
            return "MSP_ADJUSTMENT_RANGES_ITEM{" + "adjustmentIndex=" + adjustmentIndex +
                    ", auxChannelIndex=" + auxChannelIndex +
                    ", rangeStartStep=" + rangeStartStep +
                    ", rangeEndStep=" + rangeEndStep +
                    ", adjustmentFunction=" + adjustmentFunction +
                    ", auxSwitchChannelIndex=" + auxSwitchChannelIndex +
                    '}';
        }
    }
}
