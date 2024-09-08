package com.meng.messtool.modules.fpvtool.serial.msp;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/4 7:32
 */
public interface IEncodeable {
    //for FC's receive
    byte[] encode();
}
