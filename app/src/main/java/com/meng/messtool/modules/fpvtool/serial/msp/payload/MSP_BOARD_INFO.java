package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:16
 */
public class MSP_BOARD_INFO {

    public String boardIdentifier;
    public int hardwareRevision;
    public int OSD_support;
    public int commCapabilities;
    public int targetNameLength;
    public String targetName;

    public MSP_BOARD_INFO(String boardIdentifier, int hardwareRevision, int OSD_support, int commCapabilities, int targetNameLength, String targetName) {
        this.boardIdentifier = boardIdentifier;
        this.hardwareRevision = hardwareRevision;
        this.OSD_support = OSD_support;
        this.commCapabilities = commCapabilities;
        this.targetNameLength = targetNameLength;
        this.targetName = targetName;
    }
}
