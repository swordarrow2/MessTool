package com.meng.messtool.modules.fpvtool.serial.msp;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp
 *@author  清梦
 *@date    2024/9/6 20:41
 */
public @interface MspParam {
    String note() default "";

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;
}
