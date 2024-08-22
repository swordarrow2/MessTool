package com.meng.tools.datapack;

/*
 *package  com.meng.tools.datapack
 *@author  清梦
 *@date    2024/8/22 9:10
 */
public class DatapackReader {

    private final byte[] data;
    private final boolean isLittleEndian;
    private int pointer = 0;

    public DatapackReader(byte[] data, boolean isLittleEndian) {
        this.data = data;
        this.isLittleEndian = isLittleEndian;
    }

    public void setOffset(int offset) {
        this.pointer = offset;
    }

    public byte readInt8() {
        return data[pointer++];
    }

    public int readUint8() {
        return data[pointer++] & 0xFF;
    }

    public short readInt16() {
        pointer += 2;
        if (isLittleEndian) {
            return (short) ((data[pointer - 2] & 0xFF) |
                    (((data[pointer - 1] & 0xFF) << 8)));
        } else {
            return (short) ((data[pointer - 1] & 0xFF) |
                    (((data[pointer - 2] & 0xFF) << 8)));
        }
    }

    public int readUint16() {
        pointer += 2;
        if (isLittleEndian) {
            return ((data[pointer - 2] & 0xFF) |
                    (((data[pointer - 1] & 0xFF) << 8)));
        } else {
            return ((data[pointer - 1] & 0xFF) |
                    (((data[pointer - 2] & 0xFF) << 8)));
        }
    }

    public int readInt32() {
        pointer += 4;
        if (isLittleEndian) {
            return ((data[pointer - 4] & 0xFF) << 0) |
                    ((data[pointer - 3] & 0xFF) << 8) |
                    ((data[pointer - 2] & 0xFF) << 16) |
                    ((data[pointer - 1] & 0xFF) << 24);
        } else {
            return ((data[pointer - 1] & 0xFF) << 0) |
                    ((data[pointer - 2] & 0xFF) << 8) |
                    ((data[pointer - 3] & 0xFF) << 16) |
                    ((data[pointer - 4] & 0xFF) << 24);
        }
    }

    public long readUint32() {
        pointer += 4;
        if (isLittleEndian) {
            return ((data[pointer - 4] & 0xFF) << 0) |
                    ((data[pointer - 3] & 0xFF) << 8) |
                    ((data[pointer - 2] & 0xFF) << 16) |
                    ((data[pointer - 1] & 0xFF) << 24);
        } else {
            return ((data[pointer - 1] & 0xFF) << 0) |
                    ((data[pointer - 2] & 0xFF) << 8) |
                    ((data[pointer - 3] & 0xFF) << 16) |
                    ((data[pointer - 4] & 0xFF) << 24);
        }
    }

    public long readInt64() {
        pointer += 8;
        if (isLittleEndian) {
            return ((data[pointer - 8] & 0xFFL) << 0) |
                    ((data[pointer - 7] & 0xFFL) << 8) |
                    ((data[pointer - 6] & 0xFFL) << 16) |
                    ((data[pointer - 5] & 0xFFL) << 24) |
                    ((data[pointer - 4] & 0xFFL) << 32) |
                    ((data[pointer - 3] & 0xFFL) << 40) |
                    ((data[pointer - 2] & 0xFFL) << 48) |
                    ((data[pointer - 1] & 0xFFL) << 56);
        } else {
            return ((data[pointer - 1] & 0xFFL) << 0) |
                    ((data[pointer - 2] & 0xFFL) << 8) |
                    ((data[pointer - 3] & 0xFFL) << 16) |
                    ((data[pointer - 4] & 0xFFL) << 24) |
                    ((data[pointer - 5] & 0xFFL) << 32) |
                    ((data[pointer - 6] & 0xFFL) << 40) |
                    ((data[pointer - 7] & 0xFFL) << 48) |
                    ((data[pointer - 8] & 0xFFL) << 56);
        }
    }

    public float readFloat() {
        pointer += 4;
        return Float.intBitsToFloat(((data[pointer - 4] & 0xFF) << 0) |
                ((data[pointer - 3] & 0xFF) << 8) |
                ((data[pointer - 2] & 0xFF) << 16) |
                ((data[pointer - 1] & 0xFF) << 24));

    }

    public double readDouble() {
        pointer += 8;
        return Double.longBitsToDouble(((data[pointer - 8] & 0xFFL) << 0) |
                ((data[pointer - 7] & 0xFFL) << 8) |
                ((data[pointer - 6] & 0xFFL) << 16) |
                ((data[pointer - 5] & 0xFFL) << 24) |
                ((data[pointer - 4] & 0xFFL) << 32) |
                ((data[pointer - 3] & 0xFFL) << 40) |
                ((data[pointer - 2] & 0xFFL) << 48) |
                ((data[pointer - 1] & 0xFFL) << 56));

    }
}
