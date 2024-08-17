package com.meng.messtool.modules.electronic.usbserial2.msp;

import com.meng.tools.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp
 *@author  清梦
 *@date    2024/8/16 23:02
 */
public class MspV1DataPack {
    public final byte sof = '$'; //'$'
    public final byte version = 'M';//'M'
    public byte statu;//'<' '>' '!'
    public byte payloadLength;
    public byte cmd;
    public byte[] payload;
    public byte checksum;

    private int payloadPointer = 0;

    public boolean tryDecode(byte[] data) {
        int i = 0;
        if (data[i++] == '$' && data[i++] == 'M' && data[i++] == '>') {
            payloadLength = data[i++];
            cmd = data[i++];
            payload = new byte[payloadLength & 0xFF];
            byte check = (byte) (payloadLength ^ cmd);
            for (; i < data.length - 1; i++) {
                byte b = data[i];
                check ^= b;
                payload[i - 5] = b;
            }
            checksum = check;
            return data[i] == check;
        }
        return false;
    }

    public byte[] encode() {
        byte[] result = new byte[payload.length + 6];
        int pointer = 0;
        result[pointer++] = (byte) '$';
        result[pointer++] = (byte) 'M';
        result[pointer++] = (byte) '<';
        result[pointer++] = (byte) payload.length;
        result[pointer++] = cmd;
//        byte checksum = 0;
//        checksum ^= payload.length;
//        checksum ^= messageID;
        byte checksum = (byte) (payload.length ^ cmd);
        for (; pointer < payload.length + 5; pointer++) {
            byte b = payload[pointer - 5];
            checksum ^= b;
            result[pointer] = b;
        }
        result[pointer] = checksum;
        return result;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public MspV1Command getCmdEnum() {
        for (MspV1Command c : MspV1Command.values()) {
            if (c.getCmd() == cmd) {
                return c;
            }
        }
        return MspV1Command.MSP_NULL;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        payloadLength = (byte) payload.length;
    }

    public byte getStatu() {
        return statu;
    }

    public byte getPayloadLength() {
        return payloadLength;
    }

    public byte getCmd() {
        return cmd;
    }

    public byte getChecksum() {
        return checksum;
    }

    public byte readI8() {
        return payload[payloadPointer++];
    }

    public short readI16() {
        payloadPointer += 2;
        return BitConverter.getInstanceLittleEndian().toShort(payload, payloadPointer - 2);
    }

    public int readI32() {
        payloadPointer += 4;
        return BitConverter.getInstanceLittleEndian().toInt(payload, payloadPointer - 4);
    }

    public long readI64() {
        payloadPointer += 8;
        return BitConverter.getInstanceLittleEndian().toLong(payload, payloadPointer - 8);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MspV1DataPack{");
        sb.append("sof=").append(sof);
        sb.append(", version=").append(version);
        sb.append(", statu=").append(statu);
        sb.append(", payloadLength=").append(payloadLength & 0xFF);
        sb.append(", cmd=").append(cmd);
        sb.append(", payload=").append(HexString.toHexStringWithSpace(payload));
        sb.append(", checksum=").append(checksum);
        sb.append(", payloadPointer=").append(payloadPointer);
        sb.append('}');
        return sb.toString();
    }
}
