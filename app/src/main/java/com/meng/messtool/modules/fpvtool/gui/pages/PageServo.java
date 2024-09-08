package com.meng.messtool.modules.fpvtool.gui.pages;


import android.view.*;
import android.widget.*;

import com.meng.messtool.customview.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;
import com.meng.tools.app.*;

import java.util.concurrent.*;

public class PageServo implements IFpvConfigPage {

    private FpvConfigMainTabFragment host;
    private LinearLayout mainLayout;

    private final int tabIndex;

    public PageServo(FpvConfigMainTabFragment host, int tabIndex) {
        this.host = host;
        this.tabIndex = tabIndex;
    }

    @Override
    public View getMainView(FrameLayout tabContent) {
        mainLayout = new LinearLayout(host.getActivity());
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setId(getName().hashCode());

        for (int i = 0; i < 16; i++) {
            MengSeekBar mengSeekBar = new MengSeekBar(host.getActivity());
            mengSeekBar.setMax(2000);
            mengSeekBar.setText("servo" + i);
            mengSeekBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(mengSeekBar);
        }

        ThreadPool.executeAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (host.getCurrentTabIndex() == tabIndex) {
                    MspV1DataPack dataPack = new MspV1DataPack();
                    dataPack.setCmd(MspV1Cmd.MSP_SERVO);
                    host.send(dataPack.encode());
                }
            }
        }, 50, TimeUnit.MILLISECONDS);
        return mainLayout;
    }

    @Override
    public void processRecieved(final byte[] data) {
        host.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MspV1DataPack dataPack = new MspV1DataPack();
                dataPack.tryDecode(data);
                if (dataPack.getCmdEnum() != MspV1Cmd.MSP_SERVO) {
                    return;
                }
                final MSP_SERVO msp_servo = new MSP_SERVO(dataPack.getPayload());
                host.droneStatus.msp_servo = msp_servo;
                host.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mainLayout.getChildCount(); i++) {
                            MengSeekBar seekBar = (MengSeekBar) mainLayout.getChildAt(i);
                            int servo = msp_servo.servos[i];
                            seekBar.setProgress(servo - 500);
                            seekBar.setText("servo" + i + ":" + servo);
                        }
                    }
                });
            }
        });
    }

    @Override
    public String getName() {
        return "舵机";
    }

}
