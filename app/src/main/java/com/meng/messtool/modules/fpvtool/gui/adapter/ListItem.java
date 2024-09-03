package com.meng.messtool.modules.fpvtool.gui.adapter;

import android.hardware.usb.*;

import com.hoho.android.usbserial.driver.*;

/*
 *package  com.examples.usbserial
 *@author  清梦
 *@date    2024/8/15 15:48
 */
public class ListItem {
    public UsbDevice device;
    public int port;
    public UsbSerialDriver driver;

    public ListItem(UsbDevice device, int port, UsbSerialDriver driver) {
        this.device = device;
        this.port = port;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "ListItem{" + "device=" + device.toString() +
                ", port=" + port +
                ", driver=" + driver.toString() +
                '}';
    }
}
