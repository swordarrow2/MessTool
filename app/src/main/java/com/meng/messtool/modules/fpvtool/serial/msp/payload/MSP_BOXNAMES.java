package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.nio.charset.*;
import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:06
 */
public class MSP_BOXNAMES implements IDecodeable {

    private static final String TAG = "MSP_BOXNAMES";

    public MSP_BOXNAMES_ITEM[] msp_boxnames_items;

    public MSP_BOXNAMES(MSP_BOXNAMES_ITEM[] msp_boxnames_items) {
        Debuger.checkDebugMode();
        this.msp_boxnames_items = msp_boxnames_items;
    }

    public MSP_BOXNAMES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        String sdata[] = new String(data, StandardCharsets.US_ASCII).split(";");
        for (int i = 0; i < msp_boxnames_items.length; i++) {
            msp_boxnames_items[i] = new MSP_BOXNAMES_ITEM(sdata[i], ';');
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_BOXNAMES{" + "msp_boxnames_items=" + Arrays.toString(msp_boxnames_items) + '}';
    }

    public static class MSP_BOXNAMES_ITEM {

        public String name;
        public int BOX_SUFFIX;//fixed ';'

        public MSP_BOXNAMES_ITEM(String name, int BOX_SUFFIX) {
            Debuger.checkDebugMode();
            this.name = name;
            this.BOX_SUFFIX = BOX_SUFFIX;
        }

        @Override
        public String toString() {
            return "MSP_BOXNAMES_ITEM{" + "name='" + name + '\'' +
                    ", BOX_SUFFIX=" + BOX_SUFFIX +
                    '}';
        }
    }
}
