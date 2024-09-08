package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:42
 */
public class MSP_OSD_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_OSD_CONFIG";

    public int osdDriver;       // 0:none   1:max7456
    public int video_system;
    public int units;
    public int rssi_alarm;
    public int currentBatteryProfileCapacitWarning;
    public int time_alarm;
    public int alt_alarm;
    public int dist_alarm;
    public int neg_alt_alarm;
    public int[] itemPos;//151 for inav7

    public MSP_OSD_CONFIG(int osdDriver, int video_system, int units, int rssi_alarm, int currentBatteryProfileCapacitWarning, int time_alarm, int alt_alarm, int dist_alarm, int neg_alt_alarm, int[] itemPos) {
        Debuger.checkDebugMode();
        this.osdDriver = osdDriver;
        this.video_system = video_system;
        this.units = units;
        this.rssi_alarm = rssi_alarm;
        this.currentBatteryProfileCapacitWarning = currentBatteryProfileCapacitWarning;
        this.time_alarm = time_alarm;
        this.alt_alarm = alt_alarm;
        this.dist_alarm = dist_alarm;
        this.neg_alt_alarm = neg_alt_alarm;
        this.itemPos = itemPos;
    }

    public MSP_OSD_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        osdDriver = reader.readUint8();
        if (osdDriver == 1) {
            video_system = reader.readUint8();
            units = reader.readUint8();
            rssi_alarm = reader.readUint8();
            currentBatteryProfileCapacitWarning = reader.readUint16();
            time_alarm = reader.readUint16();
            alt_alarm = reader.readUint16();
            dist_alarm = reader.readUint16();
            neg_alt_alarm = reader.readUint16();
            itemPos = new int[151];    //151 for inav7
            for (int i = 0; i < itemPos.length; i++) {
                itemPos[i] = reader.readUint16();
            }
            reader.checkFinish();
        } else {
            reader.checkFinish();
        }
    }

    @Override
    public String toString() {
        return "MSP_OSD_CONFIG{" + "osdDriver=" + osdDriver +
                ", video_system=" + video_system +
                ", units=" + units +
                ", rssi_alarm=" + rssi_alarm +
                ", currentBatteryProfileCapacitWarning=" + currentBatteryProfileCapacitWarning +
                ", time_alarm=" + time_alarm +
                ", alt_alarm=" + alt_alarm +
                ", dist_alarm=" + dist_alarm +
                ", neg_alt_alarm=" + neg_alt_alarm +
                ", itemPos=" + Arrays.toString(itemPos) +
                '}';
    }
}
