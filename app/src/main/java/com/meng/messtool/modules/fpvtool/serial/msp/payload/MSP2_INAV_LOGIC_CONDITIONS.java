package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 16:45
 */
public class MSP2_INAV_LOGIC_CONDITIONS {
    public MSP2_INAV_LOGIC_CONDITIONS(int enabled, int activatorId, int operation, int operandAtype, int operandAvalue, int operandBtype, int operandBvalue, int flags) {
        this.enabled = enabled;
        this.activatorId = activatorId;
        this.operation = operation;
        this.operandAtype = operandAtype;
        this.operandAvalue = operandAvalue;
        this.operandBtype = operandBtype;
        this.operandBvalue = operandBvalue;
        this.flags = flags;
    }

    public int enabled;
    public int activatorId;
    public int operation;
    public int operandAtype;
    public int operandAvalue;
    public int operandBtype;
    public int operandBvalue;
    public int flags;
}
