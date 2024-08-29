package com.meng.messtool.modules.electronic.usbserial2.msp;

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
import com.meng.messtool.customview.*;
import com.meng.messtool.modules.electronic.usbserial2.*;
import com.meng.messtool.modules.electronic.usbserial2.msp.datapack.*;
import com.meng.messtool.modules.electronic.usbserial2.msp.sensor.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.app.*;
import java.io.*;
import java.util.concurrent.*;

import static com.meng.messtool.Constant.*;

public class MspSensorTestFragment extends BaseFragment implements SerialInputOutputManager.Listener {

    private enum UsbPermission {Unknown, Requested, Granted, Denied}

    private static final int WRITE_WAIT_MILLIS = 2000;

    private int deviceId, portNum, baudRate;

    private final BroadcastReceiver broadcastReceiver;
    private final Handler mainLooper;

    private SerialInputOutputManager usbIoManager;
    private UsbSerialPort usbSerialPort;
    private UsbPermission usbPermission = UsbPermission.Unknown;
    private boolean connected = false;
    private ScheduledFuture<?> sendTask;


    private MengSeekBar sbRangeFinderQuality;
    private MengSeekBar sbRangeFinderDistanceMm;
    private TextView tvRangeFinderBytes;

    private MengSeekBar sbOpflowQuality;
    private MengSeekBar sbOpflowMotionX;
    private MengSeekBar sbOpflowMotionY;
    private TextView tvOpflowBytes;


    private MengSeekBar sbBarotimeMs;
    private MengSeekBar sbBaropressurePa;
    private MengSeekBar sbBaroTemp;
    private TextView tvBaroBytes;


    private MengSeekBar sbAirTimeMs;
    private MengSeekBar sbAirPressurePa;
    private MengSeekBar sbAirTemp;
    private TextView tvAirBytes;


    @Override
    public String getTitle() {
        return "MSP虚拟传感器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public MspSensorTestFragment() {
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
            disconnect();
        }
    }

    /*
     * UI
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.function_electronic_usbserial2_msp_sensor, container, false);

        sbRangeFinderQuality = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_range_finder_quality);
        sbRangeFinderDistanceMm = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_range_finder_distanceMm);
        tvRangeFinderBytes = (TextView) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_range_finder_text);


        sbOpflowQuality = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_opflow_quality);
        sbOpflowMotionX = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_opflow_motionX);
        sbOpflowMotionY = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_opflow_motionY);
        tvOpflowBytes = (TextView) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_opflow_text);


        sbBarotimeMs = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_baro_timeMs);
        sbBaropressurePa = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_baro_pressurePa);
        sbBaroTemp = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_baro_temp);
        tvBaroBytes = (TextView) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_baro_text);


        sbAirTimeMs = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_air_timeMs);
        sbAirPressurePa = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_air_pressurePa);
        sbAirTemp = (MengSeekBar) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_air_temp);
        tvAirBytes = (TextView) view.findViewById(R.id.function_electronic_usbserial2_msp_sensor_air_text);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!connected && (usbPermission == UsbPermission.Unknown || usbPermission == UsbPermission.Granted)) {
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    MspSensorTestFragment.this.connect();
                    sendTask = ThreadPool.executeAtFixedRate(new Runnable() {
                        @Override
                        public void run() {

                            SensorAirspeed airspeed = new SensorAirspeed();
                            airspeed.setInstance_uint8(0);
                            airspeed.setTimeMs_uint32(sbAirTimeMs.getProgress());
                            airspeed.setDiffPressurePa_float(sbAirPressurePa.getProgress());
                            airspeed.setTemp_int16(sbAirTemp.getProgress());
                            final byte[] airBytes = MspV2DataPack.encode(MspV2Cmd.MSP2_SENSOR_BAROMETER, airspeed.encode());
                            sendbytes(airBytes);

                            SensorBaro baro = new SensorBaro();
                            baro.setTimeMs_uint32(sbBarotimeMs.getProgress());
                            baro.setPressurePa_float(sbBaropressurePa.getProgress());
                            baro.setTemp_int16(sbBaroTemp.getProgress());
                            final byte[] baroBytes = MspV2DataPack.encode(MspV2Cmd.MSP2_SENSOR_BAROMETER, baro.encode());
                            sendbytes(baroBytes);

                            SensorOpflow opflow = new SensorOpflow();
                            opflow.setQuality_uint8(sbOpflowQuality.getProgress());
                            opflow.setMotionX_int32(sbOpflowMotionX.getProgress());
                            opflow.setMotionY_int32(sbOpflowMotionY.getProgress());
                            final byte[] opflowBytes = MspV2DataPack.encode(MspV2Cmd.MSP2_SENSOR_OPTIC_FLOW, opflow.encode());
                            sendbytes(opflowBytes);


                            SensorRangefinder rangefinder = new SensorRangefinder();
                            rangefinder.setQuality_uint8(sbRangeFinderQuality.getProgress());
                            rangefinder.setDistanceMm_int32(sbRangeFinderDistanceMm.getProgress());
                            final byte[] rangeFinderBytes = MspV2DataPack.encode(MspV2Cmd.MSP2_SENSOR_RANGEFINDER, rangefinder.encode());
                            sendbytes(rangeFinderBytes);


                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvAirBytes.setText(HexDump.toHexString(airBytes));
                                    tvBaroBytes.setText(HexDump.toHexString(baroBytes));
                                    tvOpflowBytes.setText(HexDump.toHexString(opflowBytes));
                                    tvRangeFinderBytes.setText(HexDump.toHexString(rangeFinderBytes));
                                }
                            });
                        }
                    }, 50, TimeUnit.MILLISECONDS);
                }
            });
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
//                MspSensorTestFragment.this.receive(data);
            }
        });
    }

    @Override
    public void onRunError(final Exception e) {
        mainLooper.post(new Runnable() {
            @Override
            public void run() {
//                MspSensorTestFragment.this.status("connection lost: " + e.getMessage());
                MspSensorTestFragment.this.disconnect();
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
        UsbSerialDriver driver = UsbSerialProber.getDefaultProber().probeDevice(device);
        if (driver == null) {
            driver = CustomProber.getCustomProber().probeDevice(device);
        }
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
            sendTask.cancel(true);
            usbSerialPort.close();
        } catch (IOException ignored) {
        }
        usbSerialPort = null;
    }

    private void sendbytes(byte[] data) {
        if (!connected) {
            Toast.makeText(getActivity(), "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            usbSerialPort.write(data, WRITE_WAIT_MILLIS);
        } catch (Exception e) {
            onRunError(e);
        }
    }
}
