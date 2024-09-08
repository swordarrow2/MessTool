package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 21:27
 */
public class MSP2_COMMON_SERIAL_CONFIG implements IDecodeable {

    private static final String TAG = "MSP2_COMMON_SERIAL_CONFIG";

    public MSP2_COMMON_SERIAL_CONFIG_ITEM[] msp2_common_serial_config_items = new MSP2_COMMON_SERIAL_CONFIG_ITEM[6];

    public MSP2_COMMON_SERIAL_CONFIG(MSP2_COMMON_SERIAL_CONFIG_ITEM[] msp2_common_serial_config_items) {
        Debuger.checkDebugMode();
        this.msp2_common_serial_config_items = msp2_common_serial_config_items;
    }

    public MSP2_COMMON_SERIAL_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_common_serial_config_items.length; i++) {
            msp2_common_serial_config_items[i] = new MSP2_COMMON_SERIAL_CONFIG_ITEM(reader.readUint8(), reader.readInt32(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_COMMON_SERIAL_CONFIG{" + "msp2_common_serial_config_items=" + Arrays.toString(msp2_common_serial_config_items) + '}';
    }

    public static class MSP2_COMMON_SERIAL_CONFIG_ITEM {

        public int identifier;
        public int functionMask;
        public int msp_baudrateIndex;
        public int gps_baudrateIndex;
        public int telemetry_baudrateIndex;
        public int peripheral_baudrateIndex;

        public MSP2_COMMON_SERIAL_CONFIG_ITEM(int identifier, int functionMask, int msp_baudrateIndex, int gps_baudrateIndex, int telemetry_baudrateIndex, int peripheral_baudrateIndex) {
            this.identifier = identifier;
            this.functionMask = functionMask;
            this.msp_baudrateIndex = msp_baudrateIndex;
            this.gps_baudrateIndex = gps_baudrateIndex;
            this.telemetry_baudrateIndex = telemetry_baudrateIndex;
            this.peripheral_baudrateIndex = peripheral_baudrateIndex;
        }

        @Override
        public String toString() {
            return "MSP2_COMMON_SERIAL_CONFIG_ITEM{" + "identifier=" + identifier +
                    ", functionMask=" + Integer.toBinaryString(functionMask) +
                    ", msp_baudrateIndex=" + msp_baudrateIndex +
                    ", gps_baudrateIndex=" + gps_baudrateIndex +
                    ", telemetry_baudrateIndex=" + telemetry_baudrateIndex +
                    ", peripheral_baudrateIndex=" + peripheral_baudrateIndex +
                    '}';
        }
    }

}
