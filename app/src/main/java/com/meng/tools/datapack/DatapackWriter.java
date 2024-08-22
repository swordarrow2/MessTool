package com.meng.tools.datapack;

import java.util.*;

/*
 *package  com.meng.tools.datapack
 *@author  清梦
 *@date    2024/8/22 9:10
 */
public class DatapackWriter {
    private ArrayList<Byte> list = new ArrayList<>();
    private final boolean isLittleEndian;

    public DatapackWriter(boolean isLittleEndian) {
        this.isLittleEndian = isLittleEndian;
    }

    public DatapackWriter writeInt8(byte i8) {
        list.add(i8);
        return this;
    }

    public DatapackWriter writeUint8(int u8) {
        list.add((byte) u8);
        return this;
    }

    public DatapackWriter writeInt16(int i16) {
        if (isLittleEndian) {
            list.add((byte) i16);
            list.add((byte) (i16 >>> 8));
        } else {
            list.add((byte) (i16 >>> 8));
            list.add((byte) i16);
        }
        return this;
    }

    public DatapackWriter writeUint16(int u16) {
        if (isLittleEndian) {
            list.add((byte) u16);
            list.add((byte) (u16 >>> 8));
        } else {
            list.add((byte) (u16 >>> 8));
            list.add((byte) u16);
        }
        return this;
    }

    public DatapackWriter writeInt32(int i32) {
        if (isLittleEndian) {
            list.add((byte) i32);
            list.add((byte) (i32 >>> 8));
            list.add((byte) (i32 >>> 16));
            list.add((byte) (i32 >>> 24));
        } else {
            list.add((byte) (i32 >>> 24));
            list.add((byte) (i32 >>> 16));
            list.add((byte) (i32 >>> 8));
            list.add((byte) i32);
        }
        return this;
    }

    public DatapackWriter writeUint32(long u32) {
        if (isLittleEndian) {
            list.add((byte) u32);
            list.add((byte) (u32 >>> 8));
            list.add((byte) (u32 >>> 16));
            list.add((byte) (u32 >>> 24));
        } else {
            list.add((byte) (u32 >>> 24));
            list.add((byte) (u32 >>> 16));
            list.add((byte) (u32 >>> 8));
            list.add((byte) u32);
        }
        return this;
    }

    public DatapackWriter writeInt64(long i64) {
        if (isLittleEndian) {
            list.add((byte) i64);
            list.add((byte) (i64 >>> 8));
            list.add((byte) (i64 >>> 16));
            list.add((byte) (i64 >>> 24));
            list.add((byte) (i64 >>> 32));
            list.add((byte) (i64 >>> 40));
            list.add((byte) (i64 >>> 48));
            list.add((byte) (i64 >>> 56));
        } else {
            list.add((byte) (i64 >>> 56));
            list.add((byte) (i64 >>> 48));
            list.add((byte) (i64 >>> 40));
            list.add((byte) (i64 >>> 32));
            list.add((byte) (i64 >>> 24));
            list.add((byte) (i64 >>> 16));
            list.add((byte) (i64 >>> 8));
            list.add((byte) i64);
        }
        return this;
    }

    public DatapackWriter writeFloat(float f) {
        int i32 = Float.floatToRawIntBits(f);
        list.add((byte) i32);
        list.add((byte) (i32 >>> 8));
        list.add((byte) (i32 >>> 16));
        list.add((byte) (i32 >>> 24));
        return this;
    }

    public DatapackWriter writeDouble(double d) {
        long i32 = Double.doubleToRawLongBits(d);
        list.add((byte) i32);
        list.add((byte) (i32 >>> 8));
        list.add((byte) (i32 >>> 16));
        list.add((byte) (i32 >>> 24));
        list.add((byte) (i32 >>> 32));
        list.add((byte) (i32 >>> 40));
        list.add((byte) (i32 >>> 48));
        list.add((byte) (i32 >>> 56));
        return this;
    }

    public void clear() {
        list.clear();
    }

    public byte[] encode() {
        byte[] result = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
