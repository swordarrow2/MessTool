package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/8 12:36
 */
public class MSP_SIMULATOR  implements IEncodeable, IDecodeable {

//todo inav in out command
    // for encode


    // for decode


    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);

        return writer.encode();
    }


    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);

        reader.checkFinish();
    }
}
