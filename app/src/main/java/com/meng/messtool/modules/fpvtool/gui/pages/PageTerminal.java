package com.meng.messtool.modules.fpvtool.gui.pages;


import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.util.*;
import com.meng.messtool.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.fpvtool.gui.adapter.*;

import java.nio.charset.*;

public class PageTerminal implements IFpvConfigPage {

    private SerialReceiveAdapter adptReceivedText;

    private FpvConfigMainTabFragment host;
    private final int tabIndex;

    public PageTerminal(FpvConfigMainTabFragment host, int tabIndex) {
        this.host = host;
        this.tabIndex = tabIndex;
    }

    @Override
    public View getMainView(FrameLayout tabContent) {
        ListView lvReceiveText = (ListView) tabContent.findViewById(R.id.fpv_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(host.getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) tabContent.findViewById(R.id.fpv_terminal_send_text);
        View sendBtn = tabContent.findViewById(R.id.fpv_terminal_send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = (sendText.getText().toString() + '\n').getBytes();
                adptReceivedText.add("send " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
                adptReceivedText.notifyDataSetChanged();
                host.send(data);
            }
        });

        return tabContent.findViewById(R.id.fpv_terminal_main_layout);
    }

    @Override
    public void processRecieved(final byte[] data) {
        host.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adptReceivedText.add("receive " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
                adptReceivedText.notifyDataSetChanged();
            }
        });
    }

    @Override
    public String getName() {
        return "命令行";
    }

}
