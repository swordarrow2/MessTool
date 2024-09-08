package com.meng.messtool.modules.fpvtool.serial.msp;

import com.meng.messtool.modules.fpvtool.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/3 11:57
 */
public class MspV1Engine {
    public boolean onMspMessage(MspV1DataPack data, DroneStatus status) {
        switch (MspV1Cmd.getCmd(data.getCmd())) {
            case MSP_NULL:
                break;
            case MSP_API_VERSION:
                status.msp_api_version = new MSP_API_VERSION(data.getPayload());
                break;
            case MSP_FC_VARIANT:
                status.msp_fc_variant = new MSP_FC_VARIANT(data.getPayload());
                break;
            case MSP_FC_VERSION:
                status.fc_version = new MSP_FC_VERSION(data.getPayload());
                break;
            case MSP_BOARD_INFO:
                status.msp_board_info = new MSP_BOARD_INFO(data.getPayload());
                break;
            case MSP_BUILD_INFO:
                status.msp_build_info = new MSP_BUILD_INFO(data.getPayload());
                break;
            case MSP_INAV_PID:
                status.msp_inav_pid = new MSP_INAV_PID(data.getPayload());
                break;
            case MSP_SET_INAV_PID: //todo
//                status.msp_

                break;
            case MSP_NAME:
                status.msp_name = new MSP_NAME(data.getPayload());
                break;
            case MSP_SET_NAME://todo
//                status.msp_
                break;
            case MSP_NAV_POSHOLD:
                status.msp_nav_poshold = new MSP_NAV_POSHOLD(data.getPayload());
                break;
            case MSP_SET_NAV_POSHOLD:
//                status.msp_//todo
                break;
            case MSP_CALIBRATION_DATA:
                status.msp_calibration_data = new MSP_CALIBRATION_DATA(data.getPayload());
                break;
            case MSP_SET_CALIBRATION_DATA:
//                status.msp_//todo
                break;
            case MSP_POSITION_ESTIMATION_CONFIG:
                status.msp_position_estimation_config = new MSP_POSITION_ESTIMATION_CONFIG(data.getPayload());
                break;
            case MSP_SET_POSITION_ESTIMATION_CONFIG:
//                status.msp_//todo
                break;
            case MSP_WP_MISSION_LOAD:
//                status.msp_//todo
                break;
            case MSP_WP_MISSION_SAVE:
//                status.msp_//todo
                break;
            case MSP_WP_GETINFO:
                status.msp_wp_getinfo = new MSP_WP_GETINFO(data.getPayload());
                break;
            case MSP_RTH_AND_LAND_CONFIG:
                status.msp_rth_and_land_config = new MSP_RTH_AND_LAND_CONFIG(data.getPayload());
                break;
            case MSP_SET_RTH_AND_LAND_CONFIG:
//                status.msp_//todo
                break;
            case MSP_FW_CONFIG:
                status.msp_fw_config = new MSP_FW_CONFIG(data.getPayload());
                break;
            case MSP_SET_FW_CONFIG:
//                status.msp_//todo
                break;
            case MSP_MODE_RANGES:
                status.msp_mode_ranges = new MSP_MODE_RANGES(data.getPayload());
                break;
            case MSP_SET_MODE_RANGE:
//                status.msp_//todo
                break;
            case MSP_FEATURE:
                status.msp_feature = new MSP_FEATURE(data.getPayload());
                break;
            case MSP_SET_FEATURE:
//                status.msp_//todo
                break;
            case MSP_BOARD_ALIGNMENT:
                status.msp_board_alignment = new MSP_BOARD_ALIGNMENT(data.getPayload());
                break;
            case MSP_SET_BOARD_ALIGNMENT:
//                status.msp_//todo
                break;
            case MSP_CURRENT_METER_CONFIG:
                status.msp_current_meter_config = new MSP_CURRENT_METER_CONFIG(data.getPayload());
                break;
            case MSP_SET_CURRENT_METER_CONFIG:
//                status.msp_//todo
                break;
            case MSP_MIXER:
                status.msp_mixer = new MSP_MIXER(data.getPayload());
                break;
            case MSP_SET_MIXER:
//                status.msp_//todo
                break;
            case MSP_RX_CONFIG:
                status.msp_rx_config = new MSP_RX_CONFIG(data.getPayload());
                break;
            case MSP_SET_RX_CONFIG:
//                status.msp_//todo
                break;
            case MSP_LED_COLORS:
                status.msp_led_colors = new MSP_LED_COLORS(data.getPayload());
                break;
            case MSP_SET_LED_COLORS:
//                status.msp_//todo
                break;
            case MSP_LED_STRIP_CONFIG:
                status.msp_led_strip_config = new MSP_LED_STRIP_CONFIG(data.getPayload());
                break;
            case MSP_SET_LED_STRIP_CONFIG:
//                status.msp_//todo
                break;
            case MSP_RSSI_CONFIG:
                status.msp_rssi_config = new MSP_RSSI_CONFIG(data.getPayload());
                break;
            case MSP_SET_RSSI_CONFIG:
//                status.msp_//todo
                break;
            case MSP_ADJUSTMENT_RANGES:
                status.msp_adjustment_ranges = new MSP_ADJUSTMENT_RANGES(data.getPayload());
                break;
            case MSP_SET_ADJUSTMENT_RANGE:
//                status.msp_//todo
                break;
            case MSP_CF_SERIAL_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SET_CF_SERIAL_CONFIG:
//                status.msp_//todo
                break;
            case MSP_VOLTAGE_METER_CONFIG:
                status.msp_voltage_meter_config = new MSP_VOLTAGE_METER_CONFIG(data.getPayload());
                break;
            case MSP_SET_VOLTAGE_METER_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SONAR_ALTITUDE:
                status.msp_sonar_altitude = new MSP_SONAR_ALTITUDE(data.getPayload());
                break;
            case MSP_ARMING_CONFIG:
                status.msp_arming_config = new MSP_ARMING_CONFIG(data.getPayload());
                break;
            case MSP_SET_ARMING_CONFIG:
//                status.msp_//todo
                break;
            case MSP_RX_MAP:
                status.msp_rx_map = new MSP_RX_MAP(data.getPayload());
                break;
            case MSP_SET_RX_MAP:
//                status.msp_//todo
                break;
            case MSP_REBOOT:
                status.msp_reboot = new MSP_REBOOT(data.getPayload());
                break;
            case MSP_DATAFLASH_SUMMARY:
                status.msp_dataflash_summary = new MSP_DATAFLASH_SUMMARY(data.getPayload());
                break;
            case MSP_DATAFLASH_READ:
//                status.msp_//todo
                break;
            case MSP_DATAFLASH_ERASE:
//                status.msp_//todo
                break;
            case MSP_LOOP_TIME:
                status.msp_loop_time = new MSP_LOOP_TIME(data.getPayload());
                break;
            case MSP_SET_LOOP_TIME:
//                status.msp_//todo
                break;
            case MSP_FAILSAFE_CONFIG:
                status.msp_failsafe_config = new MSP_FAILSAFE_CONFIG(data.getPayload());
                break;
            case MSP_SET_FAILSAFE_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SDCARD_SUMMARY:
                status.msp_sdcard_summary = new MSP_SDCARD_SUMMARY(data.getPayload());
                break;
            case MSP_BLACKBOX_CONFIG:
                status.msp_blackbox_config = new MSP_BLACKBOX_CONFIG(data.getPayload());
                break;
            case MSP_SET_BLACKBOX_CONFIG:
//                status.msp_//todo//todo//todo//todo//todo
                break;
            case MSP_TRANSPONDER_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SET_TRANSPONDER_CONFIG:
//                status.msp_//todo
                break;
            case MSP_OSD_CONFIG:
                status.msp_osd_config = new MSP_OSD_CONFIG(data.getPayload());
                break;
            case MSP_SET_OSD_CONFIG:
//                status.msp_//todo
                break;
            case MSP_OSD_CHAR_READ:
//                status.msp_//todo
                break;
            case MSP_OSD_CHAR_WRITE:
//                status.msp_//todo
                break;
            case MSP_VTX_CONFIG:
                status.msp_vtx_config = new MSP_VTX_CONFIG(data.getPayload());
                break;
            case MSP_SET_VTX_CONFIG:
//                status.msp_//todo
                break;
            case MSP_ADVANCED_CONFIG:
                status.msp_advanced_config = new MSP_ADVANCED_CONFIG(data.getPayload());
                break;
            case MSP_SET_ADVANCED_CONFIG:
//                status.msp_//todo
                break;
            case MSP_FILTER_CONFIG:
                status.msp_filter_config = new MSP_FILTER_CONFIG(data.getPayload());
                break;
            case MSP_SET_FILTER_CONFIG:
//                status.msp_//todo
                break;
            case MSP_PID_ADVANCED:
                status.msp_pid_advanced = new MSP_PID_ADVANCED(data.getPayload());
                break;
            case MSP_SET_PID_ADVANCED:
//                status.msp_//todo
                break;
            case MSP_SENSOR_CONFIG:
                status.msp_sensor_config = new MSP_SENSOR_CONFIG(data.getPayload());
                break;
            case MSP_SET_SENSOR_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SPECIAL_PARAMETERS:
//                status.msp_//todo
                break;
            case MSP_SET_SPECIAL_PARAMETERS:
//                status.msp_//todo
                break;
            case MSP_VTXTABLE_BAND:
//                status.msp_//todo
                break;
            case MSP_VTXTABLE_POWERLEVEL:
//                status.msp_//todo
                break;
            case MSP_OSD_VIDEO_CONFIG:
//                status.msp_//todo
                break;
            case MSP_SET_OSD_VIDEO_CONFIG:
//                status.msp_//todo
                break;
            case MSP_DISPLAYPORT:
//                status.msp_//todo
                break;
            case MSP_SET_TX_INFO:
//                status.msp_//todo
                break;
            case MSP_TX_INFO:
                status.msp_tx_info = new MSP_TX_INFO(data.getPayload());
                break;
            case MSP_STATUS:
                status.msp_statusEX = new MSP_STATUS_EX(data.getPayload());
                break;
            case MSP_RAW_IMU:
                status.msp_raw_imu = new MSP_RAW_IMU(data.getPayload());
                break;
            case MSP_SERVO:
                status.msp_servo = new MSP_SERVO(data.getPayload());
                break;
            case MSP_MOTOR:
                status.msp_motor = new MSP_MOTOR(data.getPayload());
                break;
            case MSP_RC:
                status.msp_rc = new MSP_RC(data.getPayload());
                break;
            case MSP_RAW_GPS:
                status.msp_raw_gps = new MSP_RAW_GPS(data.getPayload());
                break;
            case MSP_COMP_GPS:
                status.msp_comp_gps = new MSP_COMP_GPS(data.getPayload());
                break;
            case MSP_ATTITUDE:
                status.msp_attitude = new MSP_ATTITUDE(data.getPayload());
                break;
            case MSP_ALTITUDE:
                status.msp_altitude = new MSP_ALTITUDE(data.getPayload());
                break;
            case MSP_ANALOG:
                status.msp_analog = new MSP_ANALOG(data.getPayload());
                break;
            case MSP_RC_TUNING:
                status.msp_rc_tuning = new MSP_RC_TUNING(data.getPayload());
                break;
            case MSP_ACTIVEBOXES:
                status.msp_activeboxes = new MSP_ACTIVEBOXES(data.getPayload());
                break;
            case MSP_MISC:
                status.msp_misc = new MSP_MISC(data.getPayload());
                break;
            case MSP_MOTOR_PINS:
                status.msp_motor_pins = new MSP_MOTOR_PINS(data.getPayload());
                break;
            case MSP_BOXNAMES:
                status.msp_boxnames = new MSP_BOXNAMES(data.getPayload());
                break;
            case MSP_PIDNAMES:
                status.msp_pidnames = new MSP_PIDNAMES(data.getPayload());
                break;
            case MSP_WP:
//                status.msp_//todo
                break;
            case MSP_BOXIDS:
                status.msp_boxids = new MSP_BOXIDS(data.getPayload());
                break;
            case MSP_SERVO_CONFIGURATIONS:
                status.msp_servo_configurations = new MSP_SERVO_CONFIGURATIONS(data.getPayload());
                break;
            case MSP_NAV_STATUS:
                status.msp_nav_status = new MSP_NAV_STATUS(data.getPayload());
                break;
            case MSP_NAV_CONFIG:
//                status.msp_//todo
                break;
            case MSP_3D:
                status.msp_3D = new MSP_3D(data.getPayload());
                break;
            case MSP_RC_DEADBAND:
//                status.msp_//todo
                break;
            case MSP_SENSOR_ALIGNMENT:
                status.msp_sensor_alignment = new MSP_SENSOR_ALIGNMENT(data.getPayload());
                break;
            case MSP_LED_STRIP_MODECOLOR:
                status.msp_led_strip_modecolor = new MSP_LED_STRIP_MODECOLOR(data.getPayload());
                break;
            case MSP_BATTERY_STATE:
                status.msp_battery_state = new MSP_BATTERY_STATE(data.getPayload());
                break;
            case MSP_SET_RAW_RC:
    /*            status.msp_
                break;
            case MSP_SET_RAW_GPS:
                status.msp_
                break;
            case MSP_SET_BOX:
                status.msp_
                break;
            case MSP_SET_RC_TUNING:
                status.msp_
                break;
            case MSP_ACC_CALIBRATION:
                status.msp_
                break;
            case MSP_MAG_CALIBRATION:
                status.msp_
                break;
            case MSP_SET_MISC:
                status.msp_
                break;
            case MSP_RESET_CONF:
                status.msp_
                break;
            case MSP_SET_WP:
                status.msp_
                break;
            case MSP_SELECT_SETTING:
                status.msp_
                break;
            case MSP_SET_HEAD:
                status.msp_
                break;
            case MSP_SET_SERVO_CONFIGURATION:
                status.msp_
                break;
            case MSP_SET_MOTOR:
                status.msp_
                break;
            case MSP_SET_NAV_CONFIG:
                status.msp_
                break;
            case MSP_SET_3D:
                status.msp_
                break;
            case MSP_SET_RC_DEADBAND:
                status.msp_
                break;
            case MSP_SET_RESET_CURR_PID:
                status.msp_
                break;
            case MSP_SET_SENSOR_ALIGNMENT:
                status.msp_
                break;
            case MSP_SET_LED_STRIP_MODECOLOR:
                status.msp_
                break;
            case MSP_EEPROM_WRITE:
                status.msp_
                break;
            case MSP_RESERVE_1:
                status.msp_
                break;
            case MSP_RESERVE_2:
                status.msp_
                break;
            case MSP_DEBUGMSG:
                status.msp_
                break;*/
            case MSP_DEBUG:
                status.msp_debug = new MSP_DEBUG(data.getPayload());
                break;
            case MSP_V2_FRAME:
//                status.msp_//todo
                return false;
            case MSP_STATUS_EX:
                status.msp_statusEX = new MSP_STATUS_EX(data.getPayload());
                break;
            case MSP_SENSOR_STATUS:
                status.msp_sensor_status = new MSP_SENSOR_STATUS(data.getPayload());
                break;
            case MSP_UID:
                status.msp_uid = new MSP_UID(data.getPayload());
                break;
            case MSP_GPSSVINFO:
                status.msp_gpssvinfo = new MSP_GPSSVINFO(data.getPayload());
                break;
            case MSP_GPSSTATISTICS:
                status.msp_gpsstatistics = new MSP_GPSSTATISTICS(data.getPayload());
                break;
            case MSP_ACC_TRIM:
//                status.msp_//todo
                break;
            case MSP_SET_ACC_TRIM:
//                status.msp_//todo
                break;
            case MSP_SERVO_MIX_RULES:
                status.msp_servo_mix_rules = new MSP_SERVO_MIX_RULES(data.getPayload());
                break;
            case MSP_SET_SERVO_MIX_RULE:
//                status.msp_//todo
                break;
            case MSP_SET_PASSTHROUGH:
//                status.msp_//todo
                break;
            case MSP_RTC:
                status.msp_rtc = new MSP_RTC(data.getPayload());
                break;
            case MSP_SET_RTC:
//                status.msp_//todo
                break;
        }
        return true;
    }
}
