package com.meng.messtool.modules.fpvtool.serial;


import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.util.*;
import com.meng.messtool.*;

import java.nio.charset.*;

public class FpvConfigGuiTerminal implements IFpvConfigerPart {

    private SerialReceiveAdapter adptReceivedText;

    private FpvConfigGuiFragment host;

    public FpvConfigGuiTerminal(FpvConfigGuiFragment host) {
        this.host = host;
    }

    @Override
    public void initView(View view) {
        ListView lvReceiveText = (ListView) view.findViewById(R.id.function_electronic_usbserial2_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(host.getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) view.findViewById(R.id.send_text);
        View sendBtn = view.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!host.isConnected()) {
                    host.showToast("not connected");
                    return;
                }
                try {
                    byte[] data = (sendText.getText().toString() + '\n').getBytes();
                    adptReceivedText.add("send " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
                    adptReceivedText.notifyDataSetChanged();
                    host.send(data);
                } catch (Exception e) {
                    host.onRunError(e);
                }
            }
        });
    }

    void status(String str) {
        adptReceivedText.add(str);
        adptReceivedText.notifyDataSetChanged();
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
    public int getMainView() {
        return R.id.fpv_terminal_main_layout;
    }


    @Override
    public String getName() {
        return "飞控参数配置器";
    }
}
