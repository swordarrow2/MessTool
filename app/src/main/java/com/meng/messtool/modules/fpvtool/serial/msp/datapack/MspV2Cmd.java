package com.meng.messtool.modules.fpvtool.serial.msp.datapack;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp
 *@author  清梦
 *@date    2024/8/17 14:40
 */
public enum MspV2Cmd {

    MSP2_NULL((short) 0),

    /***************************  COMMON  ****************************/
    MSP2_COMMON_TZ((short) 0x1001), //out message       Gets the TZ offset for the local time (returns: minutes(i16))
    MSP2_COMMON_SET_TZ((short) 0x1002), //in message        Sets the TZ offset for the local time (args: minutes(i16))
    MSP2_COMMON_SETTING((short) 0x1003),  //in/out message    Returns the value for a setting
    MSP2_COMMON_SET_SETTING((short) 0x1004), //in message        Sets the value for a setting

    MSP2_COMMON_MOTOR_MIXER((short) 0x1005),
    MSP2_COMMON_SET_MOTOR_MIXER((short) 0x1006),

    MSP2_COMMON_SETTING_INFO((short) 0x1007), //in/out message    Returns info about a setting (PG, type, flags, min/max, etc..).
    MSP2_COMMON_PG_LIST((short) 0x1008), //in/out message    Returns a list of the PG ids used by the settings

    MSP2_COMMON_SERIAL_CONFIG((short) 0x1009),
    MSP2_COMMON_SET_SERIAL_CONFIG((short) 0x100A),

    // radar commands
    MSP2_COMMON_SET_RADAR_POS((short) 0x100B),//SET radar position information
    MSP2_COMMON_SET_RADAR_ITD((short) 0x100C),//SET radar information to display


    /***************************  SENSOR  ****************************/

    MSP2_SENSOR_RANGEFINDER((short) 0x1F01),
    MSP2_SENSOR_OPTIC_FLOW((short) 0x1F02),
    MSP2_SENSOR_GPS((short) 0x1F03),
    MSP2_SENSOR_COMPASS((short) 0x1F04),
    MSP2_SENSOR_BAROMETER((short) 0x1F05),
    MSP2_SENSOR_AIRSPEED((short) 0x1F06),

    /***************************  INAV SPECIAL  ****************************/

    MSP2_INAV_STATUS((short) 0x2000),
    MSP2_INAV_OPTICAL_FLOW((short) 0x2001),
    MSP2_INAV_ANALOG((short) 0x2002),
    MSP2_INAV_MISC((short) 0x2003),
    MSP2_INAV_SET_MISC((short) 0x2004),
    MSP2_INAV_BATTERY_CONFIG((short) 0x2005),
    MSP2_INAV_SET_BATTERY_CONFIG((short) 0x2006),
    MSP2_INAV_RATE_PROFILE((short) 0x2007),
    MSP2_INAV_SET_RATE_PROFILE((short) 0x2008),
    MSP2_INAV_AIR_SPEED((short) 0x2009),
    MSP2_INAV_OUTPUT_MAPPING((short) 0x200A),
    MSP2_INAV_MC_BRAKING((short) 0x200B),
    MSP2_INAV_SET_MC_BRAKING((short) 0x200C),
    MSP2_INAV_OUTPUT_MAPPING_EXT((short) 0x200D),
    MSP2_INAV_TIMER_OUTPUT_MODE((short) 0x200E),
    MSP2_INAV_SET_TIMER_OUTPUT_MODE((short) 0x200F),

    MSP2_INAV_MIXER((short) 0x2010),
    MSP2_INAV_SET_MIXER((short) 0x2011),

    MSP2_INAV_OSD_LAYOUTS((short) 0x2012),
    MSP2_INAV_OSD_SET_LAYOUT_ITEM((short) 0x2013),
    MSP2_INAV_OSD_ALARMS((short) 0x2014),
    MSP2_INAV_OSD_SET_ALARMS((short) 0x2015),
    MSP2_INAV_OSD_PREFERENCES((short) 0x2016),
    MSP2_INAV_OSD_SET_PREFERENCES((short) 0x2017),

    MSP2_INAV_SELECT_BATTERY_PROFILE((short) 0x2018),

    MSP2_INAV_DEBUG((short) 0x2019),

    MSP2_BLACKBOX_CONFIG((short) 0x201A),
    MSP2_SET_BLACKBOX_CONFIG((short) 0x201B),

    MSP2_INAV_TEMP_SENSOR_CONFIG((short) 0x201C),
    MSP2_INAV_SET_TEMP_SENSOR_CONFIG((short) 0x201D),
    MSP2_INAV_TEMPERATURES((short) 0x201E),

    MSP_SIMULATOR((short) 0x201F),

    MSP2_INAV_SERVO_MIXER((short) 0x2020),
    MSP2_INAV_SET_SERVO_MIXER((short) 0x2021),
    MSP2_INAV_LOGIC_CONDITIONS((short) 0x2022),
    MSP2_INAV_SET_LOGIC_CONDITIONS((short) 0x2023),
    MSP2_INAV_GLOBAL_FUNCTIONS((short) 0x2024),
    MSP2_INAV_SET_GLOBAL_FUNCTIONS((short) 0x2025),
    MSP2_INAV_LOGIC_CONDITIONS_STATUS((short) 0x2026),
    MSP2_INAV_GVAR_STATUS((short) 0x2027),
    MSP2_INAV_PROGRAMMING_PID((short) 0x2028),
    MSP2_INAV_SET_PROGRAMMING_PID((short) 0x2029),
    MSP2_INAV_PROGRAMMING_PID_STATUS((short) 0x202A),

    MSP2_PID((short) 0x2030),
    MSP2_SET_PID((short) 0x2031),

    MSP2_INAV_OPFLOW_CALIBRATION((short) 0x2032),

    MSP2_INAV_FWUPDT_PREPARE((short) 0x2033),
    MSP2_INAV_FWUPDT_STORE((short) 0x2034),
    MSP2_INAV_FWUPDT_EXEC((short) 0x2035),
    MSP2_INAV_FWUPDT_ROLLBACK_PREPARE((short) 0x2036),
    MSP2_INAV_FWUPDT_ROLLBACK_EXEC((short) 0x2037),

    MSP2_INAV_SAFEHOME((short) 0x2038),
    MSP2_INAV_SET_SAFEHOME((short) 0x2039),

    MSP2_INAV_MISC2((short) 0x203A),
    MSP2_INAV_LOGIC_CONDITIONS_SINGLE((short) 0x203B),

    MSP2_INAV_ESC_RPM((short) 0x2040),

    MSP2_INAV_LED_STRIP_CONFIG_EX((short) 0x2048),
    MSP2_INAV_SET_LED_STRIP_CONFIG_EX((short) 0x2049),

    MSP2_INAV_FW_APPROACH((short) 0x204A),
    MSP2_INAV_SET_FW_APPROACH((short) 0x204B),

    MSP2_INAV_RATE_DYNAMICS((short) 0x2060),
    MSP2_INAV_SET_RATE_DYNAMICS((short) 0x2061),

    MSP2_INAV_EZ_TUNE((short) 0x2070),
    MSP2_INAV_EZ_TUNE_SET((short) 0x2071),

    MSP2_INAV_SELECT_MIXER_PROFILE((short) 0x2080),

    MSP2_INAV_CUSTOM_OSD_ELEMENTS((short) 0x2100),
    MSP2_INAV_SET_CUSTOM_OSD_ELEMENTS((short) 0x2101);

    public boolean isCommonCommand() {
        return cmd >= 0x1001 && cmd <= 0x100C;
    }

    public boolean isSensorCommand() {
        return cmd >= 0x1F01 && cmd <= 0x1F06;
    }

    public boolean isInavCommand() {
        return cmd >= 0x2000 && cmd <= 0x2101;
    }

    private short cmd;

    public short getCmd() {
        return cmd;
    }

    MspV2Cmd(short cmd) {
        this.cmd = cmd;
    }
}
