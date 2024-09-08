package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 7:58
 */
public class MSP_FC_VARIANT implements IDecodeable {

    private static final String TAG = "MSP_FC_VARIANT";

    public String flightControllerIdentifier;

    public MSP_FC_VARIANT(String flightControllerIdentifier) {
        Debuger.checkDebugMode();
        this.flightControllerIdentifier = flightControllerIdentifier;
    }

    public MSP_FC_VARIANT(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        flightControllerIdentifier = reader.readCppString(4);
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_FC_VARIANT{" + "flightControllerIdentifier='" + flightControllerIdentifier + '\'' + '}';
    }
}
