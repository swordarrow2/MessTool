package com.meng.messtool.modules.fpvtool.serial.msp.payload;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 20:06
 */
public class MSP_BOXNAMES {

    public String name;
    public int BOX_SUFFIX;//fixed ';'

    public MSP_BOXNAMES(String name, int BOX_SUFFIX) {
        this.name = name;
        this.BOX_SUFFIX = BOX_SUFFIX;
    }
}
