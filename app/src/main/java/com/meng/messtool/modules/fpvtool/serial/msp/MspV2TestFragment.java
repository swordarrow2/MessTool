package com.meng.messtool.modules.fpvtool.serial.msp;

import android.app.*;
import android.content.*;
import android.hardware.usb.*;
import android.os.*;
import android.support.annotation.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.driver.*;
import com.hoho.android.usbserial.util.*;
import com.meng.messtool.*;
import com.meng.messtool.modules.electronic.usbserial2.*;
import com.meng.messtool.modules.fpvtool.serial.msp.datapack.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;

import java.io.*;
import java.nio.charset.*;

import static com.meng.messtool.Constant.*;

public class MspV2TestFragment extends BaseFragment implements SerialInputOutputManager.Listener {

    private enum UsbPermission {Unknown, Requested, Granted, Denied}

    private static final int WRITE_WAIT_MILLIS = 2000;

    private int deviceId, portNum, baudRate;

    private final BroadcastReceiver broadcastReceiver;
    private final Handler mainLooper;
    private SerialReceiveAdapter adptReceivedText;

    private SerialInputOutputManager usbIoManager;
    private UsbSerialPort usbSerialPort;
    private UsbPermission usbPermission = UsbPermission.Unknown;
    private boolean connected = false;

    @Override
    public String getTitle() {
        return "MSP V2通信";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public MspV2TestFragment() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (INTENT_ACTION_GRANT_USB.equals(intent.getAction())) {
                    usbPermission = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false) ?
                            UsbPermission.Granted : UsbPermission.Denied;
                    connect();
                }
            }
        };
        mainLooper = new Handler(Looper.getMainLooper());
    }

    /*
     * Lifecycle
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        deviceId = getArguments().getInt("device");
        portNum = getArguments().getInt("port");
        baudRate = getArguments().getInt("baud");
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(INTENT_ACTION_GRANT_USB));
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connected) {
            status("disconnected");
            disconnect();
        }
    }

    /*
     * UI
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.electronic_usbserial2_msp_terminal, container, false);
        ListView lvReceiveText = (ListView) view.findViewById(R.id.function_electronic_usbserial2_msp_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) view.findViewById(R.id.send_text);
        View sendBtn = view.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MspV2TestFragment.this.sendbytes(sendText.getText().toString());
            }
        });
        lvReceiveText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pair<String, String> pair = adptReceivedText.getItem(position);
                MspV2DataPack v2DataPack = new MspV2DataPack();
//todo
            }
        });
        lvReceiveText.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView lv = new ListView(ApplicationHolder.getActivity());
                final AlertDialog ad = new AlertDialog.Builder(ApplicationHolder.getActivity()).setTitle("选择操作").setView(lv).show();
                lv.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, new String[]{"复制文本", "复制字节"}));
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
                        showToast("已复制到剪贴板");
                    }
                });
                return true;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!connected && (usbPermission == UsbPermission.Unknown || usbPermission == UsbPermission.Granted))
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    MspV2TestFragment.this.connect();
                }
            });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_terminal, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear) {
            adptReceivedText.clear();
            adptReceivedText.notifyDataSetChanged();
            return true;
        } else if (id == R.id.send_break) {
            if (!connected) {
                Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    usbSerialPort.setBreak(true);
                    Thread.sleep(100); // should show progress bar instead of blocking UI thread
                    usbSerialPort.setBreak(false);
                    adptReceivedText.add("send <break>");
                } catch (UnsupportedOperationException ignored) {
                    Toast.makeText(getActivity(), "BREAK not supported", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "BREAK failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /*
     * Serial
     */
    @Override
    public void onNewData(final byte[] data) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                MspV2TestFragment.this.receive(data);
            }
        });
    }

    @Override
    public void onRunError(final Exception e) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                MspV2TestFragment.this.status("connection lost: " + e.getMessage());
                MspV2TestFragment.this.disconnect();
            }
        });
    }

    /*
     * Serial + UI
     */
    private void connect() {
        UsbDevice device = null;
        UsbManager usbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        if (usbManager != null) {
            for (UsbDevice v : usbManager.getDeviceList().values()) {
                if (v.getDeviceId() == deviceId) {
                    device = v;
                }
            }
        }
        if (device == null) {
            status("connection failed: device not found");
            return;
        }
        UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
        if (driver == null) {
            driver = CustomProber.getCustomProber().probeDevice(device);
        }
        if (driver == null) {
            status("connection failed: no driver for device");
            return;
        }
        if (driver.getPorts().size() < portNum) {
            status("connection failed: not enough ports at device");
            return;
        }
        usbSerialPort = driver.getPorts().get(portNum);
        UsbDeviceConnection usbConnection = usbManager.openDevice(driver.getDevice());
        if (usbConnection == null && usbPermission == UsbPermission.Unknown && !usbManager.hasPermission(driver.getDevice())) {
            usbPermission = UsbPermission.Requested;
            Intent intent = new Intent(INTENT_ACTION_GRANT_USB);
            intent.setPackage(getActivity().getPackageName());
            PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
            usbManager.requestPermission(driver.getDevice(), usbPermissionIntent);
            return;
        }
        if (usbConnection == null) {
            if (!usbManager.hasPermission(driver.getDevice())) {
                status("connection failed: permission denied");
            } else {
                status("connection failed: open failed");
            }
            return;
        }
        try {
            usbSerialPort.open(usbConnection);
            try {
                usbSerialPort.setParameters(baudRate, 8, 1, UsbSerialPort.PARITY_NONE);
            } catch (UnsupportedOperationException e) {
                status("unsupport setparameters");
            }
            usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
            usbIoManager.start();
            status("connected");
            connected = true;
        } catch (Exception e) {
            status("connection failed: " + e.getMessage());
            disconnect();
        }
    }

    private void disconnect() {
        connected = false;
        if (usbIoManager != null) {
            usbIoManager.setListener(null);
            usbIoManager.stop();
        }
        usbIoManager = null;
        try {
            usbSerialPort.close();
        } catch (IOException ignored) {
        }
        usbSerialPort = null;
    }

    private void sendbytes(String str) {
        if (!connected) {
            Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] ss = str.split(" ");
        byte[] data = new byte[ss.length - 2];
        for (int i = 0; i < ss.length - 2; i++) {
            data[i] = (byte) Integer.parseInt(ss[i + 2], 16);
        }
        try {
//            MspV1DataPack dataPack = new MspV1DataPack();
//            dataPack.setCmd((byte) Integer.parseInt(ss[0], 16));
//            dataPack.setPayload(data);
            byte cb1 = (byte) Integer.parseInt(ss[0], 16);
            byte cb2 = (byte) Integer.parseInt(ss[1], 16);
//            data = new byte[]{};
            MspV2DataPack v2DataPack = new MspV2DataPack();
            v2DataPack.setCmd((short) (cb1 | (cb2 << 8)));
            v2DataPack.setFlag((byte) 0);
            v2DataPack.setPayload(data);
            byte[] encode = v2DataPack.encode(); //dataPack.encode();
            usbSerialPort.write(encode, WRITE_WAIT_MILLIS);
            adptReceivedText.add("send " + encode.length + " bytes: " + new String(encode, StandardCharsets.US_ASCII), HexDump.toHexString(encode));
            adptReceivedText.notifyDataSetChanged();
        } catch (Exception e) {
            onRunError(e);
        }
    }

    private void receive(byte[] data) {
//        MspV1DataPack dataPack = new MspV1DataPack();
        MspV2DataPack v2DataPack = new MspV2DataPack();
        // boolean b = ;// dataPack.tryDecode(data);
        adptReceivedText.add("receive " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII) + (v2DataPack.tryDecode(data)), HexString.toHexStringWithSpace(data));// HexDump.toHexString(data));
        adptReceivedText.notifyDataSetChanged();
    }

    void status(String str) {
        adptReceivedText.add(str);
        adptReceivedText.notifyDataSetChanged();
    }

}
