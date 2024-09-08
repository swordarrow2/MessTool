package com.meng.messtool.modules.fpvtool.serial.msp;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/6 16:28
 */
public class PackageInfo {
  /*
  在INAV中，MSP命令分为了3类，InCommand，OutCommand，InOutCommand。
  对于飞控来说，InCommand数据包包含命令和附加参数，没有输出，仅需要回应收到消息。
  OutCommand数据包只有指令没有附加参数，需要输出自身数据。
  InOutCommand需要先读取指令和参数，然后输出自身数据。
  IDecodeable接口用于解析飞控的输出，IEncodeable用来编码需要发送给飞控的数据。
   */
}
