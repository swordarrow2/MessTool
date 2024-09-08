package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:25
 */
public class MSP2_INAV_SET_LOGIC_CONDITIONS implements IEncodeable {

    //todo msp datapack

    public int index;
    public int enabled;
    public int activatorId;
    public int operation;
    public int operandAType;
    public int operandAValue;
    public int operandBType;
    public int operandBValue;
    public int flags;

    public MSP2_INAV_SET_LOGIC_CONDITIONS(int index, int enabled, int activatorId, int operation, int operandAType, int operandAValue, int operandBType, int operandBValue, int flags) {
        this.index = index;
        this.enabled = enabled;
        this.activatorId = activatorId;
        this.operation = operation;
        this.operandAType = operandAType;
        this.operandAValue = operandAValue;
        this.operandBType = operandBType;
        this.operandBValue = operandBValue;
        this.flags = flags;
    }

    public MSP2_INAV_SET_LOGIC_CONDITIONS() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(index)
                .writeUint8(enabled)
                .writeUint8(activatorId)
                .writeUint8(operation)
                .writeUint8(operandAType)
                .writeInt32(operandAValue)
                .writeUint8(operandBType)
                .writeInt32(operandBValue)
                .writeUint8(flags);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_SET_LOGIC_CONDITIONS{" + "index=" + index +
                ", enabled=" + enabled +
                ", activatorId=" + activatorId +
                ", operation=" + operation +
                ", operandAType=" + operandAType +
                ", operandAValue=" + operandAValue +
                ", operandBType=" + operandBType +
                ", operandBValue=" + operandBValue +
                ", flags=" + flags +
                '}';
    }
}
