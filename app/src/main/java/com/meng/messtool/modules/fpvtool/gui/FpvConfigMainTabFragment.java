package com.meng.messtool.modules.fpvtool.gui;


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
import com.meng.messtool.modules.fpvtool.*;
import com.meng.messtool.modules.fpvtool.gui.pages.*;
import com.meng.messtool.modules.fpvtool.serial.msp.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.app.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static com.meng.messtool.Constant.*;

public class FpvConfigMainTabFragment extends BaseFragment implements SerialInputOutputManager.Listener {

    private enum UsbPermission {Unknown, Requested, Granted, Denied}

    public DroneStatus droneStatus = new DroneStatus();
    public MspV1Engine mspV1Engine = new MspV1Engine();

    private TabHost tabHost;

    private static final int WRITE_WAIT_MILLIS = 1000;

    private int deviceId, portNum, baudRate;

    private TextView tvFcName;

    private final BroadcastReceiver broadcastReceiver;
    private final Handler mainLooper;

    private SerialInputOutputManager usbIoManager;
    private UsbSerialPort usbSerialPort;
    private UsbPermission usbPermission = UsbPermission.Unknown;
    private boolean connected = false;
    private WatchDog watchDog;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private IFpvConfigPage[] configerPages;

    public int getCurrentTabIndex() {
        return tabHost.getCurrentTab();
    }

    private LinkedList<byte[]> sendBuffer = new LinkedList<>();

    public FpvConfigMainTabFragment() {
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
        configerPages = new IFpvConfigPage[]{
                new PageTerminal(this, 0),
                new PageStatus(this, 1),
                new PageTest(this, 2),
                new PageServo(this, 3),
                new PageReciever(this, 4),
                new PageAttitude(this, 5),
                new PageMspv1Test(this, 6),
                new PageMspv2Test(this, 7)
        };
        ThreadPool.executeAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (sendBuffer.size() > 0 && connected) {
                    try {
                        usbSerialPort.write(sendBuffer.removeFirst(), WRITE_WAIT_MILLIS);
                    } catch (Exception e) {
                        onRunError(e);
                    }
                } else {
                    sendBuffer.clear();
                }
            }
        }, 50, TimeUnit.MILLISECONDS);
    }

    /*
     * UI
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fpv_config_gui_framework, container, false);
        tvFcName = (TextView) view.findViewById(R.id.fpv_terminal_textview_fc_name);
        final TextView sendText = (TextView) view.findViewById(R.id.fpv_terminal_send_text);
        View sendBtn = view.findViewById(R.id.fpv_terminal_send_btn);
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

        watchDog = new WatchDog(20, new Runnable() {
            @Override
            public void run() {
                processData(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.reset();
            }
        });
        watchDog.start();
        if (!connected && (usbPermission == UsbPermission.Unknown || usbPermission == UsbPermission.Granted) && tabHost != null) {
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    connect();
                }
            });
        }
        tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup();

        FrameLayout tabContentView = tabHost.getTabContentView();
        for (IFpvConfigPage page : configerPages) {
            View pageMainView = page.getMainView(tabContentView);
            if (pageMainView.getId() == View.NO_ID) {
                if (Debuger.isDebugMode()) {
                    throw new IllegalArgumentException(page.getName() + ".getId() == View.NO_ID");
                }
            } else {
                if (pageMainView.getParent() == null) {
                    tabContentView.addView(pageMainView);
                }
                tabHost.addTab(tabHost.newTabSpec(page.getName()).setIndicator(page.getName(), null).setContent(pageMainView.getId()));
            }
        }
        MFragmentManager.getInstance().showFragment(FpvDevicesList.class);
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
            showToast("no action");
            return true;
        } else if (id == R.id.send_break) {
            if (!connected) {
                Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    usbSerialPort.setBreak(true);
                    Thread.sleep(100); // should show progress bar instead of blocking UI thread
                    usbSerialPort.setBreak(false);
                    showToast("send <break>");
                } catch (UnsupportedOperationException ignored) {
                    Toast.makeText(getActivity(), "BREAK not supported", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "BREAK failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        } else if (id == R.id.new_client) {
            MFragmentManager.getInstance().showFragment(FpvDevicesList.class);
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

    public void processData(byte[] data) {
        if (data.length == 0) {
            return;
        }
//        MspV1DataPack dataPack = new MspV1DataPack();
//        if (dataPack.tryDecode(data)) {
//            mspV1Engine.onMspMessage(dataPack, droneStatus);
//        } else {
//            showToast("decode failed");
//        }
        configerPages[tabHost.getCurrentTab()].processRecieved(data);
    }

    @Override
    public void onRunError(final Exception e) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
                showToast("connection lost: " + e.getMessage());
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
            showToast("connection failed: device not found");
            return;
        }
        tvFcName.setText(String.format("%s : %s", device.getManufacturerName(), device.getProductName()));
        UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
        if (driver == null) {
            showToast("connection failed: no driver for device");
            return;
        }
        if (driver.getPorts().size() < portNum) {
            showToast("connection failed: not enough ports at device");
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
                showToast("connection failed: permission denied");
            } else {
                showToast("connection failed: open failed");
            }
            return;
        }
        try {
            usbSerialPort.open(usbConnection);
            try {
                usbSerialPort.setParameters(baudRate, 8, 1, UsbSerialPort.PARITY_NONE);

            } catch (UnsupportedOperationException e) {
                showToast("unsupport setparameters");
            }
            usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
            usbIoManager.start();
            showToast("connected");
            connected = true;
        } catch (Exception e) {
            showToast("connection failed: " + e.getMessage());
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

    public void send(String str) {
        send((str + '\n').getBytes());
    }

    public void send(byte[] data) {
        if (connected) {
            sendBuffer.add(data);
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
            showToast("disconnected");
            disconnect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if (bundle != null) {
            deviceId = getArguments().getInt("device");
            portNum = getArguments().getInt("port");
            baudRate = getArguments().getInt("baud");
            connect();
        }
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
        return "飞控参数配置器，主要用于INAV";
    }
}
