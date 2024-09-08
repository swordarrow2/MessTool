package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/8 12:36
 */
public class MSP2_COMMON_PG_LIST implements IEncodeable, IDecodeable {

    // for encode
    public int index = -1;
//todo inav in out command
    // for decode
    public MSP2_COMMON_PG_LIST_ITEM[] msp2_common_pg_list_items;


    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        if (index != -1) {
            writer.writeUint16(index);
        }
        return writer.encode();
    }


    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        msp2_common_pg_list_items = new MSP2_COMMON_PG_LIST_ITEM[data.length / 6];
        for (int i = 0; i < msp2_common_pg_list_items.length; i++) {
            msp2_common_pg_list_items[i] = new MSP2_COMMON_PG_LIST_ITEM(reader.readUint16(), reader.readUint16(), reader.readUint16());
        }
        reader.checkFinish();
    }

    public static class MSP2_COMMON_PG_LIST_ITEM {

        public MSP2_COMMON_PG_LIST_ITEM(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }

        public int index;
        public int start;
        public int end;
    }
}
