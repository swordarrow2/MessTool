package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:15
 */
public class MSP_VTX_CONFIG implements IDecodeable {

    private static final String TAG = "MSP_VTX_CONFIG";

    public int deviceType;
    public int band;
    public int channel;
    public int power;
    public int pitmode;
    public int vtxDevice;
    public int lowPowerDisarm;

    public MSP_VTX_CONFIG(int deviceType, int band, int channel, int power, int pitmode, int vtxDevice, int lowPowerDisarm) {
        Debuger.checkDebugMode();
        this.deviceType = deviceType;
        this.band = band;
        this.channel = channel;
        this.power = power;
        this.pitmode = pitmode;
        this.vtxDevice = vtxDevice;
        this.lowPowerDisarm = lowPowerDisarm;
    }

    public MSP_VTX_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        deviceType = reader.readUint8();
        if (deviceType == 0xFF) {
            reader.checkFinish();
        } else {
            band = reader.readUint8();
            channel = reader.readUint8();
            power = reader.readUint8();
            pitmode = reader.readUint8();
            vtxDevice = reader.readUint8();
            lowPowerDisarm = reader.readUint8();
            reader.checkFinish();
        }
    }

    @Override
    public String toString() {
        return "MSP_VTX_CONFIG{" + "deviceType=" + deviceType +
                ", band=" + band +
                ", channel=" + channel +
                ", power=" + power +
                ", pitmode=" + pitmode +
                ", vtxDevice=" + vtxDevice +
                ", lowPowerDisarm=" + lowPowerDisarm +
                '}';
    }
}
