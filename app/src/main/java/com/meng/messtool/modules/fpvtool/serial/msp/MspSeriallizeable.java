package com.meng.messtool.modules.fpvtool.serial.msp;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp
 *@author  清梦
 *@date    2024/8/22 8:59
 */
public interface MspSeriallizeable<T> {
    byte[] encode();

    void decode(byte[] seriallized, int offset);

    T deepCopy();
}
