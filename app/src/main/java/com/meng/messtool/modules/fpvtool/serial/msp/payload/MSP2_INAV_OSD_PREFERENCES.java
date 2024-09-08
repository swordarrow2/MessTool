package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/4 1:24
 */
public class MSP2_INAV_OSD_PREFERENCES implements IDecodeable {

    private static final String TAG = "MSP2_INAV_OSD_PREFERENCES";

    public int video_system;
    public int main_voltage_decimals;
    public int ahi_reverse_roll;
    public int crosshairs_style;
    public int left_sidebar_scroll;
    public int right_sidebar_scroll;
    public int sidebar_scroll_arrows;
    public int units;
    public int stats_energy_unit;

    public MSP2_INAV_OSD_PREFERENCES(int video_system, int main_voltage_decimals, int ahi_reverse_roll, int crosshairs_style, int left_sidebar_scroll, int right_sidebar_scroll, int sidebar_scroll_arrows, int units, int stats_energy_unit) {
        Debuger.checkDebugMode();
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

    public MSP2_INAV_OSD_PREFERENCES(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        video_system = reader.readUint8();
        main_voltage_decimals = reader.readUint8();
        ahi_reverse_roll = reader.readUint8();
        crosshairs_style = reader.readUint8();
        left_sidebar_scroll = reader.readUint8();
        right_sidebar_scroll = reader.readUint8();
        sidebar_scroll_arrows = reader.readUint8();
        units = reader.readUint8();
        stats_energy_unit = reader.readUint8();
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP2_INAV_OSD_PREFERENCES{" + "video_system=" + video_system +
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
