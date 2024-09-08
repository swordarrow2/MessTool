package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:20
 */
public class MSP2_INAV_OSD_ALARMS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_OSD_ALARMS";

    public int rssi_alarm;
    public int time_alarm;
    public int alt_alarm;
    public int dist_alarm;
    public int neg_alt_alarm;
    public int gforce_alarm;
    public int gforce_axis_alarm_min;
    public int gforce_axis_alarm_max;
    public int current_alarm;
    public int imu_temp_alarm_min;
    public int imu_temp_alarm_max;
    public int baro_temp_alarm_min;
    public int baro_temp_alarm_max;

    public MSP2_INAV_OSD_ALARMS(int rssi_alarm, int time_alarm, int alt_alarm, int dist_alarm, int neg_alt_alarm, int gforce_alarm, int gforce_axis_alarm_min, int gforce_axis_alarm_max, int current_alarm, int imu_temp_alarm_min, int imu_temp_alarm_max, int baro_temp_alarm_min, int baro_temp_alarm_max) {
        Debuger.checkDebugMode();
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

    public MSP2_INAV_OSD_ALARMS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        rssi_alarm = reader.readUint8();
        time_alarm = reader.readUint16();
        alt_alarm = reader.readUint16();
        dist_alarm = reader.readUint16();
        neg_alt_alarm = reader.readUint16();
        gforce_alarm = reader.readUint16();
        gforce_axis_alarm_min = reader.readInt16();
        gforce_axis_alarm_max = reader.readInt16();
        current_alarm = reader.readUint8();
        imu_temp_alarm_min = reader.readInt16();
        imu_temp_alarm_max = reader.readInt16();
        baro_temp_alarm_min = reader.readInt16();
        baro_temp_alarm_max = reader.readInt16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OSD_ALARMS{" + "rssi_alarm=" + rssi_alarm +
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
