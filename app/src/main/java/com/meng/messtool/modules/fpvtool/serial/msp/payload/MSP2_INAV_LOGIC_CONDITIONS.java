package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:45
 */
public class MSP2_INAV_LOGIC_CONDITIONS implements IDecodeable {

    private static final String TAG = "MSP2_INAV_LOGIC_CONDITIONS";

    public MSP2_INAV_LOGIC_CONDITIONS_ITEM[] logicConditions = new MSP2_INAV_LOGIC_CONDITIONS_ITEM[64];

    public MSP2_INAV_LOGIC_CONDITIONS(MSP2_INAV_LOGIC_CONDITIONS_ITEM[] logicConditions) {
        Debuger.checkDebugMode();
        this.logicConditions = logicConditions;
    }

    public MSP2_INAV_LOGIC_CONDITIONS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < logicConditions.length; i++) {
            logicConditions[i] = new MSP2_INAV_LOGIC_CONDITIONS_ITEM(reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readUint8(), reader.readInt32(), reader.readUint8(), reader.readInt32(), reader.readUint8());
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_LOGIC_CONDITIONS{" + "logicConditions=" + Arrays.toString(logicConditions) + '}';
    }

    public static class MSP2_INAV_LOGIC_CONDITIONS_ITEM {

        public int enabled;
        public int activatorId;
        public int operation;
        public int operandAtype;
        public int operandAvalue;
        public int operandBtype;
        public int operandBvalue;
        public int flags;

        public MSP2_INAV_LOGIC_CONDITIONS_ITEM(int enabled, int activatorId, int operation, int operandAtype, int operandAvalue, int operandBtype, int operandBvalue, int flags) {
            this.enabled = enabled;
            this.activatorId = activatorId;
            this.operation = operation;
            this.operandAtype = operandAtype;
            this.operandAvalue = operandAvalue;
            this.operandBtype = operandBtype;
            this.operandBvalue = operandBvalue;
            this.flags = flags;
        }

        @Override
        public String toString() {
            return "MSP2_INAV_LOGIC_CONDITIONS_ITEM{" + "enabled=" + enabled +
                    ", activatorId=" + activatorId +
                    ", operation=" + operation +
                    ", operandAtype=" + operandAtype +
                    ", operandAvalue=" + operandAvalue +
                    ", operandBtype=" + operandBtype +
                    ", operandBvalue=" + operandBvalue +
                    ", flags=" + flags +
                    '}';
        }
    }
}
