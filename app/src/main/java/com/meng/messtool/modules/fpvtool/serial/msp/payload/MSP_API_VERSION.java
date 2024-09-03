package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:14
 */
public class MSP_API_VERSION {

    public int version;
    public int versionMajor;
    public int versionMinor;

    public MSP_API_VERSION(int version, int versionMajor, int versionMinor) {
        this.version = version;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
    }

}
