package com.meng.messtool.modules.fpvtool;

import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;

import static com.meng.messtool.modules.fpvtool.serial.msp.datapack.MspV1Cmd.MSP_LED_COLORS;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/3 11:43
 */
public class DroneStatus {

    public int MAX_SUPPORTED_SERVOS;

    //    MSP_API_VERSION
    public MSP_API_VERSION msp_api_version;

//    MSP_FC_VARIANT
public String flightControllerIdentifier;

//    MSP_FC_VERSION
    public int FC_VERSION_MAJOR;
    public int FC_VERSION_MINOR;
    public int FC_VERSION_PATCH_LEVEL;

//    MSP_BOARD_INFO
 public MSP_BOARD_INFO msp_board_info;

//    MSP_BUILD_INFO
    public String    buildDate   ;
    public String    buildTime  ;
    public String    shortGitRevision  ;

//    MSP_SENSOR_STATUS
public MSP_SENSOR_STATUS msp_sensor_status;

//    MSP_ACTIVEBOXES
    public long mspBoxModeFlags;

//    MSP_STATUS_EX && MSP_STATUS
    public int    cycleTime  ;
    public int     i2cGetErrorCounter ;
    public int    packSensorStatus  ;
    public int    getConfigProfile  ;
    public int     averageSystemLoadPercent ;
    public int     armingFlags ;
    public int    accGetCalibrationAxisFlags  ;

    //MSP2_INAV_STATUS
    public int    getConfigBatteryProfile  ;
    public int     getConfigMixerProfile ;

//    MSP_RAW_IMU
  public MSP_RAW_IMU msp_raw_imu;

//    MSP_SERVO
    public int[]  servos=new int[16] ;

//    MSP_SERVO_CONFIGURATIONS
    public MSP_SERVO_CONFIGURATIONS[] MSPSERVOCONFIGURATIONS =new MSP_SERVO_CONFIGURATIONS[16] ;

//    MSP_SERVO_MIX_RULES
    public MSP_SERVO_MIX_RULES[] MSPSERVOMIXRULES =new MSP_SERVO_MIX_RULES[32];

//    MSP2_INAV_SERVO_MIXER
    public MSP2_INAV_SERVO_MIXER[] MSP2INAVSERVOMIXERS =new MSP2_INAV_SERVO_MIXER[1];
    //todo MAX_MIXER_PROFILE_COUNT>1

//    MSP2_INAV_LOGIC_CONDITIONS
    public MSP2_INAV_LOGIC_CONDITIONS[] logicConditions=new MSP2_INAV_LOGIC_CONDITIONS[64];

//    MSP2_INAV_LOGIC_CONDITIONS_STATUS
    public int[] logicConditionGetValue=new int[64];

//    MSP2_INAV_GVAR_STATUS
    public int[] globalVariables =new int[8];

//    MSP2_INAV_PROGRAMMING_PID
    public MSP2_INAV_PROGRAMMING_PID[] msp2_inav_programming_pid=new MSP2_INAV_PROGRAMMING_PID[4];

//    MSP2_INAV_PROGRAMMING_PID_STATUS
    public int[] programmingPidGetOutput=new int[4];

//    MSP2_COMMON_MOTOR_MIXER
    public MSP2_COMMON_MOTOR_MIXER[] msp2_common_motor_mixers=new MSP2_COMMON_MOTOR_MIXER[12];
//todo MAX_MIXER_PROFILE_COUNT>1

//    MSP_MOTOR
public int[]    motor=new int[8] ;

//    MSP_RC
 public int[]    rxGetChannelValue=new int[16] ;

//    MSP_ATTITUDE
MSP_ATTITUDE msp_attitude;

//    MSP_ALTITUDE
public MSP_ALTITUDE msp_altitude;

//    MSP_SONAR_ALTITUDE
    public int   rangefinderGetLatestAltitude   ;

//    MSP2_INAV_OPTICAL_FLOW
public MSP2_INAV_OPTICAL_FLOW msp2_inav_optical_flow;

//    MSP_ANALOG
public MSP_ANALOG msp_analog;

//    MSP2_INAV_ANALOG
public MSP2_INAV_ANALOG msp2_inav_analog;

//    MSP_ARMING_CONFIG
    public MSP_ARMING_CONFIG msp_arming_config;

//    MSP_LOOP_TIME
public int    looptime  ;

//    MSP_RC_TUNING
public MSP_RC_TUNING msp_rc_tuning;

//    MSP2_INAV_RATE_PROFILE
public MSP2_INAV_RATE_PROFILE msp2_inav_rate_profile;

//    MSP2_PID
public MSP2_PID[] msp2_pids = new MSP2_PID[9];

//    MSP_PIDNAMES
public String MSP_PIDNAMES;

//    MSP_MODE_RANGES
public MSP_MODE_RANGES[] msp_mode_ranges=new MSP_MODE_RANGES[40];

//    MSP_ADJUSTMENT_RANGES
public MSP_ADJUSTMENT_RANGES[] msp_adjustment_ranges=new MSP_ADJUSTMENT_RANGES[20];

//    MSP_BOXNAMES
      public MSP_BOXNAMES[] msp_boxnames;//seems like dynamic length

//    MSP_MISC
   public MSP_MISC msp_misc;

//    MSP2_INAV_MISC
public MSP2_INAV_MISC msp2_inav_misc;

//    MSP2_INAV_MISC2
public MSP2_INAV_MISC2 msp2_inav_misc2;

//    MSP_MOTOR_PINS
public MSP_MOTOR_PINS motor_pins;

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
public MSP2_COMMON_SERIAL_CONFIG[] msp2_common_serial_config =new MSP2_COMMON_SERIAL_CONFIG[6];

//    MSP_LED_COLORS
public MSP_LED_COLORS[] msp_led_colors = new MSP_LED_COLORS[16];

//    MSP_LED_STRIP_CONFIG
public MSP_LED_STRIP_CONFIG[] msp_led_strip_configs=new MSP_LED_STRIP_CONFIG[128];

//    MSP2_INAV_LED_STRIP_CONFIG_EX
public MSP2_INAV_LED_STRIP_CONFIG_EX[] msp2_inav_led_strip_config_exes=new MSP2_INAV_LED_STRIP_CONFIG_EX[128];

//    MSP_LED_STRIP_MODECOLOR
public MSP_LED_STRIP_MODECOLOR[] msp_led_strip_modecolors=new MSP_LED_STRIP_MODECOLOR[45];

//line 1284











//    public void initServo(int MAX_SUPPORTED_SERVOS){
//        servos=new int[MAX_SUPPORTED_SERVOS];
//        MSPSERVOCONFIGURATIONS =new MSP_SERVO_CONFIGURATIONS[MAX_SUPPORTED_SERVOS];
//        MSPSERVOMIXRULES=new MSP_SERVO_MIX_RULES[MAX_SUPPORTED_SERVOS*2];
//    }

}
