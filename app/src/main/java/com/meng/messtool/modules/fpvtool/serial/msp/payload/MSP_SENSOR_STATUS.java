package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:17
 */
public class MSP_SENSOR_STATUS implements IDecodeable {

    private static final String TAG = "MSP_SENSOR_STATUS";

    public int isHardwareHealthy;
    public int getHwGyroStatus;
    public int getHwAccelerometerStatus;
    public int getHwCompassStatus;
    public int getHwBarometerStatus;
    public int getHwGPSStatus;
    public int getHwRangefinderStatus;
    public int getHwPitotmeterStatus;
    public int getHwOpticalFlowStatus;

    public MSP_SENSOR_STATUS(int isHardwareHealthy, int getHwGyroStatus, int getHwAccelerometerStatus, int getHwCompassStatus, int getHwBarometerStatus, int getHwGPSStatus, int getHwRangefinderStatus, int getHwPitotmeterStatus, int getHwOpticalFlowStatus) {
        Debuger.checkDebugMode();
        this.isHardwareHealthy = isHardwareHealthy;
        this.getHwGyroStatus = getHwGyroStatus;
        this.getHwAccelerometerStatus = getHwAccelerometerStatus;
        this.getHwCompassStatus = getHwCompassStatus;
        this.getHwBarometerStatus = getHwBarometerStatus;
        this.getHwGPSStatus = getHwGPSStatus;
        this.getHwRangefinderStatus = getHwRangefinderStatus;
        this.getHwPitotmeterStatus = getHwPitotmeterStatus;
        this.getHwOpticalFlowStatus = getHwOpticalFlowStatus;
    }

    public MSP_SENSOR_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        isHardwareHealthy = reader.readInt8();
        getHwGyroStatus = reader.readInt8();
        getHwAccelerometerStatus = reader.readInt8();
        getHwCompassStatus = reader.readInt8();
        getHwBarometerStatus = reader.readInt8();
        getHwGPSStatus = reader.readInt8();
        getHwRangefinderStatus = reader.readInt8();
        getHwPitotmeterStatus = reader.readInt8();
        getHwOpticalFlowStatus = reader.readInt8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SENSOR_STATUS{" + "isHardwareHealthy=" + isHardwareHealthy +
                ", getHwGyroStatus=" + getHwGyroStatus +
                ", getHwAccelerometerStatus=" + getHwAccelerometerStatus +
                ", getHwCompassStatus=" + getHwCompassStatus +
                ", getHwBarometerStatus=" + getHwBarometerStatus +
                ", getHwGPSStatus=" + getHwGPSStatus +
                ", getHwRangefinderStatus=" + getHwRangefinderStatus +
                ", getHwPitotmeterStatus=" + getHwPitotmeterStatus +
                ", getHwOpticalFlowStatus=" + getHwOpticalFlowStatus +
                '}';
    }
}
