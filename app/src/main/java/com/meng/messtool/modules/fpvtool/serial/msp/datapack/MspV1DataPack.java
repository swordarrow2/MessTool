package com.meng.messtool.modules.fpvtool.serial.msp.datapack;

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
    public byte[] payload = new byte[0];
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

    public void setCmd(MspV1Cmd cmd) {
        this.cmd = cmd.getCmd();
    }

    public MspV1Cmd getCmdEnum() {
        for (MspV1Cmd c : MspV1Cmd.values()) {
            if (c.getCmd() == cmd) {
                return c;
            }
        }
        return MspV1Cmd.MSP_NULL;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        payloadLength = (byte) payload.length;
    }

    public byte getStatu() {
        return statu;
    }

    public byte[] getPayload() {
        return payload;
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


    @Override
    public String toString() {
        return "MspV1DataPack{" + "sof=" + sof +
                ", version=" + version +
                ", statu=" + statu +
                ", payloadLength=" + (payloadLength & 0xFF) +
                ", cmd=" + cmd +
                ", payload=" + HexString.toHexStringWithSpace(payload) +
                ", checksum=" + checksum +
                ", payloadPointer=" + payloadPointer +
                '}';
    }
}
