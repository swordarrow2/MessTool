package com.meng.messtool.modules.fpvtool.gui.pages;


import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.messtool.modules.fpvtool.serial.msp.payload.*;
import com.meng.tools.app.*;

import java.util.concurrent.*;

public class PageAttitude implements IFpvConfigPage {

    private FpvConfigMainTabFragment host;
    private final int tabIndex;

    public PageAttitude(FpvConfigMainTabFragment host, int tabIndex) {
        this.host = host;
        this.tabIndex = tabIndex;
    }

    public TextView status;

    @Override
    public View getMainView(FrameLayout tabContent) {
        status = (TextView) tabContent.findViewById(R.id.fpv_device_status_textview);
        ThreadPool.executeAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (host.getCurrentTabIndex() == tabIndex) {
                    MspV1DataPack dataPack = new MspV1DataPack();
                    dataPack.setCmd(MspV1Cmd.MSP_ATTITUDE);
                    host.send(dataPack.encode());
                }
            }
        }, 50, TimeUnit.MILLISECONDS);
        return tabContent.findViewById(R.id.fpv_device_status_main);
    }

    @Override
    public void processRecieved(final byte[] data) {
        MspV1DataPack dataPack = new MspV1DataPack();
        dataPack.tryDecode(data);
        if (dataPack.getCmdEnum() != MspV1Cmd.MSP_ATTITUDE) {
            return;
        }
        final MSP_ATTITUDE msp_attitude = new MSP_ATTITUDE(dataPack.getPayload());
        host.droneStatus.msp_attitude = msp_attitude;
        host.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                status.setText(msp_attitude.toString());
            }
        });
    }

    @Override
    public String getName() {
        return "姿态";
    }

}
