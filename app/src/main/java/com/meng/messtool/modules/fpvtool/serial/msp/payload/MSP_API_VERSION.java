package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:14
 */
public class MSP_API_VERSION implements IDecodeable {

    private static final String TAG = "MSP_API_VERSION";

    public int version;
    public int versionMajor;
    public int versionMinor;

    public MSP_API_VERSION(byte[] data) {
        decode(data);
    }

    public MSP_API_VERSION(int version, int versionMajor, int versionMinor) {
        Debuger.checkDebugMode();
        this.version = version;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        version = reader.readUint8();
        versionMajor = reader.readUint8();
        versionMinor = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_API_VERSION{" + "version=" + version +
                ", versionMajor=" + versionMajor +
                ", versionMinor=" + versionMinor +
                '}';
    }
}
