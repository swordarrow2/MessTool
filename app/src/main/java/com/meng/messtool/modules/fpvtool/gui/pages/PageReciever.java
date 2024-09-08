package com.meng.messtool.modules.fpvtool.gui.pages;


import android.view.*;
import android.widget.*;

import com.meng.messtool.customview.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;
import com.meng.tools.app.*;

import java.util.concurrent.*;

public class PageReciever implements IFpvConfigPage {

    private FpvConfigMainTabFragment host;
    private LinearLayout mainLayout;

    private final int tabIndex;

    public PageReciever(FpvConfigMainTabFragment host, int tabIndex) {
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
            mengSeekBar.setMax(3000);
            switch (i) {
                case 0:
                    mengSeekBar.setText("横滚");
                    break;
                case 1:
                    mengSeekBar.setText("俯仰");
                    break;
                case 2:
                    mengSeekBar.setText("偏航");
                    break;
                case 3:
                    mengSeekBar.setText("油门");
                    break;
                default:
                    mengSeekBar.setText("AUX" + (i - 4));
            }
            mengSeekBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(mengSeekBar);
        }

        ThreadPool.executeAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (host.getCurrentTabIndex() == tabIndex) {
                    MspV1DataPack dataPack = new MspV1DataPack();
                    dataPack.setCmd(MspV1Cmd.MSP_RC);
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
                if (dataPack.getCmdEnum() != MspV1Cmd.MSP_RC) {
                    return;
                }
                final MSP_RC msp_servo = new MSP_RC(dataPack.getPayload());
                host.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mainLayout.getChildCount(); i++) {
                            MengSeekBar seekBar = (MengSeekBar) mainLayout.getChildAt(i);
                            int channel = msp_servo.rxGetChannelValue[i];
                            seekBar.setProgress(channel);
                            switch (i) {
                                case 0:
                                    seekBar.setText("横滚" + ":" + channel);
                                    break;
                                case 1:
                                    seekBar.setText("俯仰" + ":" + channel);
                                    break;
                                case 2:
                                    seekBar.setText("偏航" + ":" + channel);
                                    break;
                                case 3:
                                    seekBar.setText("油门" + ":" + channel);
                                    break;
                                default:
                                    seekBar.setText("AUX" + (i - 4) + ":" + channel);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public String getName() {
        return "接收机";
    }

}
