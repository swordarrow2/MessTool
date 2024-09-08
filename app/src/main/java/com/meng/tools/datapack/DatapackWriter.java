package com.meng.tools.datapack;

import java.io.*;
import java.nio.charset.*;

/*
 *package  com.meng.tools.datapack
 *@author  清梦
 *@date    2024/8/22 9:10
 */
public class DatapackWriter {
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //    private ArrayList<Byte> list = new ArrayList<>();
    private final boolean isLittleEndian;

    public DatapackWriter(boolean isLittleEndian) {
        this.isLittleEndian = isLittleEndian;
    }

    public DatapackWriter writeInt8(byte i8) {
        byteArrayOutputStream.write(i8);
        return this;
    }

    public DatapackWriter writeUint8(int u8) {
        byteArrayOutputStream.write(u8);
        return this;
    }

    public DatapackWriter writeInt16(int i16) {
        if (isLittleEndian) {
            byteArrayOutputStream.write((byte) i16);
            byteArrayOutputStream.write((byte) (i16 >>> 8));
        } else {
            byteArrayOutputStream.write((byte) (i16 >>> 8));
            byteArrayOutputStream.write((byte) i16);
        }
        return this;
    }

    public DatapackWriter writeUint16(int u16) {
        if (isLittleEndian) {
            byteArrayOutputStream.write((byte) u16);
            byteArrayOutputStream.write((byte) (u16 >>> 8));
        } else {
            byteArrayOutputStream.write((byte) (u16 >>> 8));
            byteArrayOutputStream.write((byte) u16);
        }
        return this;
    }

    public DatapackWriter writeInt32(int i32) {
        if (isLittleEndian) {
            byteArrayOutputStream.write((byte) i32);
            byteArrayOutputStream.write((byte) (i32 >>> 8));
            byteArrayOutputStream.write((byte) (i32 >>> 16));
            byteArrayOutputStream.write((byte) (i32 >>> 24));
        } else {
            byteArrayOutputStream.write((byte) (i32 >>> 24));
            byteArrayOutputStream.write((byte) (i32 >>> 16));
            byteArrayOutputStream.write((byte) (i32 >>> 8));
            byteArrayOutputStream.write((byte) i32);
        }
        return this;
    }

    public DatapackWriter writeUint32(long u32) {
        if (isLittleEndian) {
            byteArrayOutputStream.write((byte) u32);
            byteArrayOutputStream.write((byte) (u32 >>> 8));
            byteArrayOutputStream.write((byte) (u32 >>> 16));
            byteArrayOutputStream.write((byte) (u32 >>> 24));
        } else {
            byteArrayOutputStream.write((byte) (u32 >>> 24));
            byteArrayOutputStream.write((byte) (u32 >>> 16));
            byteArrayOutputStream.write((byte) (u32 >>> 8));
            byteArrayOutputStream.write((byte) u32);
        }
        return this;
    }

    public DatapackWriter writeInt64(long i64) {
        if (isLittleEndian) {
            byteArrayOutputStream.write((byte) i64);
            byteArrayOutputStream.write((byte) (i64 >>> 8));
            byteArrayOutputStream.write((byte) (i64 >>> 16));
            byteArrayOutputStream.write((byte) (i64 >>> 24));
            byteArrayOutputStream.write((byte) (i64 >>> 32));
            byteArrayOutputStream.write((byte) (i64 >>> 40));
            byteArrayOutputStream.write((byte) (i64 >>> 48));
            byteArrayOutputStream.write((byte) (i64 >>> 56));
        } else {
            byteArrayOutputStream.write((byte) (i64 >>> 56));
            byteArrayOutputStream.write((byte) (i64 >>> 48));
            byteArrayOutputStream.write((byte) (i64 >>> 40));
            byteArrayOutputStream.write((byte) (i64 >>> 32));
            byteArrayOutputStream.write((byte) (i64 >>> 24));
            byteArrayOutputStream.write((byte) (i64 >>> 16));
            byteArrayOutputStream.write((byte) (i64 >>> 8));
            byteArrayOutputStream.write((byte) i64);
        }
        return this;
    }

    public DatapackWriter writeFloat(float f) {
        int i32 = Float.floatToRawIntBits(f);
        byteArrayOutputStream.write((byte) i32);
        byteArrayOutputStream.write((byte) (i32 >>> 8));
        byteArrayOutputStream.write((byte) (i32 >>> 16));
        byteArrayOutputStream.write((byte) (i32 >>> 24));
        return this;
    }

    public DatapackWriter writeDouble(double d) {
        long i64 = Double.doubleToRawLongBits(d);
        byteArrayOutputStream.write((byte) i64);
        byteArrayOutputStream.write((byte) (i64 >>> 8));
        byteArrayOutputStream.write((byte) (i64 >>> 16));
        byteArrayOutputStream.write((byte) (i64 >>> 24));
        byteArrayOutputStream.write((byte) (i64 >>> 32));
        byteArrayOutputStream.write((byte) (i64 >>> 40));
        byteArrayOutputStream.write((byte) (i64 >>> 48));
        byteArrayOutputStream.write((byte) (i64 >>> 56));
        return this;
    }

    public DatapackWriter writeCppString(String s) {
        byte[] str = s.getBytes(StandardCharsets.US_ASCII);
        byteArrayOutputStream.write(str, 0, str.length);
        byteArrayOutputStream.write('\0');
        return this;
    }

    public void clear() {
        byteArrayOutputStream.reset();
    }

    public byte[] encode() {
        return byteArrayOutputStream.toByteArray();
    }
}
