package com.meng.messtool.modules.fpvtool.serial.msp.payload;

import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.tools.datapack.*;

import java.util.*;

/*
 *package  com.meng.messtool.modules.fpvtool.serial.msp.payload
 *@author  清梦
 *@date    2024/9/3 17:18
 */
public class MSP_RAW_IMU implements IDecodeable {

    private static final String TAG = "MSP_RAW_IMU";

    public int[] accADC = new int[3];
    public int[] gyroRateDps = new int[3];
    public int[] magADC = new int[3];

    public MSP_RAW_IMU(int[] accADC, int[] gyroRateDps, int[] magADC) {
        this.accADC = accADC;
        this.gyroRateDps = gyroRateDps;
        this.magADC = magADC;
    }

    public MSP_RAW_IMU(byte[] data) {
        decode(data);
    }

    @Override
    public void decode(byte[] data) {
        DatapackReader reader = new DatapackReader(data, true);
        for (int i = 0; i < accADC.length; i++) {
            accADC[i] = reader.readInt16();
        }
        for (int i = 0; i < gyroRateDps.length; i++) {
            gyroRateDps[i] = reader.readInt16();
        }
        for (int i = 0; i < magADC.length; i++) {
            magADC[i] = reader.readUint16();
        }
        reader.checkFinish();
    }

    @Override
    public String toString() {
        return "MSP_RAW_IMU{" + "accADC=" + Arrays.toString(accADC) +
                ", gyroRateDps=" + Arrays.toString(gyroRateDps) +
                ", magADC=" + Arrays.toString(magADC) +
                '}';
    }
}
