package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_INAV_OSD_SET_ALARMS implements IEncodeable {

    public int rssi_alarm;
    public int time_alarm;
    public int alt_alarm;
    public int dist_alarm;
    public int neg_alt_alarm;
    public float gforce_alarm;
    public float gforce_axis_alarm_min;
    public float gforce_axis_alarm_max;
    public int current_alarm;
    public int imu_temp_alarm_min;
    public int imu_temp_alarm_max;
    public int baro_temp_alarm_min;
    public int baro_temp_alarm_max;

    public MSP2_INAV_OSD_SET_ALARMS(int rssi_alarm, int time_alarm, int alt_alarm, int dist_alarm, int neg_alt_alarm, float gforce_alarm, float gforce_axis_alarm_min, float gforce_axis_alarm_max, int current_alarm, int imu_temp_alarm_min, int imu_temp_alarm_max, int baro_temp_alarm_min, int baro_temp_alarm_max) {
        this.rssi_alarm = rssi_alarm;
        this.time_alarm = time_alarm;
        this.alt_alarm = alt_alarm;
        this.dist_alarm = dist_alarm;
        this.neg_alt_alarm = neg_alt_alarm;
        this.gforce_alarm = gforce_alarm;
        this.gforce_axis_alarm_min = gforce_axis_alarm_min;
        this.gforce_axis_alarm_max = gforce_axis_alarm_max;
        this.current_alarm = current_alarm;
        this.imu_temp_alarm_min = imu_temp_alarm_min;
        this.imu_temp_alarm_max = imu_temp_alarm_max;
        this.baro_temp_alarm_min = baro_temp_alarm_min;
        this.baro_temp_alarm_max = baro_temp_alarm_max;
    }

    public MSP2_INAV_OSD_SET_ALARMS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(rssi_alarm)
                .writeUint16(time_alarm)
                .writeUint16(alt_alarm)
                .writeUint16(dist_alarm)
                .writeUint16(neg_alt_alarm)
                .writeFloat(gforce_alarm)
                .writeFloat(gforce_axis_alarm_min)
                .writeFloat(gforce_axis_alarm_max)
                .writeUint8(current_alarm)
                .writeInt16(imu_temp_alarm_min)
                .writeInt16(imu_temp_alarm_max)
                .writeInt16(baro_temp_alarm_min)
                .writeInt16(baro_temp_alarm_max);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OSD_SET_ALARMS{" + "rssi_alarm=" + rssi_alarm +
                ", time_alarm=" + time_alarm +
                ", alt_alarm=" + alt_alarm +
                ", dist_alarm=" + dist_alarm +
                ", neg_alt_alarm=" + neg_alt_alarm +
                ", gforce_alarm=" + gforce_alarm +
                ", gforce_axis_alarm_min=" + gforce_axis_alarm_min +
                ", gforce_axis_alarm_max=" + gforce_axis_alarm_max +
                ", current_alarm=" + current_alarm +
                ", imu_temp_alarm_min=" + imu_temp_alarm_min +
                ", imu_temp_alarm_max=" + imu_temp_alarm_max +
                ", baro_temp_alarm_min=" + baro_temp_alarm_min +
                ", baro_temp_alarm_max=" + baro_temp_alarm_max +
                '}';
    }
}
