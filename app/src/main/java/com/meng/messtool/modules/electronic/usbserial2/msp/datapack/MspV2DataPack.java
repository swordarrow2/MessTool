package com.meng.messtool.modules.electronic.usbserial2.msp;

import android.util.*;

import com.meng.messtool.*;
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
    public byte[] payload;
    public byte checksum;

    private int payloadPointer = 0;

//    private byte crc8_dvb_s2(byte crc, byte a) {
//        crc ^= a;
//        for (int ii = 0; ii < 8; ++ii) {
//            if ((crc & 0x80) != 0) {
//                crc = (byte) ((crc << 1) ^ 0xD5);
//            } else {
//                crc = (byte) (crc << 1);
//            }
//        }
//        return crc;
//    }

    public String tryDecode(final byte[] data) {
        if (data[0] == '$' && data[1] == 'X' && data[2] == '>') {
            flag = data[3];
            byte cmd1 = data[4];
            byte cmd2 = data[5];
            cmd = (short) (cmd1 | (cmd2 << 8));

            byte payLength1 = data[6];
            byte payLength2 = data[7];
            payloadLength = (short) (payLength1 | (payLength2 << 8));

            payload = new byte[payloadLength & 0xFFFF];

            byte csm = 0;
           CRC8_DVB_S2 crc8_dvb_s2= CRC8_DVB_S2.getInstance();
            csm = crc8_dvb_s2.add(csm, (byte) 0);
            csm = crc8_dvb_s2(csm, (byte) cmd);
            csm = crc8_dvb_s2(csm, (byte) (cmd >>> 8));
            csm = crc8_dvb_s2(csm, (byte) payloadLength);
            csm = crc8_dvb_s2(csm, (byte) (payloadLength >>> 8));

            int pointer = 8;
            for (; pointer < data.length - 1; pointer++) {
                byte b = data[pointer];
                payload[pointer - 8] = b;
                csm = crc8_dvb_s2(csm, b);
            }
            checksum = csm;
            try {
                final byte c1 = csm;
                final int p1 = pointer;
                ApplicationHolder.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Debuger.addLog(getClass().getSimpleName(), String.format(Locale.CHINA, "%d not equals %d", data[p1], c1));
                            ApplicationHolder.getActivity().setTitle(String.format(Locale.CHINA, "%d not equals %d", data[p1], c1));
                        } catch (Exception e) {
                        }
                    }
                });
            } catch (Exception e) {
            }
            if (data[pointer] == csm) {
                return "llegal";
            }
            return "illegal" + String.format(Locale.CHINA, "%d not equals %d", data[pointer], csm);
        }
        return "not";
    }


    public byte[] encode() {
        byte[] result = new byte[payload.length + 9];
        result[0] = (byte) '$';
        result[1] = (byte) 'X';
        result[2] = (byte) '<';
        result[3] = (byte) 0;
        result[4] = (byte) cmd;
        result[5] = (byte) (cmd >>> 8);
        result[6] = (byte) payloadLength;
        result[7] = (byte) (payloadLength >>> 8);


        byte csm = 0;
        csm = crc8_dvb_s2(csm, (byte) 0);
        csm = crc8_dvb_s2(csm, (byte) cmd);
        csm = crc8_dvb_s2(csm, (byte) (cmd >>> 8));
        csm = crc8_dvb_s2(csm, (byte) payloadLength);
        csm = crc8_dvb_s2(csm, (byte) (payloadLength >>> 8));

        int pointer = 8;
        for (; pointer < payload.length + 8; pointer++) {
            byte b = payload[pointer - 8];
            result[pointer] = b;
            csm = crc8_dvb_s2(csm, b);
        }
        result[pointer] = csm;

        return result;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public MspV2Cmd getCmdEnum() {
        for (MspV2Cmd c : MspV2Cmd.values()) {
            if (c.getCmd() == cmd) {
                return c;
            }
        }
        return MspV2Cmd.MSP2_NULL;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
        payloadLength = (short) payload.length;
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
        final StringBuilder sb = new StringBuilder("MspV2DataPack{");
        sb.append("sof=").append((char) sof);
        sb.append(", version=").append((char) version);
        sb.append(", statu=0x").append(statu);
        sb.append(", payloadLength=").append(payloadLength & 0xFFFF);
        sb.append(", cmd=0x").append(Integer.toHexString(cmd & 0xFFFF));
        if (payload != null) {
            sb.append(", payload=").append(HexString.toHexStringWithSpace(payload));
        }
        if (cmd == 0x2000) {
            sb.append(",cycleTime:").append(readI16() & 0xFFFF);
            sb.append(",i2cErrorCount:").append(readI16() & 0xFFFF);
            int SensorStatus = readI16() & 0xFFFF;
            sb.append(",SensorStatus:").append(Integer.toBinaryString(SensorStatus)).append(" hex:").append(Integer.toHexString(SensorStatus));
            sb.append(",averageSystemLoadPercent:").append(readI16() & 0xFFFF);
            int config = readI8() & 0xFF;
            sb.append(",Config:").append(Integer.toBinaryString(config)).append(" hex:").append(Integer.toHexString(config));
            int armingFlags = readI32();
            sb.append(",armingFlags:").append(Integer.toBinaryString(armingFlags)).append(" hex:").append(Integer.toHexString(armingFlags));
            long mode = readI64();
            sb.append(",mode:").append(Long.toBinaryString(mode)).append(" hex:").append(Long.toHexString(mode));
            if (mode == 1 << 18) {
                sb.append(",fs mode");
            }
            if (mode == 1 << 19) {
                sb.append(",BOXNAVWP mode");
            }
            sb.append(",ConfigMixerProfile:").append(readI8() & 0xFF);
        }
        sb.append(", checksum=0x").append(Integer.toHexString(checksum & 0xFF));


        sb.append('}');
        return sb.toString();
    }
}
