package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_WP_MISSION_SAVE implements IEncodeable {

    @InavReserve
    public int missionId;

    public MSP_WP_MISSION_SAVE(int missionId) {
        this.missionId = missionId;
    }

    public MSP_WP_MISSION_SAVE() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(missionId);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_WP_MISSION_SAVE{" + "missionId=" + missionId + '}';
    }
}
