package com.meng.tools.hash;

public class CRC32A {
    /**
     * @author 月溪
     */
    private static CRC32A instance = null;

    public static CRC32A getInstance() {
        if (instance == null) {
            instance = new CRC32A();
        }
        return instance;
    }

    private int[] table = new int[256];

    private CRC32A() {
        int value;
        for (int i = 0; i < 256; i++) {
            value = i << 24;
            for (int j = 0; j < 8; j++) {
                if ((value & 0x80000000) != 0) {
                    value = (value << 1) ^ 79764919;
                } else {
                    value = value << 1;
                }
            }
            table[i] = value;
        }
    }

    public int calculate(byte[] data) {
        int crc = 0xffffffff;
        for (int i = 0; i < data.length; i++) {
            crc = (crc << 8) ^ (table[(crc >>> 24) ^ (data[i] & 0xff)]);
        }
        crc = ~crc;
        byte[] result = new byte[4];

        result[0] = (byte) ((crc >>> 0) & 0xff);
        result[1] = (byte) ((crc >>> 8) & 0xff);
        result[2] = (byte) ((crc >>> 16) & 0xff);
        result[3] = (byte) ((crc >>> 24) & 0xff);

        return result[0] << 24 | result[1] << 16 | result[2] << 8 | result[3] << 0;
    }
}
