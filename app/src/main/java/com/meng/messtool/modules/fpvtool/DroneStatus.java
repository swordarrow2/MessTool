package com.meng.messtool.modules.fpvtool;

import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/3 11:43
 */
public class DroneStatus {

    //    MSP_API_VERSION
    public MSP_API_VERSION msp_api_version;

    //    MSP_FC_VARIANT
    public MSP_FC_VARIANT msp_fc_variant;

    //    MSP_FC_VERSION
    public MSP_FC_VERSION fc_version;

    //    MSP_BOARD_INFO
    public MSP_BOARD_INFO msp_board_info;

    //    MSP_BUILD_INFO
    public MSP_BUILD_INFO msp_build_info;

    //    MSP_SENSOR_STATUS
    public MSP_SENSOR_STATUS msp_sensor_status;

    //    MSP_ACTIVEBOXES
    public MSP_ACTIVEBOXES msp_activeboxes;

    //    MSP_STATUS && MSP_STATUS_EX
    public MSP_STATUS_EX msp_statusEX;

    //    MSP2_INAV_STATUS
    public MSP2_INAV_STATUS msp2_inav_status;

    //    MSP_RAW_IMU
    public MSP_RAW_IMU msp_raw_imu;

    //    MSP_SERVO
    public MSP_SERVO msp_servo;

    //    MSP_SERVO_CONFIGURATIONS
    public MSP_SERVO_CONFIGURATIONS msp_servo_configurations;

    //    MSP_SERVO_MIX_RULES
    public MSP_SERVO_MIX_RULES msp_servo_mix_rules;

    //    MSP2_INAV_SERVO_MIXER
    public MSP2_INAV_SERVO_MIXER msp2_inav_servo_mixer;
//    todo MAX_MIXER_PROFILE_COUNT>1

    //    MSP2_INAV_LOGIC_CONDITIONS
    public MSP2_INAV_LOGIC_CONDITIONS msp2_inav_logic_conditions;

    //    MSP2_INAV_LOGIC_CONDITIONS_STATUS
    public MSP2_INAV_LOGIC_CONDITIONS_STATUS msp2_inav_logic_conditions_status;

    //    MSP2_INAV_GVAR_STATUS
    public MSP2_INAV_GVAR_STATUS msp2_inav_gvar_status;

    //    MSP2_INAV_PROGRAMMING_PID
    public MSP2_INAV_PROGRAMMING_PID msp2_inav_programming_pid;

    //    MSP2_INAV_PROGRAMMING_PID_STATUS
    public MSP2_INAV_PROGRAMMING_PID_STATUS msp2_inav_programming_pid_status;

    //    MSP2_COMMON_MOTOR_MIXER
    public MSP2_COMMON_MOTOR_MIXER msp2_common_motor_mixer;
//    todo MAX_MIXER_PROFILE_COUNT>1

    //    MSP_MOTOR
    public MSP_MOTOR msp_motor;

    //    MSP_RC
    public MSP_RC msp_rc;

    //    MSP_ATTITUDE
    public MSP_ATTITUDE msp_attitude;

    //    MSP_ALTITUDE
    public MSP_ALTITUDE msp_altitude;

    //    MSP_SONAR_ALTITUDE
    public MSP_SONAR_ALTITUDE msp_sonar_altitude;

    //    MSP2_INAV_OPTICAL_FLOW
    public MSP2_INAV_OPTICAL_FLOW msp2_inav_optical_flow;

    //    MSP_ANALOG
    public MSP_ANALOG msp_analog;

    //    MSP2_INAV_ANALOG
    public MSP2_INAV_ANALOG msp2_inav_analog;

    //    MSP_ARMING_CONFIG
    public MSP_ARMING_CONFIG msp_arming_config;

    //    MSP_LOOP_TIME
    public MSP_LOOP_TIME msp_loop_time;

    //    MSP_RC_TUNING
    public MSP_RC_TUNING msp_rc_tuning;

    //    MSP2_INAV_RATE_PROFILE
    public MSP2_INAV_RATE_PROFILE msp2_inav_rate_profile;

    //    MSP2_PID
    public MSP2_PID msp2_pid;

    //    MSP_PIDNAMES
    public MSP_PIDNAMES msp_pidnames;

    //    MSP_MODE_RANGES
    public MSP_MODE_RANGES msp_mode_ranges;

    //    MSP_ADJUSTMENT_RANGES
    public MSP_ADJUSTMENT_RANGES msp_adjustment_ranges;

    //    MSP_BOXNAMES
    public MSP_BOXNAMES msp_boxnames;

    //    MSP_BOXIDS
    public MSP_BOXIDS msp_boxids;

    //    MSP_MISC
    public MSP_MISC msp_misc;

    //    MSP2_INAV_MISC
    public MSP2_INAV_MISC msp2_inav_misc;

    //    MSP2_INAV_MISC2
    public MSP2_INAV_MISC2 msp2_inav_misc2;

    //    MSP_MOTOR_PINS
    public MSP_MOTOR_PINS msp_motor_pins;

    //    MSP_RAW_GPS
    public MSP_RAW_GPS msp_raw_gps;

    //    MSP_COMP_GPS
    public MSP_COMP_GPS msp_comp_gps;

    //    MSP_NAV_STATUS
    public MSP_NAV_STATUS msp_nav_status;

    //    MSP_GPSSVINFO
    public MSP_GPSSVINFO msp_gpssvinfo;

    //    MSP_GPSSTATISTICS
    public MSP_GPSSTATISTICS msp_gpsstatistics;

    //    MSP_DEBUG
    public MSP_DEBUG msp_debug;

    //    MSP2_INAV_DEBUG
    public MSP2_INAV_DEBUG msp2_inav_debug;

    //    MSP_UID
    public MSP_UID msp_uid;

    //    MSP_FEATURE
    public MSP_FEATURE msp_feature;

    //    MSP_BOARD_ALIGNMENT
    public MSP_BOARD_ALIGNMENT msp_board_alignment;

    //    MSP_VOLTAGE_METER_CONFIG
    public MSP_VOLTAGE_METER_CONFIG msp_voltage_meter_config;

    //    MSP_CURRENT_METER_CONFIG
    public MSP_CURRENT_METER_CONFIG msp_current_meter_config;

    //    MSP_MIXER
    public MSP_MIXER msp_mixer;

    //    MSP_RX_CONFIG
    public MSP_RX_CONFIG msp_rx_config;

    //    MSP_FAILSAFE_CONFIG
    public MSP_FAILSAFE_CONFIG msp_failsafe_config;

    //    MSP_RSSI_CONFIG
    public MSP_RSSI_CONFIG msp_rssi_config;

    //    MSP_RX_MAP
    public MSP_RX_MAP msp_rx_map;

    //    MSP2_COMMON_SERIAL_CONFIG
    public MSP2_COMMON_SERIAL_CONFIG msp2_common_serial_config;

    //    MSP_LED_COLORS
    public MSP_LED_COLORS msp_led_colors;

    //    MSP_LED_STRIP_CONFIG
    public MSP_LED_STRIP_CONFIG msp_led_strip_config;

    //    MSP2_INAV_LED_STRIP_CONFIG_EX
    public MSP2_INAV_LED_STRIP_CONFIG_EX msp2_inav_led_strip_config_ex;

    //    MSP_LED_STRIP_MODECOLOR
    public MSP_LED_STRIP_MODECOLOR msp_led_strip_modecolor;

    //    MSP_DATAFLASH_SUMMARY
    public MSP_DATAFLASH_SUMMARY msp_dataflash_summary;

    //    MSP_BLACKBOX_CONFIG
    public MSP_BLACKBOX_CONFIG msp_blackbox_config;

    //    MSP2_BLACKBOX_CONFIG
    public MSP2_BLACKBOX_CONFIG msp2_blackbox_config;

    //    MSP_SDCARD_SUMMARY
    public MSP_SDCARD_SUMMARY msp_sdcard_summary;

    //    MSP_BATTERY_STATE
    public MSP_BATTERY_STATE msp_battery_state;

    //    MSP_OSD_CONFIG
    public MSP_OSD_CONFIG msp_osd_config;

    //    MSP_3D
    public MSP_3D msp_3D;

    //    MSP_SENSOR_ALIGNMENT
    public MSP_SENSOR_ALIGNMENT msp_sensor_alignment;

    //    MSP_ADVANCED_CONFIG
    public MSP_ADVANCED_CONFIG msp_advanced_config;

    //    MSP_FILTER_CONFIG
    public MSP_FILTER_CONFIG msp_filter_config;

    //    MSP_PID_ADVANCED
    public MSP_PID_ADVANCED msp_pid_advanced;

    //    MSP_INAV_PID
    public MSP_INAV_PID msp_inav_pid;

    //    MSP_SENSOR_CONFIG
    public MSP_SENSOR_CONFIG msp_sensor_config;

    //    MSP_NAV_POSHOLD
    public MSP_NAV_POSHOLD msp_nav_poshold;

    //    MSP_RTH_AND_LAND_CONFIG
    public MSP_RTH_AND_LAND_CONFIG msp_rth_and_land_config;

    //    MSP_FW_CONFIG
    public MSP_FW_CONFIG msp_fw_config;

    //    MSP_CALIBRATION_DATA
    public MSP_CALIBRATION_DATA msp_calibration_data;

    //    MSP_POSITION_ESTIMATION_CONFIG
    public MSP_POSITION_ESTIMATION_CONFIG msp_position_estimation_config;

    //    MSP_REBOOT
    public MSP_REBOOT msp_reboot;

    //    MSP_WP_GETINFO
    public MSP_WP_GETINFO msp_wp_getinfo;

    //    MSP_TX_INFO
    public MSP_TX_INFO msp_tx_info;

    //    MSP_RTC
    public MSP_RTC msp_rtc;

    //    MSP_VTX_CONFIG
    public MSP_VTX_CONFIG msp_vtx_config;

    //    MSP_NAME
    public MSP_NAME msp_name;

    //    MSP2_COMMON_TZ
    public MSP2_COMMON_TZ msp2_common_tz;

    //    MSP2_INAV_AIR_SPEED
    public MSP2_INAV_AIR_SPEED msp2_inav_air_speed;

    //    MSP2_INAV_MIXER
    public MSP2_INAV_MIXER msp2_inav_mixer;

    //    MSP2_INAV_OSD_ALARMS
    public MSP2_INAV_OSD_ALARMS msp2_inav_osd_alarms;

    //    MSP2_INAV_OSD_PREFERENCES
    public MSP2_INAV_OSD_PREFERENCES msp2_inav_osd_preferences;

    //    MSP2_INAV_OUTPUT_MAPPING
    public MSP2_INAV_OUTPUT_MAPPING msp2_inav_output_mapping;

    //    MSP2_INAV_OUTPUT_MAPPING_EXT
    public MSP2_INAV_OUTPUT_MAPPING_EXT msp2_inav_output_mapping_ext;

    //    MSP2_INAV_MC_BRAKING
    public MSP2_INAV_MC_BRAKING msp2_inav_mc_braking;

    //    MSP2_INAV_TEMP_SENSOR_CONFIG
    public MSP2_INAV_TEMP_SENSOR_CONFIG msp2_inav_temp_sensor_config;

    //    MSP2_INAV_TEMPERATURES
    public MSP2_INAV_TEMPERATURES msp2_inav_temperatures;

    //    MSP2_INAV_ESC_RPM
    public MSP2_INAV_ESC_RPM msp2_inav_esc_rpms;

    //    MSP2_INAV_EZ_TUNE
    public MSP2_INAV_EZ_TUNE msp2_inav_ez_tune;

    //    MSP2_INAV_RATE_DYNAMICS
    public MSP2_INAV_RATE_DYNAMICS msp2_inav_rate_dynamics;

    //    MSP2_INAV_CUSTOM_OSD_ELEMENTS
    public MSP2_INAV_CUSTOM_OSD_ELEMENTS msp2_inav_custom_osd_elements;

}
