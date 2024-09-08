package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:35
 */
public class MSP2_INAV_FWUPDT_ROLLBACK_PREPARE implements IEncodeable {

    //todo msp datapack

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);

        return writer.encode();
    }
}
