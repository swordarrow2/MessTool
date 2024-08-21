package com.meng.tools.hash;

/*
 *package  com.meng.tools.hash
 *@author  清梦
 *@date    2024/8/20 21:12
 */
public class CRC8_DVB_S2 {

    public static CRC8_DVB_S2 getInstance() {
        return new CRC8_DVB_S2();
    }

    private CRC8_DVB_S2() {
    }

    private byte crc = 0;

    public void reset() {
        crc = 0;
    }

    public byte getCrc() {
        return crc;
    }

    public void addHash(byte a) {
        crc ^= a;
        for (int ii = 0; ii < 8; ++ii) {
            if ((crc & 0x80) != 0) {
                crc = (byte) ((crc << 1) ^ 0xD5);
            } else {
                crc = (byte) (crc << 1);
            }
        }
    }
}
