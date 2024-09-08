package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 22:42
 */
public class MSP_BUILD_INFO implements IDecodeable {

    private static final String TAG = "MSP_BUILD_INFO";

    public String buildDate;
    public String buildTime;
    public String shortGitRevision;

    public MSP_BUILD_INFO(String buildDate, String buildTime, String shortGitRevision) {
        Debuger.checkDebugMode();
        this.buildDate = buildDate;
        this.buildTime = buildTime;
        this.shortGitRevision = shortGitRevision;
    }

    public MSP_BUILD_INFO(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        buildDate = reader.readCppString(11);
        buildTime = reader.readCppString(8);
        shortGitRevision = reader.readCppString(8);
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_BUILD_INFO{" + "buildDate='" + buildDate + '\'' +
                ", buildTime='" + buildTime + '\'' +
                ", shortGitRevision='" + shortGitRevision + '\'' +
                '}';
    }
}
