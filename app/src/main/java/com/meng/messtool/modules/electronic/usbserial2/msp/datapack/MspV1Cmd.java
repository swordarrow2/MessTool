package com.meng.messtool.modules.electronic.usbserial2.msp;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp
 *@author  清梦
 *@date    2024/8/16 22:19
 */
public enum MspV1Cmd {

    MSP_NULL((byte) 0),

    MSP_API_VERSION((byte) 0x1),//out message
    MSP_FC_VARIANT((byte) 0x2),//out message
    MSP_FC_VERSION((byte) 0x3),//out message
    MSP_BOARD_INFO((byte) 0x4),//out message
    MSP_BUILD_INFO((byte) 0x5),//out message

    MSP_INAV_PID((byte) 0x6),
    MSP_SET_INAV_PID((byte) 0x7),

    MSP_NAME((byte) 0xa),//out message          Returns user set board name - betaflight
    MSP_SET_NAME((byte) 0xb),//in message           Sets board name - betaflight

    MSP_NAV_POSHOLD((byte) 0xc),
    MSP_SET_NAV_POSHOLD((byte) 0xd),

    MSP_CALIBRATION_DATA((byte) 0xe),
    MSP_SET_CALIBRATION_DATA((byte) 0xf),

    MSP_POSITION_ESTIMATION_CONFIG((byte) 0x10),
    MSP_SET_POSITION_ESTIMATION_CONFIG((byte) 0x11),

    MSP_WP_MISSION_LOAD((byte) 0x12),     // Load mission from NVRAM
    MSP_WP_MISSION_SAVE((byte) 0x13),     // Save mission to NVRAM
    MSP_WP_GETINFO((byte) 0x14),

    MSP_RTH_AND_LAND_CONFIG((byte) 0x15),
    MSP_SET_RTH_AND_LAND_CONFIG((byte) 0x16),
    MSP_FW_CONFIG((byte) 0x17),
    MSP_SET_FW_CONFIG((byte) 0x18),
    //
// MSP commands for Cleanflight original features
//
    MSP_MODE_RANGES((byte) 0x22),//out message         Returns all mode ranges
    MSP_SET_MODE_RANGE((byte) 0x23),//in message          Sets a single mode range

    MSP_FEATURE((byte) 0x24),
    MSP_SET_FEATURE((byte) 0x25),

    MSP_BOARD_ALIGNMENT((byte) 0x26),
    MSP_SET_BOARD_ALIGNMENT((byte) 0x27),

    MSP_CURRENT_METER_CONFIG((byte) 0x28),
    MSP_SET_CURRENT_METER_CONFIG((byte) 0x29),

    MSP_MIXER((byte) 0x2a),
    MSP_SET_MIXER((byte) 0x2b),

    MSP_RX_CONFIG((byte) 0x2c),
    MSP_SET_RX_CONFIG((byte) 0x2d),

    MSP_LED_COLORS((byte) 0x2e),
    MSP_SET_LED_COLORS((byte) 0x2f),

    MSP_LED_STRIP_CONFIG((byte) 0x30),
    MSP_SET_LED_STRIP_CONFIG((byte) 0x31),

    MSP_RSSI_CONFIG((byte) 0x32),
    MSP_SET_RSSI_CONFIG((byte) 0x33),

    MSP_ADJUSTMENT_RANGES((byte) 0x34),
    MSP_SET_ADJUSTMENT_RANGE((byte) 0x35),

    // private - only to be used by the configurator, the commands are likely to change
    MSP_CF_SERIAL_CONFIG((byte) 0x36),  //Deprecated and not used
    MSP_SET_CF_SERIAL_CONFIG((byte) 0x37), //Deprecated and not used

    MSP_VOLTAGE_METER_CONFIG((byte) 0x38),
    MSP_SET_VOLTAGE_METER_CONFIG((byte) 0x39),

    MSP_SONAR_ALTITUDE((byte) 0x3a),//out message get surface altitude [cm]

    MSP_ARMING_CONFIG((byte) 0x3d),//out message         Returns auto_disarm_delay and disarm_kill_switch parameters
    MSP_SET_ARMING_CONFIG((byte) 0x3e),//in message          Sets auto_disarm_delay and disarm_kill_switch parameters

    //
// Baseflight MSP commands (if enabled they exist in Cleanflight)
//
    MSP_RX_MAP((byte) 0x40),//out message get channel map (also returns number of channels total)
    MSP_SET_RX_MAP((byte) 0x41),//in message set rx map, numchannels to set comes from MSP_RX_MAP

    MSP_REBOOT((byte) 0x44),//in message reboot settings

    MSP_DATAFLASH_SUMMARY((byte) 0x46),//out message - get description of dataflash chip
    MSP_DATAFLASH_READ((byte) 0x47),//out message - get content of dataflash chip
    MSP_DATAFLASH_ERASE((byte) 0x48),//in message - erase dataflash chip

    MSP_LOOP_TIME((byte) 0x49),//out message         Returns FC cycle time i.e looptime parameter
    MSP_SET_LOOP_TIME((byte) 0x4a),//in message          Sets FC cycle time i.e looptime parameter

    MSP_FAILSAFE_CONFIG((byte) 0x4b),//out message         Returns FC Fail-Safe settings
    MSP_SET_FAILSAFE_CONFIG((byte) 0x4c),//in message          Sets FC Fail-Safe settings

    MSP_SDCARD_SUMMARY((byte) 0x4f),//out message         Get the state of the SD card

    MSP_BLACKBOX_CONFIG((byte) 0x50),//out message         Get blackbox settings
    MSP_SET_BLACKBOX_CONFIG((byte) 0x51),//in message          Set blackbox settings

    MSP_TRANSPONDER_CONFIG((byte) 0x52),//out message         Get transponder settings
    MSP_SET_TRANSPONDER_CONFIG((byte) 0x53),//in message          Set transponder settings

    MSP_OSD_CONFIG((byte) 0x54),//out message         Get osd settings - betaflight
    MSP_SET_OSD_CONFIG((byte) 0x55),//in message          Set osd settings - betaflight

    MSP_OSD_CHAR_READ((byte) 0x56),//out message         Get osd settings - betaflight
    MSP_OSD_CHAR_WRITE((byte) 0x57),//in message          Set osd settings - betaflight

    MSP_VTX_CONFIG((byte) 0x58),//out message         Get vtx settings - betaflight
    MSP_SET_VTX_CONFIG((byte) 0x59),//in message          Set vtx settings - betaflight

    // Betaflight Additional Commands
    MSP_ADVANCED_CONFIG((byte) 0x5a),
    MSP_SET_ADVANCED_CONFIG((byte) 0x5b),

    MSP_FILTER_CONFIG((byte) 0x5c),
    MSP_SET_FILTER_CONFIG((byte) 0x5d),

    MSP_PID_ADVANCED((byte) 0x5e),
    MSP_SET_PID_ADVANCED((byte) 0x5f),

    MSP_SENSOR_CONFIG((byte) 0x60),
    MSP_SET_SENSOR_CONFIG((byte) 0x61),

    MSP_SPECIAL_PARAMETERS((byte) 0x62),// Temporary betaflight parameters before cleanup and keep CF compatibility
    MSP_SET_SPECIAL_PARAMETERS((byte) 0x63),// Temporary betaflight parameters before cleanup and keep CF compatibility

    MSP_VTXTABLE_BAND((byte) 0x89),//out message         vtxTable band/channel data - needed by msp vtx
    MSP_VTXTABLE_POWERLEVEL((byte) 0x8a),//out message         vtxTable powerLevel data - neede by msp vtx

    //
// OSD specific
//
    MSP_OSD_VIDEO_CONFIG((byte) 0xb4),
    MSP_SET_OSD_VIDEO_CONFIG((byte) 0xb5),

    MSP_DISPLAYPORT((byte) 0xb6),

    MSP_SET_TX_INFO((byte) 0xba),// in message           Used to send runtime information from TX lua scripts to the firmware
    MSP_TX_INFO((byte) 0xbb), // out message          Used by TX lua scripts to read information from the firmware

//
// Multwii original MSP commands
//

    MSP_STATUS((byte) 0x65),//out message         cycletime & errors_count & sensor present & box activation & current setting number
    MSP_RAW_IMU((byte) 0x66),//out message         9 DOF
    MSP_SERVO((byte) 0x67),//out message         servos
    MSP_MOTOR((byte) 0x68),//out message         motors
    MSP_RC((byte) 0x69),//out message         rc channels and more
    MSP_RAW_GPS((byte) 0x6a),//out message         fix, numsat, lat, lon, alt, speed, ground course
    MSP_COMP_GPS((byte) 0x6b),//out message         distance home, direction home
    MSP_ATTITUDE((byte) 0x6c),//out message         2 angles 1 heading
    MSP_ALTITUDE((byte) 0x6d),//out message         altitude, variometer
    MSP_ANALOG((byte) 0x6e),//out message         vbat, powermetersum, rssi if available on RX
    MSP_RC_TUNING((byte) 0x6f),//out message         rc rate, rc expo, rollpitch rate, yaw rate, dyn throttle PID
    MSP_ACTIVEBOXES((byte) 0x71),//out message         Active box flags (full width, more than 32 bits)
    MSP_MISC((byte) 0x72),//out message         powermeter trig
    MSP_MOTOR_PINS((byte) 0x73),//out message         which pins are in use for motors & servos, for GUI
    MSP_BOXNAMES((byte) 0x74),//out message         the aux switch names
    MSP_PIDNAMES((byte) 0x75),//out message         the PID names
    MSP_WP((byte) 0x76),//out message         get a WP, WP# is in the payload, returns (WP#, lat, lon, alt, flags) WP#0-home, WP#16-poshold
    MSP_BOXIDS((byte) 0x77),//out message         get the permanent IDs associated to BOXes
    MSP_SERVO_CONFIGURATIONS((byte) 0x78),//out message         All servo configurations.
    MSP_NAV_STATUS((byte) 0x79),//out message         Returns navigation status
    MSP_NAV_CONFIG((byte) 0x7a),//out message         Returns navigation parameters
    MSP_3D((byte) 0x7c),//out message         Settings needed for reversible ESCs
    MSP_RC_DEADBAND((byte) 0x7d),//out message         deadbands for yaw alt pitch roll
    MSP_SENSOR_ALIGNMENT((byte) 0x7e),//out message         orientation of acc,gyro,mag
    MSP_LED_STRIP_MODECOLOR((byte) 0x7f),//out message         Get LED strip mode_color settings
    MSP_BATTERY_STATE((byte) 0x82),   // DJI googles fc battery info

    MSP_SET_RAW_RC((byte) 0xc8),//in message          8 rc chan
    MSP_SET_RAW_GPS((byte) 0xc9),//in message          fix, numsat, lat, lon, alt, speed
    MSP_SET_BOX((byte) 0xcb),//in message          BOX setup (number is dependant of your setup)
    MSP_SET_RC_TUNING((byte) 0xcc),//in message          rc rate, rc expo, rollpitch rate, yaw rate, dyn throttle PID, yaw expo
    MSP_ACC_CALIBRATION((byte) 0xcd),//in message          no param
    MSP_MAG_CALIBRATION((byte) 0xce),//in message          no param
    MSP_SET_MISC((byte) 0xcf),//in message          powermeter trig + 8 free for future use
    MSP_RESET_CONF((byte) 0xd0),//in message          no param
    MSP_SET_WP((byte) 0xd1),//in message          sets a given WP (WP#,lat, lon, alt, flags)
    MSP_SELECT_SETTING((byte) 0xd2),//in message          Select Setting Number (0-2)
    MSP_SET_HEAD((byte) 0xd3),//in message          define a new heading hold direction
    MSP_SET_SERVO_CONFIGURATION((byte) 0xd4),//in message          Servo settings
    MSP_SET_MOTOR((byte) 0xd6),//in message          PropBalance function
    MSP_SET_NAV_CONFIG((byte) 0xd7),//in message          Sets nav config parameters - write to the eeprom
    MSP_SET_3D((byte) 0xd9),//in message          Settings needed for reversible ESCs
    MSP_SET_RC_DEADBAND((byte) 0xda),//in message          deadbands for yaw alt pitch roll
    MSP_SET_RESET_CURR_PID((byte) 0xdb),//in message          resetting the current pid profile to defaults
    MSP_SET_SENSOR_ALIGNMENT((byte) 0xdc),//in message          set the orientation of the acc,gyro,mag
    MSP_SET_LED_STRIP_MODECOLOR((byte) 0xdd),//in message         Set LED strip mode_color settings

//  MSP_BIND              ((byte)    240    ),//in message          no param
//  MSP_ALARMS            ((byte)    242

    MSP_EEPROM_WRITE((byte) 0xfa),//in message          no param
    MSP_RESERVE_1((byte) 0xfb),    //reserved for system usage
    MSP_RESERVE_2((byte) 0xfc),   //reserved for system usage
    MSP_DEBUGMSG((byte) 0xfd),//out message         debug string buffer
    MSP_DEBUG((byte) 0xfe),//out message         debug1,debug2,debug3,debug4
    MSP_V2_FRAME((byte) 0xff),   //MSPv2 payload indicator

    // Additional commands that are not compatible with MultiWii
    MSP_STATUS_EX((byte) 0x96),//out message         cycletime, errors_count, CPU load, sensor present etc
    MSP_SENSOR_STATUS((byte) 0x97),//out message         Hardware sensor status
    MSP_UID((byte) 0xa0),//out message         Unique device ID
    MSP_GPSSVINFO((byte) 0xa4),//out message         get Signal Strength (only U-Blox)
    MSP_GPSSTATISTICS((byte) 0xa6),//out message         get GPS debugging data
    MSP_ACC_TRIM((byte) 0xf0),//out message         get acc angle trim values
    MSP_SET_ACC_TRIM((byte) 0xef),//in message          set acc angle trim values
    MSP_SERVO_MIX_RULES((byte) 0xf1),//out message         Returns servo mixer configuration
    MSP_SET_SERVO_MIX_RULE((byte) 0xf2),//in message          Sets servo mixer configuration
    MSP_SET_PASSTHROUGH((byte) 0xf5),//in message          Sets up passthrough to different peripherals (4way interface, uart, etc...)
    MSP_RTC((byte) 0xf6),//out message         Gets the RTC clock (returns: secs(i32) millis(u16) - (0,0) if time is not known)
    MSP_SET_RTC((byte) 0xf7);//in message          Sets the RTC clock (args: secs(i32) millis(u16))


    private byte cmd;

    MspV1Cmd(byte b) {
        cmd = b;
    }

    public byte getCmd() {
        return cmd;
    }
}
