package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 22:44
 */
public class MSP_DATAFLASH_SUMMARY implements IDecodeable {

    private static final String TAG = "MSP_DATAFLASH_SUMMARY";

    public int flashIsReady;     // 0 or 1
    public int sectors;
    public long totalSize;
    public long flashfsGetOffset;// Effectively the current number of bytes stored on the volume

    public MSP_DATAFLASH_SUMMARY(int flashIsReady, int sectors, long totalSize, long flashfsGetOffset) {
        Debuger.checkDebugMode();
        this.flashIsReady = flashIsReady;
        this.sectors = sectors;
        this.totalSize = totalSize;
        this.flashfsGetOffset = flashfsGetOffset;
    }

    public MSP_DATAFLASH_SUMMARY(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        flashIsReady = reader.readUint8();
        sectors = reader.readInt32();
        totalSize = reader.readUint32();
        flashfsGetOffset = reader.readUint32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_DATAFLASH_SUMMARY{" + "flashIsReady=" + flashIsReady +
                ", sectors=" + sectors +
                ", totalSize=" + totalSize +
                ", flashfsGetOffset=" + flashfsGetOffset +
                '}';
    }
}
