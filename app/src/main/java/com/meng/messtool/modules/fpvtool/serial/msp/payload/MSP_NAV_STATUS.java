package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:51
 */
public class MSP_NAV_STATUS implements IDecodeable {

    private static final String TAG = "MSP_NAV_STATUS";

    public int mode;
    public int state;
    public int activeWpAction;
    public int activeWpNumber;
    public int error;
    public int getHeadingHoldTarget;

    public MSP_NAV_STATUS(int mode, int state, int activeWpAction, int activeWpNumber, int error, int getHeadingHoldTarget) {
        Debuger.checkDebugMode();
        this.mode = mode;
        this.state = state;
        this.activeWpAction = activeWpAction;
        this.activeWpNumber = activeWpNumber;
        this.error = error;
        this.getHeadingHoldTarget = getHeadingHoldTarget;
    }

    public MSP_NAV_STATUS(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        mode = reader.readUint8();
        state = reader.readUint8();
        activeWpAction = reader.readUint8();
        activeWpNumber = reader.readUint8();
        error = reader.readUint8();
        getHeadingHoldTarget = reader.readInt16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_NAV_STATUS{" + "mode=" + mode +
                ", state=" + state +
                ", activeWpAction=" + activeWpAction +
                ", activeWpNumber=" + activeWpNumber +
                ", error=" + error +
                ", getHeadingHoldTarget=" + getHeadingHoldTarget +
                '}';
    }
}
