package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 7:58
 */
public class MSP_FC_VERSION implements IDecodeable {

    private static final String TAG = "MSP_FC_VERSION";

    public int fcVersionMajor;
    public int fcVersionMinor;
    public int fcVersionPatchLevel;

    public MSP_FC_VERSION(int fcVersionMajor, int fcVersionMinor, int fcVersionPatchLevel) {
        Debuger.checkDebugMode();
        this.fcVersionMajor = fcVersionMajor;
        this.fcVersionMinor = fcVersionMinor;
        this.fcVersionPatchLevel = fcVersionPatchLevel;
    }


    public MSP_FC_VERSION(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        fcVersionMajor = reader.readUint8();
        fcVersionMinor = reader.readUint8();
        fcVersionPatchLevel = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_FC_VERSION{" + "fcVersionMajor=" + fcVersionMajor +
                ", fcVersionMinor=" + fcVersionMinor +
                ", fcVersionPatchLevel=" + fcVersionPatchLevel +
                '}';
    }
}
