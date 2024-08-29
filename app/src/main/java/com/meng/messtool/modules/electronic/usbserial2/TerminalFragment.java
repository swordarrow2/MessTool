package com.meng.messtool.modules.electronic.usbserial2;

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
import com.meng.messtool.system.base.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

import static com.meng.messtool.Constant.*;

public class TerminalFragment extends BaseFragment implements SerialInputOutputManager.Listener {

    private enum UsbPermission {Unknown, Requested, Granted, Denied}

    private static final int WRITE_WAIT_MILLIS = 2000;
    private static final int READ_WAIT_MILLIS = 2000;

    private int deviceId, portNum, baudRate;
    private boolean withIoManager;

    private final BroadcastReceiver broadcastReceiver;
    private final Handler mainLooper;
    private SerialReceiveAdapter adptReceivedText;
    private ControlLines controlLines;

    private SerialInputOutputManager usbIoManager;
    private UsbSerialPort usbSerialPort;
    private UsbPermission usbPermission = UsbPermission.Unknown;
    private boolean connected = false;

    @Override
    public String getTitle() {
        return "USB串口通信";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public TerminalFragment() {
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
        withIoManager = getArguments().getBoolean("withIoManager");
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
        View view = inflater.inflate(R.layout.function_electronic_usbserial2_fragment_terminal, container, false);
        ListView lvReceiveText = (ListView) view.findViewById(R.id.function_electronic_usbserial2_terminal_list);
        adptReceivedText = new SerialReceiveAdapter(getActivity());
        lvReceiveText.setAdapter(adptReceivedText);
        final TextView sendText = (TextView) view.findViewById(R.id.send_text);
        View sendBtn = view.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerminalFragment.this.send(sendText.getText().toString());
            }
        });
        View receiveBtn = view.findViewById(R.id.receive_btn);
        controlLines = new ControlLines(view);
        if (withIoManager) {
            receiveBtn.setVisibility(View.GONE);
        } else {
            receiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TerminalFragment.this.read();
                }
            });
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!connected && (usbPermission == UsbPermission.Unknown || usbPermission == UsbPermission.Granted))
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    TerminalFragment.this.connect();
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
                TerminalFragment.this.receive(data);
            }
        });
    }

    @Override
    public void onRunError(final Exception e) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                TerminalFragment.this.status("connection lost: " + e.getMessage());
                TerminalFragment.this.disconnect();
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
            if (withIoManager) {
                usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
                usbIoManager.start();
            }
            status("connected");
            connected = true;
            controlLines.start();
        } catch (Exception e) {
            status("connection failed: " + e.getMessage());
            disconnect();
        }
    }

    private void disconnect() {
        connected = false;
        controlLines.stop();
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

    private void read() {
        if (!connected) {
            Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            byte[] buffer = new byte[8192];
            int len = usbSerialPort.read(buffer, READ_WAIT_MILLIS);
            receive(Arrays.copyOf(buffer, len));
        } catch (IOException e) {
            // when using read with timeout, USB bulkTransfer returns -1 on timeout _and_ errors
            // like connection loss, so there is typically no exception thrown here on error
            status("connection lost: " + e.getMessage());
            disconnect();
        }
    }

    private void receive(byte[] data) {
        adptReceivedText.add("receive " + data.length + " bytes: " + new String(data, StandardCharsets.US_ASCII), HexDump.toHexString(data));
        adptReceivedText.notifyDataSetChanged();
    }

    void status(String str) {
        adptReceivedText.add(str);
        adptReceivedText.notifyDataSetChanged();
    }

    class ControlLines {
        private static final int refreshInterval = 200; // msec

        private final Runnable runnable;
        private final ToggleButton rtsBtn, ctsBtn, dtrBtn, dsrBtn, cdBtn, riBtn;

        ControlLines(View view) {
            runnable = new Runnable() {

                @Override
                public void run() {
                    ControlLines.this.run();
                }
            };
            rtsBtn = (ToggleButton) view.findViewById(R.id.controlLineRts);
            ctsBtn = (ToggleButton) view.findViewById(R.id.controlLineCts);
            dtrBtn = (ToggleButton) view.findViewById(R.id.controlLineDtr);
            dsrBtn = (ToggleButton) view.findViewById(R.id.controlLineDsr);
            cdBtn = (ToggleButton) view.findViewById(R.id.controlLineCd);
            riBtn = (ToggleButton) view.findViewById(R.id.controlLineRi);
            rtsBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View p1) {
                    toggle(p1);
                }
            });
            dtrBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View p1) {
                    toggle(p1);
                }
            });
        }

        private void toggle(View v) {
            ToggleButton btn = (ToggleButton) v;
            if (!connected) {
                btn.setChecked(!btn.isChecked());
                Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
                return;
            }
            String ctrl = "";
            try {
                if (btn.equals(rtsBtn)) {
                    ctrl = "RTS";
                    usbSerialPort.setRTS(btn.isChecked());
                }
                if (btn.equals(dtrBtn)) {
                    ctrl = "DTR";
                    usbSerialPort.setDTR(btn.isChecked());
                }
            } catch (IOException e) {
                status("set" + ctrl + "() failed: " + e.getMessage());
            }
        }

        private void run() {
            if (!connected)
                return;
            try {
                EnumSet<UsbSerialPort.ControlLine> controlLines = usbSerialPort.getControlLines();
                rtsBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.RTS));
                ctsBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.CTS));
                dtrBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.DTR));
                dsrBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.DSR));
                cdBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.CD));
                riBtn.setChecked(controlLines.contains(UsbSerialPort.ControlLine.RI));
                mainLooper.postDelayed(runnable, refreshInterval);
            } catch (Exception e) {
                status("getControlLines() failed: " + e.getMessage() + " -> stopped control line refresh");
            }
        }

        void start() {
            if (!connected)
                return;
            try {
                EnumSet<UsbSerialPort.ControlLine> controlLines = usbSerialPort.getSupportedControlLines();
                if (!controlLines.contains(UsbSerialPort.ControlLine.RTS)) {
                    rtsBtn.setEnabled(false);
                }
                if (!controlLines.contains(UsbSerialPort.ControlLine.CTS)) {
                    ctsBtn.setEnabled(false);
                }
                if (!controlLines.contains(UsbSerialPort.ControlLine.DTR)) {
                    dtrBtn.setEnabled(false);
                }
                if (!controlLines.contains(UsbSerialPort.ControlLine.DSR)) {
                    dsrBtn.setEnabled(false);
                }
                if (!controlLines.contains(UsbSerialPort.ControlLine.CD)) {
                    cdBtn.setEnabled(false);
                }
                if (!controlLines.contains(UsbSerialPort.ControlLine.RI)) {
                    riBtn.setEnabled(false);
                }
                run();
            } catch (Exception e) {
                Toast.makeText(getActivity(), "getSupportedControlLines() failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                rtsBtn.setEnabled(false);
                ctsBtn.setEnabled(false);
                dtrBtn.setEnabled(false);
                dsrBtn.setEnabled(false);
                cdBtn.setEnabled(false);
                riBtn.setEnabled(false);
            }
        }

        void stop() {
            mainLooper.removeCallbacks(runnable);
            rtsBtn.setChecked(false);
            ctsBtn.setChecked(false);
            dtrBtn.setChecked(false);
            dsrBtn.setChecked(false);
            cdBtn.setChecked(false);
            riBtn.setChecked(false);
        }
    }
}
