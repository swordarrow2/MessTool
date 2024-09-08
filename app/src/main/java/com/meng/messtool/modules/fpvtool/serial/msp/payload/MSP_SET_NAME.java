package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:31
 */
public class MSP_SET_NAME implements IEncodeable {

    public String craftName;

    public MSP_SET_NAME(String craftName) {
        this.craftName = craftName;
    }

    public MSP_SET_NAME() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeCppString(craftName);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_NAME{" + "craftName='" + craftName + '\'' + '}';
    }
}
