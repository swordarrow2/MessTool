package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 22:45
 */
public class MSP2_BLACKBOX_CONFIG implements IDecodeable {

    private static final String TAG = "MSP2_BLACKBOX_CONFIG";

    public int supported;
    public int device;
    public int rate_num;
    public int rate_denom;
    public int includeFlags;

    public MSP2_BLACKBOX_CONFIG(int device, int rate_num, int rate_denom, int includeFlags) {
        Debuger.checkDebugMode();
        this.device = device;
        this.rate_num = rate_num;
        this.rate_denom = rate_denom;
        this.includeFlags = includeFlags;
    }

    public MSP2_BLACKBOX_CONFIG(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        supported = reader.readUint8();
        if (supported == 1) {
            device = reader.readUint8();
            rate_num = reader.readUint16();
            rate_denom = reader.readUint16();
            includeFlags = reader.readInt32();
            Debuger.addLog(TAG, "ok:" + toString());
        } else {
            device = reader.readUint8();
            rate_num = reader.readUint16();
            rate_denom = reader.readUint16();
            Debuger.addLog(TAG, "Blackbox not supported");
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_BLACKBOX_CONFIG{" + "device=" + device +
                ", rate_num=" + rate_num +
                ", rate_denom=" + rate_denom +
                ", includeFlags=" + Integer.toBinaryString(includeFlags) +
                '}';
    }
}
