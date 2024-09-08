package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:33
 */
public class MSP2_INAV_SET_TEMP_SENSOR_CONFIG implements IEncodeable {

    public MSP2_INAV_TEMP_SENSOR_CONFIG.MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[] msp2_inav_temp_sensor_config_items = new MSP2_INAV_TEMP_SENSOR_CONFIG.MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[8];

    public MSP2_INAV_SET_TEMP_SENSOR_CONFIG(MSP2_INAV_TEMP_SENSOR_CONFIG.MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM[] msp2_inav_temp_sensor_config_items) {
        this.msp2_inav_temp_sensor_config_items = msp2_inav_temp_sensor_config_items;
    }

    public MSP2_INAV_SET_TEMP_SENSOR_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        for (MSP2_INAV_TEMP_SENSOR_CONFIG.MSP2_INAV_TEMP_SENSOR_CONFIG_ITEM item : msp2_inav_temp_sensor_config_items) {
            writer.writeUint8(item.type);
            for (int addr : item.addresses) {
                writer.writeUint8(addr);
            }
            writer.writeUint16(item.alarm_min)
                    .writeUint16(item.alarm_max)
                    .writeCppString(item.label);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_TEMP_SENSOR_CONFIG{" + "msp2_inav_temp_sensor_config_items=" + Arrays.toString(msp2_inav_temp_sensor_config_items) + '}';
    }
}
