package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 23:58
 */
public class MSP_NAV_POSHOLD implements IDecodeable {

    private static final String TAG = "MSP_NAV_POSHOLD";

    public int user_control_mode;
    public int max_auto_speed;
    public int max_auto_climb_rate;
    public int max_manual_speed;
    public int max_manual_climb_rate;
    public int max_bank_angle;
    public int althold_throttle_type;
    public int hover_throttle;

    public MSP_NAV_POSHOLD(int user_control_mode, int max_auto_speed, int max_auto_climb_rate, int max_manual_speed, int max_manual_climb_rate, int max_bank_angle, int althold_throttle_type, int hover_throttle) {
        Debuger.checkDebugMode();
        this.user_control_mode = user_control_mode;
        this.max_auto_speed = max_auto_speed;
        this.max_auto_climb_rate = max_auto_climb_rate;
        this.max_manual_speed = max_manual_speed;
        this.max_manual_climb_rate = max_manual_climb_rate;
        this.max_bank_angle = max_bank_angle;
        this.althold_throttle_type = althold_throttle_type;
        this.hover_throttle = hover_throttle;
    }

    public MSP_NAV_POSHOLD(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        user_control_mode = reader.readUint8();
        max_auto_speed = reader.readUint16();
        max_auto_climb_rate = reader.readUint16();
        max_manual_speed = reader.readUint16();
        max_manual_climb_rate = reader.readUint16();
        max_bank_angle = reader.readUint8();
        althold_throttle_type = reader.readUint8();
        hover_throttle = reader.readUint16();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_NAV_POSHOLD{" + "user_control_mode=" + user_control_mode +
                ", max_auto_speed=" + max_auto_speed +
                ", max_auto_climb_rate=" + max_auto_climb_rate +
                ", max_manual_speed=" + max_manual_speed +
                ", max_manual_climb_rate=" + max_manual_climb_rate +
                ", max_bank_angle=" + max_bank_angle +
                ", althold_throttle_type=" + althold_throttle_type +
                ", hover_throttle=" + hover_throttle +
                '}';
    }
}
