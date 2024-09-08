package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_SET_OSD_CONFIG implements IEncodeable {

    public boolean onlyPosition;
    public int index;
    public int position;
    public int video_system;
    public int units;
    public int rssi_alarm;
    public int currentBatteryProfileCapacitWarning;
    public int time_alarm;
    public int alt_alarm;
    public int dist_alarm;
    public int neg_alt_alarm;

    public MSP_SET_OSD_CONFIG(boolean onlyPosition, int index, int position, int video_system, int units, int rssi_alarm, int currentBatteryProfileCapacitWarning, int time_alarm, int alt_alarm, int dist_alarm, int neg_alt_alarm) {
        this.onlyPosition = onlyPosition;
        this.index = index;
        this.position = position;
        this.video_system = video_system;
        this.units = units;
        this.rssi_alarm = rssi_alarm;
        this.currentBatteryProfileCapacitWarning = currentBatteryProfileCapacitWarning;
        this.time_alarm = time_alarm;
        this.alt_alarm = alt_alarm;
        this.dist_alarm = dist_alarm;
        this.neg_alt_alarm = neg_alt_alarm;
    }

    public MSP_SET_OSD_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        if (onlyPosition) {
            writer.writeUint8(index)
                    .writeUint16(position);
        } else {
            writer.writeUint8(0xFF)
                    .writeUint8(video_system)
                    .writeUint8(units)
                    .writeUint8(rssi_alarm)
                    .writeUint16(currentBatteryProfileCapacitWarning)
                    .writeUint16(time_alarm)
                    .writeUint16(alt_alarm)
                    .writeUint16(dist_alarm)
                    .writeUint16(neg_alt_alarm);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_OSD_CONFIG{" + "onlyPosition=" + onlyPosition +
                ", index=" + index +
                ", position=" + position +
                ", video_system=" + video_system +
                ", units=" + units +
                ", rssi_alarm=" + rssi_alarm +
                ", currentBatteryProfileCapacitWarning=" + currentBatteryProfileCapacitWarning +
                ", time_alarm=" + time_alarm +
                ", alt_alarm=" + alt_alarm +
                ", dist_alarm=" + dist_alarm +
                ", neg_alt_alarm=" + neg_alt_alarm +
                '}';
    }
}
