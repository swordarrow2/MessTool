package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:49
 */
public class MSP_COMP_GPS implements IDecodeable {

    private static final String TAG = "MSP_COMP_GPS";

    public int GPS_distanceToHome;      // distance to home point in meters
    public int GPS_directionToHome;     // direction to home point in degrees
    public int gpsHeartbeat;            // 0 or 1

    public MSP_COMP_GPS(int GPS_distanceToHome, int GPS_directionToHome, int gpsHeartbeat) {
        Debuger.checkDebugMode();
        this.GPS_distanceToHome = GPS_distanceToHome;
        this.GPS_directionToHome = GPS_directionToHome;
        this.gpsHeartbeat = gpsHeartbeat;
    }

    public MSP_COMP_GPS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        GPS_distanceToHome = reader.readUint16();
        GPS_directionToHome = reader.readUint16();
        gpsHeartbeat = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_COMP_GPS{" + "GPS_distanceToHome=" + GPS_distanceToHome +
                ", GPS_directionToHome=" + GPS_directionToHome +
                ", gpsHeartbeat=" + gpsHeartbeat +
                '}';
    }
}
