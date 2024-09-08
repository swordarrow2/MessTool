package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:52
 */
public class MSP2_INAV_CUSTOM_OSD_ELEMENTS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_CUSTOM_OSD_ELEMENTS";

    public int maxCustomElements;
    public int osdCustomElementTextSizeSub1;
    public CUSTOM_ELEMENTS[] custom_elements = new CUSTOM_ELEMENTS[3];

    public MSP2_INAV_CUSTOM_OSD_ELEMENTS(int maxCustomElements, int osdCustomElementTextSizeSub1, CUSTOM_ELEMENTS[] custom_elements) {
        Debuger.checkDebugMode();
        this.maxCustomElements = maxCustomElements;
        this.osdCustomElementTextSizeSub1 = osdCustomElementTextSizeSub1;
        this.custom_elements = custom_elements;
    }

    public MSP2_INAV_CUSTOM_OSD_ELEMENTS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        maxCustomElements = reader.readUint8();
        osdCustomElementTextSizeSub1 = reader.readUint8();
        for (int i = 0; i < custom_elements.length; i++) {
            custom_elements[i] = new CUSTOM_ELEMENTS();
            for (int j = 0; j < custom_elements[i].custom_elements_parts.length; j++) {
                custom_elements[i].custom_elements_parts[j] = new CUSTOM_ELEMENTS.CUSTOM_ELEMENTS_PARTS(reader.readUint8(), reader.readUint16());
            }
            custom_elements[i].visibilityType = reader.readUint8();
            custom_elements[i].visibilityValue = reader.readUint16();
            custom_elements[i].osdCustomElementText = reader.readCppString(15);
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_CUSTOM_OSD_ELEMENTS{" + "maxCustomElements=" + maxCustomElements +
                ", osdCustomElementTextSizeSub1=" + osdCustomElementTextSizeSub1 +
                ", custom_elements=" + Arrays.toString(custom_elements) +
                '}';
    }

    public static class CUSTOM_ELEMENTS {

        public CUSTOM_ELEMENTS_PARTS[] custom_elements_parts = new CUSTOM_ELEMENTS_PARTS[3];
        public int visibilityType;
        public int visibilityValue;
        public String osdCustomElementText;

        public static class CUSTOM_ELEMENTS_PARTS {

            public int type;
            public int value;

            public CUSTOM_ELEMENTS_PARTS(int type, int value) {
                this.type = type;
                this.value = value;
            }

            @Override
            public String toString() {
                return "CUSTOM_ELEMENTS_PARTS{" + "type=" + type + ", value=" + value + '}';
            }

        }
    }
}
