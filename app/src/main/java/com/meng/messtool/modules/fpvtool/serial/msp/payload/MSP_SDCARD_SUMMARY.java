package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 22:49
 */
public class MSP_SDCARD_SUMMARY implements IDecodeable {

    private static final String TAG = "MSP_SDCARD_SUMMARY";

    public int flags;
    public int state;
    public int afatfs_getLastError;
    public long afatfs_getContiguousFreeSpaceKB;
    public long numBlocksHalf;

    public MSP_SDCARD_SUMMARY(int flags, int state, int afatfs_getLastError, long afatfs_getContiguousFreeSpaceKB, long numBlocksHalf) {
        Debuger.checkDebugMode();
        this.flags = flags;
        this.state = state;
        this.afatfs_getLastError = afatfs_getLastError;
        this.afatfs_getContiguousFreeSpaceKB = afatfs_getContiguousFreeSpaceKB;
        this.numBlocksHalf = numBlocksHalf;
    }

    public MSP_SDCARD_SUMMARY(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        flags = reader.readUint8();
        state = reader.readUint8();
        afatfs_getLastError = reader.readUint8();
        afatfs_getContiguousFreeSpaceKB = reader.readUint32();
        numBlocksHalf = reader.readUint32();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_SDCARD_SUMMARY{" + "flags=" + flags +
                ", state=" + state +
                ", afatfs_getLastError=" + afatfs_getLastError +
                ", afatfs_getContiguousFreeSpaceKB=" + afatfs_getContiguousFreeSpaceKB +
                ", numBlocksHalf=" + numBlocksHalf +
                '}';
    }
}
