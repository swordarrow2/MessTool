package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:57
 */
public class MSP_GPSSTATISTICS implements IDecodeable {

    private static final String TAG = "MSP_GPSSTATISTICS";

    public int lastMessageDt;
    public int errors;
    public int timeouts;
    public int packetCount;
    public int hdop;
    public int eph;
    public int epv;

    public MSP_GPSSTATISTICS(int lastMessageDt, int errors, int timeouts, int packetCount, int hdop, int eph, int epv) {
        Debuger.checkDebugMode();
        this.lastMessageDt = lastMessageDt;
        this.errors = errors;
        this.timeouts = timeouts;
        this.packetCount = packetCount;
        this.hdop = hdop;
        this.eph = eph;
        this.epv = epv;
    }

    public MSP_GPSSTATISTICS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        lastMessageDt = reader.readUint16();
        errors = reader.readInt32();
        timeouts = reader.readInt32();
        packetCount = reader.readInt32();
        hdop = reader.readUint16();
        eph = reader.readUint16();
        epv = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_GPSSTATISTICS{" + "lastMessageDt=" + lastMessageDt +
                ", errors=" + errors +
                ", timeouts=" + timeouts +
                ", packetCount=" + packetCount +
                ", hdop=" + hdop +
                ", eph=" + eph +
                ", epv=" + epv +
                '}';
    }
}
