package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:52
 */
public class MSP_GPSSVINFO implements IDecodeable {

    private static final String TAG = "MSP_GPSSVINFO";

    public int one;
    public int zero0;
    public int zero1;
    public int hdopDevide100_0;
    public int hdopDevide100_1;

    public MSP_GPSSVINFO(int one, int zero0, int zero1, int hdopDevide100_0, int hdopDevide100_1) {
        Debuger.checkDebugMode();
        this.one = one;
        this.zero0 = zero0;
        this.zero1 = zero1;
        this.hdopDevide100_0 = hdopDevide100_0;
        this.hdopDevide100_1 = hdopDevide100_1;
    }

    public MSP_GPSSVINFO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        one = reader.readUint8();
        zero0 = reader.readUint8();
        zero1 = reader.readUint8();
        hdopDevide100_0 = reader.readUint8();
        hdopDevide100_1 = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_GPSSVINFO{" + "one=" + one +
                ", zero0=" + zero0 +
                ", zero1=" + zero1 +
                ", hdopDevide100_0=" + hdopDevide100_0 +
                ", hdopDevide100_1=" + hdopDevide100_1 +
                '}';
    }
}
