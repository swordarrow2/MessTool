package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:35
 */
public class MSP2_INAV_MISC2 implements IDecodeable {

    private static final String TAG = "MSP2_INAV_MISC2";

    public int seconds;
    public int getFlightTime;
    public int getThrottlePercentUseScaled;
    public int navigationIsControllingThrottle;

    public MSP2_INAV_MISC2(int seconds, int getFlightTime, int getThrottlePercentUseScaled, int navigationIsControllingThrottle) {
        Debuger.checkDebugMode();
        this.seconds = seconds;
        this.getFlightTime = getFlightTime;
        this.getThrottlePercentUseScaled = getThrottlePercentUseScaled;
        this.navigationIsControllingThrottle = navigationIsControllingThrottle;
    }

    public MSP2_INAV_MISC2(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        seconds = reader.readInt32();
        getFlightTime = reader.readInt32();
        getThrottlePercentUseScaled = reader.readUint8();
        navigationIsControllingThrottle = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_MISC2{" + "seconds=" + seconds +
                ", getFlightTime=" + getFlightTime +
                ", getThrottlePercentUseScaled=" + getThrottlePercentUseScaled +
                ", navigationIsControllingThrottle=" + navigationIsControllingThrottle +
                '}';
    }
}
