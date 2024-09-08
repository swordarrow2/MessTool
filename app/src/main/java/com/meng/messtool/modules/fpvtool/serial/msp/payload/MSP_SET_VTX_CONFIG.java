package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_SET_VTX_CONFIG implements IEncodeable {

    public int newFrequency;
    public int power;
    public int newPitmode;
    public int lowPowerDisarm;
    @InavIgnore
    public int pitModeFreq;
    public int band;
    public int channel;

    public MSP_SET_VTX_CONFIG(int newFrequency, int power, int newPitmode, int lowPowerDisarm, int pitModeFreq, int band, int channel) {
        this.newFrequency = newFrequency;
        this.power = power;
        this.newPitmode = newPitmode;
        this.lowPowerDisarm = lowPowerDisarm;
        this.pitModeFreq = pitModeFreq;
        this.band = band;
        this.channel = channel;
    }

    public MSP_SET_VTX_CONFIG() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint16(newFrequency)
                .writeUint8(power)
                .writeUint8(newPitmode)
                .writeUint8(lowPowerDisarm)
                .writeUint16(pitModeFreq)
                .writeUint8(band)
                .writeUint8(channel);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_VTX_CONFIG{" + "newFrequency=" + newFrequency +
                ", power=" + power +
                ", newPitmode=" + newPitmode +
                ", lowPowerDisarm=" + lowPowerDisarm +
                ", pitModeFreq=" + pitModeFreq +
                ", band=" + band +
                ", channel=" + channel +
                '}';
    }
}
