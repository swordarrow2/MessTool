package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/6 17:32
 */
public class MSP2_INAV_OSD_SET_PREFERENCES implements IEncodeable {

    @MspParam(note = "VIDEO_SYSTEM_AUTO = 0,VIDEO_SYSTEM_PAL,VIDEO_SYSTEM_NTSC,VIDEO_SYSTEM_HDZERO," +
            "VIDEO_SYSTEM_DJIWTF,VIDEO_SYSTEM_AVATAR,VIDEO_SYSTEM_BFCOMPAT,VIDEO_SYSTEM_BFCOMPAT_HD")
    public int video_system;
    public int main_voltage_decimals;
    public int ahi_reverse_roll;
    public int crosshairs_style;
    public int left_sidebar_scroll;
    public int right_sidebar_scroll;
    public int sidebar_scroll_arrows;
    public int units;
    public int stats_energy_unit;

    public MSP2_INAV_OSD_SET_PREFERENCES(int video_system, int main_voltage_decimals, int ahi_reverse_roll, int crosshairs_style, int left_sidebar_scroll, int right_sidebar_scroll, int sidebar_scroll_arrows, int units, int stats_energy_unit) {
        this.video_system = video_system;
        this.main_voltage_decimals = main_voltage_decimals;
        this.ahi_reverse_roll = ahi_reverse_roll;
        this.crosshairs_style = crosshairs_style;
        this.left_sidebar_scroll = left_sidebar_scroll;
        this.right_sidebar_scroll = right_sidebar_scroll;
        this.sidebar_scroll_arrows = sidebar_scroll_arrows;
        this.units = units;
        this.stats_energy_unit = stats_energy_unit;
    }

    public MSP2_INAV_OSD_SET_PREFERENCES() {
    }

    @Override
    public byte[] encode() {
        DatapackWriter writer = new DatapackWriter(true);
        writer.writeUint8(video_system)
                .writeUint8(main_voltage_decimals)
                .writeUint8(ahi_reverse_roll)
                .writeUint8(crosshairs_style)
                .writeUint8(left_sidebar_scroll)
                .writeUint8(right_sidebar_scroll)
                .writeUint8(sidebar_scroll_arrows)
                .writeUint8(units)
                .writeUint8(stats_energy_unit);
        return writer.encode();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OSD_SET_PREFERENCES{" + "video_system=" + video_system +
                ", main_voltage_decimals=" + main_voltage_decimals +
                ", ahi_reverse_roll=" + ahi_reverse_roll +
                ", crosshairs_style=" + crosshairs_style +
                ", left_sidebar_scroll=" + left_sidebar_scroll +
                ", right_sidebar_scroll=" + right_sidebar_scroll +
                ", sidebar_scroll_arrows=" + sidebar_scroll_arrows +
                ", units=" + units +
                ", stats_energy_unit=" + stats_energy_unit +
                '}';
    }
}
