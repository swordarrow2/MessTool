package com.meng.tools;

public class BitmapHelper {

    public static int[] argb4444ToArgb8888(byte[] data) {
        int[] argbArray = new int[data.length / 2];
        for (int i = 0; i < argbArray.length; ++i) {
            int px = readShort(data, i * 2);
            int a = (px >> 12 & 0b1111) * 16;
            int r = (px >> 8 & 0b1111) * 16;
            int g = (px >> 4 & 0b1111) * 16;
            int b = (px >> 0 & 0b1111) * 16;
            argbArray[i] = (a << 24) | (r << 16) | (g << 8) | (b << 0);
        }
        return argbArray;
    }

    public static int[] rgb565ToArgb8888(byte[] data) {
        int[] argbArray = new int[data.length / 2];
        for (int i = 0; i < argbArray.length; ++i) {
            int px = readShort(data, i * 2);
            int r = (px >> 11 & 0b11111) * 8;
            int g = (px >> 5 & 0b111111) * 4;
            int b = (px >> 0 & 0b11111) * 8;
            int a = 0xff;
            argbArray[i] = (a << 24) | (r << 16) | (g << 8) | (b << 0);
        }
        return argbArray;
    }

    public static int[] argb8888ToArgb8888(byte[] data) {
        int[] argbArray = new int[data.length / 4];
        for (int i = 0; i < argbArray.length; ++i) {
            argbArray[i] = readInt(data, i * 4);
        }
        return argbArray;
    }

    public static int[] gray8ToArgb8888(byte[] data) {
        int[] argbArray = new int[data.length];
        for (int i = 0; i < argbArray.length; ++i) {
            argbArray[i] = data[i] << 24 | 0x00FFFFFF;
        }
        return argbArray;
    }

    public static int readShort(byte[] data, int pos) {
        return (data[pos] & 0xff) | (data[pos + 1] & 0xff) << 8;
    }

    public static int readInt(byte[] data, int pos) {
        return (data[pos] & 0xff) | (data[pos + 1] & 0xff) << 8 | (data[pos + 2] & 0xff) << 16
                | (data[pos + 3] & 0xff) << 24;
    }

    public static int[] argb4444ToRgba8888(byte[] data) {
        int[] argbArray = new int[data.length / 2];
        for (int i = 0; i < argbArray.length; ++i) {
            int px = readShort(data, i * 2);
            int a = (px >> 12 & 0b1111) * 17;
            int r = (px >> 8 & 0b1111) * 17;
            int g = (px >> 4 & 0b1111) * 17;
            int b = (px >> 0 & 0b1111) * 17;
            argbArray[i] = (r << 24) | (g << 16) | (b << 8) | (a << 0);
        }
        return argbArray;
    }

    public static int[] rgb565ToRgba8888(byte[] data) {
        int[] argbArray = new int[data.length / 2];
        for (int i = 0; i < argbArray.length; ++i) {
            int px = readShort(data, i * 2);
            int r = (px >> 11 & 0b11111) * 8;
            int g = (px >> 5 & 0b111111) * 4;
            int b = (px >> 0 & 0b11111) * 8;
            int a = 0xff;
            argbArray[i] = (r << 24) | (g << 16) | (b << 8) | (a << 0);
        }
        return argbArray;
    }

    public static int[] argb8888ToRgba8888(byte[] data) {
        int[] argbArray = new int[data.length / 4];
        for (int i = 0; i < argbArray.length; ++i) {
            int px = readInt(data, i * 4);
            px = (px << 8) | (px >> 24 & 0xff);
            argbArray[i] = px;
        }
        return argbArray;
    }

    public static int[] gray8ToRgba8888(byte[] data) {
        int[] argbArray = new int[data.length];
        for (int i = 0; i < argbArray.length; ++i) {
            argbArray[i] = data[i] | 0xFFFFFF00;
        }
        return argbArray;
    }
}
