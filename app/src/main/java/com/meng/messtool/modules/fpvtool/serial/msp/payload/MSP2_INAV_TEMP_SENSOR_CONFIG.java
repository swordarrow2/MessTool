package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:42
 */
public class MSP2_INAV_TEMP_SENSOR_CONFIG implements IDecodeable {

    private static final String TAG = "MSP2_INAV_TEMP_SENSOR_CONFIG";

    public MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[] msp2_inav_temp_sensor_config_items = new MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[8];

    public MSP2_INAV_TEMP_SENSOR_CONFIG(MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[] msp2_inav_temp_sensor_config_items) {
        Debuger.checkDebugMode();
        this.msp2_inav_temp_sensor_config_items = msp2_inav_temp_sensor_config_items;
    }

    public MSP2_INAV_TEMP_SENSOR_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < msp2_inav_temp_sensor_config_items.length; i++) {
            int type = reader.readUint8();
            int[] addresses = new int[8];
            for (int ii = 0; ii < addresses.length; ii++) {
                addresses[ii] = reader.readInt32();
            }
            int alarm_min = reader.readUint16();
            int alarm_max = reader.readUint16();
            int osdSymbol = reader.readUint8();
            String labels = reader.readCppString(4);
            msp2_inav_temp_sensor_config_items[i] = new MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM(type, addresses, alarm_min, alarm_max, osdSymbol, labels);
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_TEMP_SENSOR_CONFIG{" + "msp2_inav_temp_sensor_config_items=" + Arrays.toString(msp2_inav_temp_sensor_config_items) + '}';
    }

    public static class MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM {

        public int type;
        public int[] addresses = new int[8];
        public int alarm_min;
        public int alarm_max;
        public int osdSymbol;

        public String label;

        public MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM(int type, int[] addresses, int alarm_min, int alarm_max, int osdSymbol, String label) {
            this.type = type;
            this.addresses = addresses;
            this.alarm_min = alarm_min;
            this.alarm_max = alarm_max;
            this.osdSymbol = osdSymbol;
            this.label = label;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM{" + "type=" + type +
                    ", addresses=" + Arrays.toString(addresses) +
                    ", alarm_min=" + alarm_min +
                    ", alarm_max=" + alarm_max +
                    ", osdSymbol=" + osdSymbol +
                    ", label=" + label +
                    '}';
        }
    }
}
