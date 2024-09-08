package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:16
 */
public class MSP_BOARD_INFO implements IDecodeable {

    private static final String TAG = "MSP_BOARD_INFO";

    public String boardIdentifier;
    // No other build targets currently have
    // hardware revision detection.
    public int hardwareRevision;
    // OSD support (for BF compatibility):
    // 0 = no OSD
    // 1 = OSD slave (not supported in INAV)
    // 2 = OSD chip on board
    public int OSD_support;
    // Board communication capabilities (uint8)
    // Bit 0: 1 iff the board has VCP
    // Bit 1: 1 iff the board supports software serial
    public int commCapabilities;
    public int targetNameLength;
    public String targetName;

    public MSP_BOARD_INFO(String boardIdentifier, int hardwareRevision, int OSD_support, int commCapabilities, int targetNameLength, String targetName) {
        Debuger.checkDebugMode();
        this.boardIdentifier = boardIdentifier;
        this.hardwareRevision = hardwareRevision;
        this.OSD_support = OSD_support;
        this.commCapabilities = commCapabilities;
        this.targetNameLength = targetNameLength;
        this.targetName = targetName;
    }

    public MSP_BOARD_INFO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        boardIdentifier = reader.readCppString(4);
        hardwareRevision = reader.readUint16();
        OSD_support = reader.readUint8();
        commCapabilities = reader.readUint8();
        targetNameLength = reader.readUint8();
        targetName = reader.readCppString(targetNameLength);
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_BOARD_INFO{" + "boardIdentifier='" + boardIdentifier + '\'' +
                ", hardwareRevision=" + hardwareRevision +
                ", OSD_support=" + OSD_support +
                ", commCapabilities=" + commCapabilities +
                ", targetNameLength=" + targetNameLength +
                ", targetName='" + targetName + '\'' +
                '}';
    }
}
