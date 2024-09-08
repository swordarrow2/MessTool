package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:35
 */
public class MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS implements IEncodeable {

    @MspParam(min = 0, max = 2)
    public int index;
    public MSP2_INAV_CUSTOM_OSD_ELEMENTS.CUSTOM_ELEMENTS[] custom_elements = new MSP2_INAV_CUSTOM_OSD_ELEMENTS.CUSTOM_ELEMENTS[3];

    public MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS(int index, MSP2_INAV_CUSTOM_OSD_ELEMENTS.CUSTOM_ELEMENTS[] custom_elements) {
        this.index = index;
        this.custom_elements = custom_elements;
    }

    public MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS() {

    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index);
        for (MSP2_INAV_CUSTOM_OSD_ELEMENTS.CUSTOM_ELEMENTS elements : custom_elements) {
            for (MSP2_INAV_CUSTOM_OSD_ELEMENTS.CUSTOM_ELEMENTS.CUSTOM_ELEMENTS_PARTS parts : elements.custom_elements_parts) {
                writer.writeUint8(parts.type).writeUint16(parts.value);
            }
            writer.writeUint8(elements.visibilityType).writeUint16(elements.visibilityValue).writeCppString(elements.osdCustomElementText);
        }
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS{" + "index=" + index +
                ", custom_elements=" + Arrays.toString(custom_elements) +
                '}';
    }
}
