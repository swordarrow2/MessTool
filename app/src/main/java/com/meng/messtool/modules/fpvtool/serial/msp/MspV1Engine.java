package com.meng.messtool.modules.fpvtool.serial.msp;

import com.meng.messtool.modules.fpvtool.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/3 11:57
 */
public class MspV1Engine {
    public void onMspMessage(MspV1DataPack data, DroneStatus status) {
        switch (MspV1Cmd.getCmd(data.getCmd())) {
            case MSP_NULL:
                break;
            case MSP_API_VERSION:

                break;
            case MSP_FC_VARIANT:
                break;
            case MSP_FC_VERSION:
                break;
            case MSP_BOARD_INFO:
                break;
            case MSP_BUILD_INFO:
                break;
            case MSP_INAV_PID:
                break;
            case MSP_SET_INAV_PID:
                break;
            case MSP_NAME:
                break;
            case MSP_SET_NAME:
                break;
            case MSP_NAV_POSHOLD:
                break;
            case MSP_SET_NAV_POSHOLD:
                break;
            case MSP_CALIBRATION_DATA:
                break;
            case MSP_SET_CALIBRATION_DATA:
                break;
            case MSP_POSITION_ESTIMATION_CONFIG:
                break;
            case MSP_SET_POSITION_ESTIMATION_CONFIG:
                break;
            case MSP_WP_MISSION_LOAD:
                break;
            case MSP_WP_MISSION_SAVE:
                break;
            case MSP_WP_GETINFO:
                break;
            case MSP_RTH_AND_LAND_CONFIG:
                break;
            case MSP_SET_RTH_AND_LAND_CONFIG:
                break;
            case MSP_FW_CONFIG:
                break;
            case MSP_SET_FW_CONFIG:
                break;
            case MSP_MODE_RANGES:
                break;
            case MSP_SET_MODE_RANGE:
                break;
            case MSP_FEATURE:
                break;
            case MSP_SET_FEATURE:
                break;
            case MSP_BOARD_ALIGNMENT:
                break;
            case MSP_SET_BOARD_ALIGNMENT:
                break;
            case MSP_CURRENT_METER_CONFIG:
                break;
            case MSP_SET_CURRENT_METER_CONFIG:
                break;
            case MSP_MIXER:
                break;
            case MSP_SET_MIXER:
                break;
            case MSP_RX_CONFIG:
                break;
            case MSP_SET_RX_CONFIG:
                break;
            case MSP_LED_COLORS:
                break;
            case MSP_SET_LED_COLORS:
                break;
            case MSP_LED_STRIP_CONFIG:
                break;
            case MSP_SET_LED_STRIP_CONFIG:
                break;
            case MSP_RSSI_CONFIG:
                break;
            case MSP_SET_RSSI_CONFIG:
                break;
            case MSP_ADJUSTMENT_RANGES:
                break;
            case MSP_SET_ADJUSTMENT_RANGE:
                break;
            case MSP_CF_SERIAL_CONFIG:
                break;
            case MSP_SET_CF_SERIAL_CONFIG:
                break;
            case MSP_VOLTAGE_METER_CONFIG:
                break;
            case MSP_SET_VOLTAGE_METER_CONFIG:
                break;
            case MSP_SONAR_ALTITUDE:
                break;
            case MSP_ARMING_CONFIG:
                break;
            case MSP_SET_ARMING_CONFIG:
                break;
            case MSP_RX_MAP:
                break;
            case MSP_SET_RX_MAP:
                break;
            case MSP_REBOOT:
                break;
            case MSP_DATAFLASH_SUMMARY:
                break;
            case MSP_DATAFLASH_READ:
                break;
            case MSP_DATAFLASH_ERASE:
                break;
            case MSP_LOOP_TIME:
                break;
            case MSP_SET_LOOP_TIME:
                break;
            case MSP_FAILSAFE_CONFIG:
                break;
            case MSP_SET_FAILSAFE_CONFIG:
                break;
            case MSP_SDCARD_SUMMARY:
                break;
            case MSP_BLACKBOX_CONFIG:
                break;
            case MSP_SET_BLACKBOX_CONFIG:
                break;
            case MSP_TRANSPONDER_CONFIG:
                break;
            case MSP_SET_TRANSPONDER_CONFIG:
                break;
            case MSP_OSD_CONFIG:
                break;
            case MSP_SET_OSD_CONFIG:
                break;
            case MSP_OSD_CHAR_READ:
                break;
            case MSP_OSD_CHAR_WRITE:
                break;
            case MSP_VTX_CONFIG:
                break;
            case MSP_SET_VTX_CONFIG:
                break;
            case MSP_ADVANCED_CONFIG:
                break;
            case MSP_SET_ADVANCED_CONFIG:
                break;
            case MSP_FILTER_CONFIG:
                break;
            case MSP_SET_FILTER_CONFIG:
                break;
            case MSP_PID_ADVANCED:
                break;
            case MSP_SET_PID_ADVANCED:
                break;
            case MSP_SENSOR_CONFIG:
                break;
            case MSP_SET_SENSOR_CONFIG:
                break;
            case MSP_SPECIAL_PARAMETERS:
                break;
            case MSP_SET_SPECIAL_PARAMETERS:
                break;
            case MSP_VTXTABLE_BAND:
                break;
            case MSP_VTXTABLE_POWERLEVEL:
                break;
            case MSP_OSD_VIDEO_CONFIG:
                break;
            case MSP_SET_OSD_VIDEO_CONFIG:
                break;
            case MSP_DISPLAYPORT:
                break;
            case MSP_SET_TX_INFO:
                break;
            case MSP_TX_INFO:
                break;
            case MSP_STATUS:
                break;
            case MSP_RAW_IMU:
                break;
            case MSP_SERVO:
                break;
            case MSP_MOTOR:
                break;
            case MSP_RC:
                break;
            case MSP_RAW_GPS:
                break;
            case MSP_COMP_GPS:
                break;
            case MSP_ATTITUDE:
                break;
            case MSP_ALTITUDE:
                break;
            case MSP_ANALOG:
                break;
            case MSP_RC_TUNING:
                break;
            case MSP_ACTIVEBOXES:
                break;
            case MSP_MISC:
                break;
            case MSP_MOTOR_PINS:
                break;
            case MSP_BOXNAMES:
                break;
            case MSP_PIDNAMES:
                break;
            case MSP_WP:
                break;
            case MSP_BOXIDS:
                break;
            case MSP_SERVO_CONFIGURATIONS:
                break;
            case MSP_NAV_STATUS:
                break;
            case MSP_NAV_CONFIG:
                break;
            case MSP_3D:
                break;
            case MSP_RC_DEADBAND:
                break;
            case MSP_SENSOR_ALIGNMENT:
                break;
            case MSP_LED_STRIP_MODECOLOR:
                break;
            case MSP_BATTERY_STATE:
                break;
            case MSP_SET_RAW_RC:
                break;
            case MSP_SET_RAW_GPS:
                break;
            case MSP_SET_BOX:
                break;
            case MSP_SET_RC_TUNING:
                break;
            case MSP_ACC_CALIBRATION:
                break;
            case MSP_MAG_CALIBRATION:
                break;
            case MSP_SET_MISC:
                break;
            case MSP_RESET_CONF:
                break;
            case MSP_SET_WP:
                break;
            case MSP_SELECT_SETTING:
                break;
            case MSP_SET_HEAD:
                break;
            case MSP_SET_SERVO_CONFIGURATION:
                break;
            case MSP_SET_MOTOR:
                break;
            case MSP_SET_NAV_CONFIG:
                break;
            case MSP_SET_3D:
                break;
            case MSP_SET_RC_DEADBAND:
                break;
            case MSP_SET_RESET_CURR_PID:
                break;
            case MSP_SET_SENSOR_ALIGNMENT:
                break;
            case MSP_SET_LED_STRIP_MODECOLOR:
                break;
            case MSP_EEPROM_WRITE:
                break;
            case MSP_RESERVE_1:
                break;
            case MSP_RESERVE_2:
                break;
            case MSP_DEBUGMSG:
                break;
            case MSP_DEBUG:
                break;
            case MSP_V2_FRAME:
                break;
            case MSP_STATUS_EX:
                break;
            case MSP_SENSOR_STATUS:
                break;
            case MSP_UID:
                break;
            case MSP_GPSSVINFO:
                break;
            case MSP_GPSSTATISTICS:
                break;
            case MSP_ACC_TRIM:
                break;
            case MSP_SET_ACC_TRIM:
                break;
            case MSP_SERVO_MIX_RULES:
                break;
            case MSP_SET_SERVO_MIX_RULE:
                break;
            case MSP_SET_PASSTHROUGH:
                break;
            case MSP_RTC:
                break;
            case MSP_SET_RTC:
                break;
        }
    }
}
