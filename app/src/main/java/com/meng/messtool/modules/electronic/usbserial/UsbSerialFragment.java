package com.meng.messtool.modules.electronic.usbserial;

import android.content.*;
import android.hardware.usb.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.hoho.android.usbserial.driver.*;
import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import java.io.*;
import java.util.*;

public class UsbSerialFragment extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-06-12 16:47:46
     */
    public static final String TAG = "UsbSerialFragment";

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> devices = new ArrayList<>();

    @Override
    public String getTitle() {
        return "USB串口1代";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.function_electronic_usbserial2_device_list, null);
        listView = (ListView) inflate.findViewById(R.id.function_electronic_usbserial2_device_list_main_list);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, devices);
        listView.setAdapter(adapter);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process() throws IOException {
        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }
        devices.add(availableDrivers.toString());

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // add UsbManager.requestPermission(driver.getDevice(), ..) handling here
            showToast("UsbDeviceConnection is null");
            return;
        }

        UsbSerialPort port = driver.getPorts().get(0); // Most devices have just one port (port 0)
        port.open(connection);
        port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

    /*    
        usbIoManager = new SerialInputOutputManager(usbSerialPort, this);
        usbIoManager.start();
        ...
        port.write("hello".getBytes(), WRITE_WAIT_MILLIS);

    @Override
    public void onNewData(byte[] data) {
        runOnUiThread(() -> { textView.append(new String(data)); });
    }
        */
    }

}
