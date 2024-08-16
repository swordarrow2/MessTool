package com.meng.tools;

/*
 *package  com.meng.tools
 *@author  清梦
 *@date    2024/8/16 18:04
 */
public class HexString {
    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] md) {
        int j = md.length;
        char str[] = new char[j * 2];
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
            str[i * 2 + 1] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static String toHexStringWithSpace(byte[] array) {
        char[] buf = new char[array.length > 0 ? array.length * 3 - 1 : 0];

        int bufIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                buf[bufIndex++] = ' ';
            byte b = array[i];
            buf[bufIndex++] = hexDigits[(b >>> 4) & 0x0F];
            buf[bufIndex++] = hexDigits[b & 0x0F];
        }

        return new String(buf);
    }
}
