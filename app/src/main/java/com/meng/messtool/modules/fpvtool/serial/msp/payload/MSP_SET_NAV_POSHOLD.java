package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:26
 */
public class MSP_SET_NAV_POSHOLD implements IEncodeable {

    public int user_control_mode;
    public int max_auto_speed;
    public int max_auto_climb_rate;
    public int max_manual_speed;
    public int max_manual_climb_rate;
    public int max_bank_angle;
    public int althold_throttle_type;
    public int hover_throttle;

    public MSP_SET_NAV_POSHOLD(int user_control_mode, int max_auto_speed, int max_auto_climb_rate, int max_manual_speed, int max_manual_climb_rate, int max_bank_angle, int althold_throttle_type, int hover_throttle) {
        this.user_control_mode = user_control_mode;
        this.max_auto_speed = max_auto_speed;
        this.max_auto_climb_rate = max_auto_climb_rate;
        this.max_manual_speed = max_manual_speed;
        this.max_manual_climb_rate = max_manual_climb_rate;
        this.max_bank_angle = max_bank_angle;
        this.althold_throttle_type = althold_throttle_type;
        this.hover_throttle = hover_throttle;
    }

    public MSP_SET_NAV_POSHOLD() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(user_control_mode)
                .writeUint8(max_auto_speed)
                .writeUint8(max_auto_climb_rate)
                .writeUint8(max_manual_speed)
                .writeUint8(max_manual_climb_rate)
                .writeUint8(max_bank_angle)
                .writeUint8(althold_throttle_type)
                .writeUint16(hover_throttle);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP_SET_NAV_POSHOLD{" + "user_control_mode=" + user_control_mode +
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
