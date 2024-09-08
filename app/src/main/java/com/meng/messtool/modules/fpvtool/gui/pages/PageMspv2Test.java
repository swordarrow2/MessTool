package com.meng.messtool.modules.fpvtool.gui.pages;


import android.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.util.*;
import com.meng.messtool.*;
import com.meng.messtool.modules.fpvtool.gui.*;
import com.meng.messtool.modules.fpvtool.gui.adapter.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.tools.*;

import java.nio.charset.*;

public class PageMspv2Test implements IFpvConfigPage {

    private SerialReceiveAdapter adptReceivedText;

    private FpvConfigMainTabFragment host;
    private final int tabIndex;

    public PageMspv2Test(FpvConfigMainTabFragment host, int tabIndex) {
        this.host = host;
        this.tabIndex = tabIndex;
    }

    @Override
    public View getMainView(FrameLayout tabContent) {
        View view = LayoutInflater.from(host.getContext()).inflate(R.layout.electronic_usbserial2_msp_terminal, null, false);
        ListView lvReceiveText = (ListView) view.findViewById(R.id.function_electronic_usbserial2_msp_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(host.getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) view.findViewById(R.id.fpv_terminal_send_text);
        View sendBtn = view.findViewById(R.id.fpv_terminal_send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendbytes(sendText.getText().toString());
            }
        });
        lvReceiveText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pair<String, String> pair = adptReceivedText.getItem(position);
                MspV2DataPack v2DataPack = new MspV2DataPack();
            }
        });
        lvReceiveText.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView lv = new ListView(ApplicationHolder.getActivity());
                final AlertDialog ad = new AlertDialog.Builder(ApplicationHolder.getActivity()).setTitle("选择操作").setView(lv).show();
                lv.setAdapter(new ArrayAdapter<>(host.getActivity(), android.R.layout.simple_list_item_1, new String[]{"复制文本", "复制字节"}));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                        ad.dismiss();
                        switch (p3) {
                            case 0:
                                AndroidContent.copyToClipboard(adptReceivedText.getItem(position).first);
                                break;
                            case 1:
                                AndroidContent.copyToClipboard(adptReceivedText.getItem(position).second);
                                break;
                        }
                        host.showToast("已复制到剪贴板");
                    }
                });
                return true;
            }
        });
        view.setId(getName().hashCode());
        return view;
    }

    private void sendbytes(String str) {
        String[] ss = str.split(" ");
        byte[] data = new byte[ss.length - 2];
        for (int i = 0; i < ss.length - 2; i++) {
            data[i] = (byte) Integer.parseInt(ss[i + 2], 16);
        }
        byte cb1 = (byte) Integer.parseInt(ss[0], 16);
        byte cb2 = (byte) Integer.parseInt(ss[1], 16);
        MspV2DataPack v2DataPack = new MspV2DataPack();
        v2DataPack.setCmd((short) (cb1 | (cb2 << 8)));
        v2DataPack.setFlag((byte) 0);
        v2DataPack.setPayload(data);
        byte[] encode = v2DataPack.encode();
        host.send(encode);
        adptReceivedText.add("send " + encode.length + " bytes: " + new String(encode, StandardCharsets.US_ASCII), HexDump.toHexString(encode));
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
    public String getName() {
        return "Mspv2Test";
    }

}
