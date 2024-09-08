package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;

import java.nio.charset.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 8:46
 */
public class MSP_PIDNAMES implements IDecodeable {

    private static final String TAG = "MSP_PIDNAMES";

    public String pidNames;

    public MSP_PIDNAMES(String pidNames) {
        Debuger.checkDebugMode();
        this.pidNames = pidNames;
    }

    public MSP_PIDNAMES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        pidNames = new String(data, StandardCharsets.US_ASCII);
    }

    @Override
    public String toString() {
        return "MSP_PIDNAMES{" + "pidNames='" + pidNames + '\'' + '}';
    }
}
