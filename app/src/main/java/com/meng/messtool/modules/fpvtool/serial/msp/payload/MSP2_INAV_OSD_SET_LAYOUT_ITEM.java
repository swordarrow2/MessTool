package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_INAV_OSD_SET_LAYOUT_ITEM implements IEncodeable {

    public int layout;
    public int item;
    public int item_pos;

    public MSP2_INAV_OSD_SET_LAYOUT_ITEM(int layout, int item, int item_pos) {
        this.layout = layout;
        this.item = item;
        this.item_pos = item_pos;
    }

    public MSP2_INAV_OSD_SET_LAYOUT_ITEM() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(layout)
                .writeUint8(item)
                .writeUint16(item_pos);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OSD_SET_LAYOUT_ITEM{" + "layout=" + layout +
                ", item=" + item +
                ", item_pos=" + item_pos +
                '}';
    }
}
