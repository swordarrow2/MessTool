package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 0:10
 */
public class MSP_WP_GETINFO implements IDecodeable {

    private static final String TAG = "MSP_WP_GETINFO";

    public int zero;
    public int navMaxWaypoints;
    public int isWaypointListValid;
    public int getWaypointCount;

    public MSP_WP_GETINFO(int zero, int navMaxWaypoints, int isWaypointListValid, int getWaypointCount) {
        Debuger.checkDebugMode();
        this.zero = zero;
        this.navMaxWaypoints = navMaxWaypoints;
        this.isWaypointListValid = isWaypointListValid;
        this.getWaypointCount = getWaypointCount;
    }

    public MSP_WP_GETINFO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        zero = reader.readUint8();
        navMaxWaypoints = reader.readUint8();
        isWaypointListValid = reader.readUint8();
        getWaypointCount = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_WP_GETINFO{" + "zero=" + zero +
                ", navMaxWaypoints=" + navMaxWaypoints +
                ", isWaypointListValid=" + isWaypointListValid +
                ", getWaypointCount=" + getWaypointCount +
                '}';
    }
}
