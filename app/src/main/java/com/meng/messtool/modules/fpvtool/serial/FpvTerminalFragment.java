package com.meng.messtool.modules.fpvtool.serial;


import android.app.*;
import android.content.*;
import android.hardware.usb.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.driver.*;
import com.hoho.android.usbserial.util.*;
import com.meng.messtool.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;

import java.io.*;
import java.nio.charset.*;

import static com.meng.messtool.Constant.*;

public class FpvTerminalFragment extends BaseFragment implements SerialInputOutputManager.Listener {

    private enum UsbPermission {Unknown, Requested, Granted, Denied}

    private boolean onRecord = false;

    private static final int WRITE_WAIT_MILLIS = 2000;

    private int deviceId, portNum, baudRate;

    private TextView tvFcName;

    private final BroadcastReceiver broadcastReceiver;
    private final Handler mainLooper;
    private SerialReceiveAdapter adptReceivedText;

    private SerialInputOutputManager usbIoManager;
    private UsbSerialPort usbSerialPort;
    private UsbPermission usbPermission = UsbPermission.Unknown;
    private boolean connected = false;
    private WatchDog watchDog;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public FpvTerminalFragment() {
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
     * UI
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fpv_terminal, container, false);
        tvFcName = (TextView) view.findViewById(R.id.fpv_terminal_textview_fc_name);
        ListView lvReceiveText = (ListView) view.findViewById(R.id.function_electronic_usbserial2_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) view.findViewById(R.id.send_text);
        View sendBtn = view.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send(sendText.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        watchDog = new WatchDog(100, new Runnable() {
            @Override
            public void run() {
                processData(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
            }
        });
        watchDog.start();
        if (!connected && (usbPermission == UsbPermission.Unknown || usbPermission == UsbPermission.Granted))
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    connect();
                    onRecord = true;
                }
            });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_terminal, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
        } else if (id == R.id.new_client) {
            MFragmentManager.getInstance().showFragment(FpvDevicesFragment.class);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /*
     * Serial
     */

    @Override
    public void onNewData(byte[] data) {
        watchDog.feedDog();
        byteArrayOutputStream.write(data, 0, data.length);
    }

    public void processData(final byte[] data) {
        if (data.length == 0) {
            return;
        }
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                adptReceivedText.add("receive " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
                adptReceivedText.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onRunError(final Exception e) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                status("connection lost: " + e.getMessage());
                disconnect();
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
        tvFcName.setText(String.format("%s : %s", device.getManufacturerName(), device.getProductName()));
        UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);

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

    private void send(String str) {
        if (!connected) {
            Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            byte[] data = (str + '\n').getBytes();
            adptReceivedText.add("send " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
            adptReceivedText.notifyDataSetChanged();
            usbSerialPort.write(data, WRITE_WAIT_MILLIS);
        } catch (Exception e) {
            onRunError(e);
        }
    }

    void status(String str) {
        if (onRecord) {
            adptReceivedText.add(str);
            adptReceivedText.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
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

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        deviceId = getArguments().getInt("device");
        portNum = getArguments().getInt("port");
        baudRate = getArguments().getInt("baud");
        connect();
    }

    @Override
    public String getTitle() {
        return "飞控参数配置器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public CharSequence getDescribe() {
        return "飞控参数配置器，用于BetaFlight和INAV";
    }
}
