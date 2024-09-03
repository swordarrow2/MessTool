package com.meng.messtool.modules.fpvtool.serial.msp;

import com.meng.messtool.modules.fpvtool.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/3 11:57
 */
public class MspV2Engine {

    private void onMspMessage(MspV2DataPack data, DroneStatus status) {
        switch (MspV2Cmd.getCmd(data.cmd)) {
            case MSP2_NULL:
                break;
            case MSP2_COMMON_TZ:
                break;
            case MSP2_COMMON_SET_TZ:
                break;
            case MSP2_COMMON_SETTING:
                break;
            case MSP2_COMMON_SET_SETTING:
                break;
            case MSP2_COMMON_MOTOR_MIXER:
                break;
            case MSP2_COMMON_SET_MOTOR_MIXER:
                break;
            case MSP2_COMMON_SETTING_INFO:
                break;
            case MSP2_COMMON_PG_LIST:
                break;
            case MSP2_COMMON_SERIAL_CONFIG:
                break;
            case MSP2_COMMON_SET_SERIAL_CONFIG:
                break;
            case MSP2_COMMON_SET_RADAR_POS:
                break;
            case MSP2_COMMON_SET_RADAR_ITD:
                break;
            case MSP2_SENSOR_RANGEFINDER:
                break;
            case MSP2_SENSOR_OPTIC_FLOW:
                break;
            case MSP2_SENSOR_GPS:
                break;
            case MSP2_SENSOR_COMPASS:
                break;
            case MSP2_SENSOR_BAROMETER:
                break;
            case MSP2_SENSOR_AIRSPEED:
                break;
            case MSP2_INAV_STATUS:
                break;
            case MSP2_INAV_OPTICAL_FLOW:
                break;
            case MSP2_INAV_ANALOG:
                break;
            case MSP2_INAV_MISC:
                break;
            case MSP2_INAV_SET_MISC:
                break;
            case MSP2_INAV_BATTERY_CONFIG:
                break;
            case MSP2_INAV_SET_BATTERY_CONFIG:
                break;
            case MSP2_INAV_RATE_PROFILE:
                break;
            case MSP2_INAV_SET_RATE_PROFILE:
                break;
            case MSP2_INAV_AIR_SPEED:
                break;
            case MSP2_INAV_OUTPUT_MAPPING:
                break;
            case MSP2_INAV_MC_BRAKING:
                break;
            case MSP2_INAV_SET_MC_BRAKING:
                break;
            case MSP2_INAV_OUTPUT_MAPPING_EXT:
                break;
            case MSP2_INAV_TIMER_OUTPUT_MODE:
                break;
            case MSP2_INAV_SET_TIMER_OUTPUT_MODE:
                break;
            case MSP2_INAV_MIXER:
                break;
            case MSP2_INAV_SET_MIXER:
                break;
            case MSP2_INAV_OSD_LAYOUTS:
                break;
            case MSP2_INAV_OSD_SET_LAYOUT_ITEM:
                break;
            case MSP2_INAV_OSD_ALARMS:
                break;
            case MSP2_INAV_OSD_SET_ALARMS:
                break;
            case MSP2_INAV_OSD_PREFERENCES:
                break;
            case MSP2_INAV_OSD_SET_PREFERENCES:
                break;
            case MSP2_INAV_SELECT_BATTERY_PROFILE:
                break;
            case MSP2_INAV_DEBUG:
                break;
            case MSP2_BLACKBOX_CONFIG:
                break;
            case MSP2_SET_BLACKBOX_CONFIG:
                break;
            case MSP2_INAV_TEMP_SENSOR_CONFIG:
                break;
            case MSP2_INAV_SET_TEMP_SENSOR_CONFIG:
                break;
            case MSP2_INAV_TEMPERATURES:
                break;
            case MSP_SIMULATOR:
                break;
            case MSP2_INAV_SERVO_MIXER:
                break;
            case MSP2_INAV_SET_SERVO_MIXER:
                break;
            case MSP2_INAV_LOGIC_CONDITIONS:
                break;
            case MSP2_INAV_SET_LOGIC_CONDITIONS:
                break;
            case MSP2_INAV_GLOBAL_FUNCTIONS:
                break;
            case MSP2_INAV_SET_GLOBAL_FUNCTIONS:
                break;
            case MSP2_INAV_LOGIC_CONDITIONS_STATUS:
                break;
            case MSP2_INAV_GVAR_STATUS:
                break;
            case MSP2_INAV_PROGRAMMING_PID:
                break;
            case MSP2_INAV_SET_PROGRAMMING_PID:
                break;
            case MSP2_INAV_PROGRAMMING_PID_STATUS:
                break;
            case MSP2_PID:
                break;
            case MSP2_SET_PID:
                break;
            case MSP2_INAV_OPFLOW_CALIBRATION:
                break;
            case MSP2_INAV_FWUPDT_PREPARE:
                break;
            case MSP2_INAV_FWUPDT_STORE:
                break;
            case MSP2_INAV_FWUPDT_EXEC:
                break;
            case MSP2_INAV_FWUPDT_ROLLBACK_PREPARE:
                break;
            case MSP2_INAV_FWUPDT_ROLLBACK_EXEC:
                break;
            case MSP2_INAV_SAFEHOME:
                break;
            case MSP2_INAV_SET_SAFEHOME:
                break;
            case MSP2_INAV_MISC2:
                break;
            case MSP2_INAV_LOGIC_CONDITIONS_SINGLE:
                break;
            case MSP2_INAV_ESC_RPM:
                break;
            case MSP2_INAV_LED_STRIP_CONFIG_EX:
                break;
            case MSP2_INAV_SET_LED_STRIP_CONFIG_EX:
                break;
            case MSP2_INAV_FW_APPROACH:
                break;
            case MSP2_INAV_SET_FW_APPROACH:
                break;
            case MSP2_INAV_RATE_DYNAMICS:
                break;
            case MSP2_INAV_SET_RATE_DYNAMICS:
                break;
            case MSP2_INAV_EZ_TUNE:
                break;
            case MSP2_INAV_EZ_TUNE_SET:
                break;
            case MSP2_INAV_SELECT_MIXER_PROFILE:
                break;
            case MSP2_INAV_CUSTOM_OSD_ELEMENTS:
                break;
            case MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS:
                break;
        }
    }
}
