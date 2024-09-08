package com.meng.messtool.modules.fpvtool.serial.msp.datapack;

import com.meng.tools.*;
import com.meng.tools.hash.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.electronic.usbserial2.msp
 *@author  清梦
 *@date    2024/8/16 23:02
 */
public class MspV2DataPack {
    public final byte sof = '$'; //'$'
    public final byte version = 'X';//'M'
    public byte statu;//'<' '>' '!'
    public byte flag;
    public short cmd;
    public short payloadLength;
    public byte[] payload = new byte[0];
    public byte checksum;


    public static boolean tryDecode(final byte[] data) {
        MspV2DataPack dataPack = new MspV2DataPack();
        if (data[0] != '$' || data[1] != 'X' || data[2] != '>') {
            return false;
        }
        dataPack.flag = data[3];
        byte cmd1 = data[4];
        byte cmd2 = data[5];
        dataPack.cmd = (short) (cmd1 | (cmd2 << 8));

        byte payLength1 = data[6];
        byte payLength2 = data[7];
        dataPack.payloadLength = (short) (payLength1 | (payLength2 << 8));

        dataPack.payload = new byte[dataPack.payloadLength & 0xFFFF];

        CRC8_DVB_S2 hash = CRC8_DVB_S2.getInstance();
        hash.addHash((byte) 0);
        hash.addHash((byte) dataPack.cmd);
        hash.addHash((byte) (dataPack.cmd >>> 8));
        hash.addHash((byte) dataPack.payloadLength);
        hash.addHash((byte) (dataPack.payloadLength >>> 8));

        int pointer = 8;
        for (; pointer < data.length - 1; pointer++) {
            byte b = data[pointer];
            dataPack.payload[pointer - 8] = b;
            hash.addHash(b);
        }
        dataPack.checksum = hash.getCrc();
        return data[pointer] == dataPack.checksum;
    }


    public static byte[] encode(MspV2Cmd cmd, byte[] payload) {
        CRC8_DVB_S2 hash = CRC8_DVB_S2.getInstance();

        byte[] result = new byte[payload.length + 9];
        result[0] = (byte) '$';
        result[1] = (byte) 'X';
        result[2] = (byte) '<';

        hash.addHash(result[3] = (byte) 0);
        hash.addHash(result[4] = (byte) cmd.getCmd());
        hash.addHash(result[5] = (byte) (cmd.getCmd() >>> 8));
        hash.addHash(result[6] = (byte) payload.length);
        hash.addHash(result[7] = (byte) (payload.length >>> 8));

        int pointer = 8;
        for (; pointer < payload.length + 8; pointer++) {
            hash.addHash(result[pointer] = payload[pointer - 8]);
        }
        result[pointer] = hash.getCrc();

        return result;
    }

    public byte[] encode() {
        return encode(MspV2Cmd.getCmd(cmd), payload);
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        payloadLength = (short) payload.length;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public byte getFlag() {
        return flag;
    }

    public byte getStatu() {
        return statu;
    }

    public short getPayloadLength() {
        return payloadLength;
    }

    public short getCmd() {
        return cmd;
    }

    public byte getChecksum() {
        return checksum;
    }

    @Override
    public String toString() {
        return "MspV2DataPack{" + "sof=" + sof +
                ", version=" + version +
                ", statu=" + statu +
                ", flag=" + flag +
                ", cmd=" + cmd +
                ", payloadLength=" + payloadLength +
                ", payload=" + Arrays.toString(payload) +
                ", checksum=" + checksum +
                '}';
    }
}
