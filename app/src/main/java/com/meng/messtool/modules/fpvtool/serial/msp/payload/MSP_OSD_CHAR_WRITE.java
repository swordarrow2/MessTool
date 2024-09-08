package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:29
 */
public class MSP_OSD_CHAR_WRITE implements IEncodeable {

    //todo msp datapack

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);

        return writer.encode();
    }
}
