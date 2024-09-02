package com.meng.messtool.modules.fpvtool.serial;

import android.app.*;
import android.content.*;
import android.hardware.usb.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.driver.*;
import com.meng.messtool.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;

import java.util.*;

public class FpvDevicesFragment extends BaseFragment {

    private final ArrayList<ListItem> listItems = new ArrayList<>();
    private ArrayAdapter<ListItem> listAdapter;
    private int baudRate = 115200;
    private PendingIntent mPrtPermissionIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fpv_device_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.fpv_device_list_list);
        listAdapter = new FpvDeviceAdapter(getActivity(), listItems);
        listView.setAdapter(listAdapter);
        listView.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.fpv_device_list_header, null, false), null, false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem item = listItems.get(position - 1);
                if (item.driver == null) {
                    Toast.makeText(getActivity(), "no driver", Toast.LENGTH_SHORT).show();
                } else {
                    final Bundle args = new Bundle();
                    args.putInt("device", item.device.getDeviceId());
                    args.putInt("port", item.port);
                    args.putInt("baud", baudRate);

                    FpvConfigGuiFragment terminalFragment = MFragmentManager.getInstance().getFragment(FpvConfigGuiFragment.class);
                    terminalFragment.setArguments(args);
                    MFragmentManager.getInstance().showFragment(FpvConfigGuiFragment.class);

                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        intentFilter.addAction(Constant.USB_PERMISSION);
        getActivity().registerReceiver(usbReceiver, intentFilter);//注册receiver
        mPrtPermissionIntent = PendingIntent.getBroadcast(getActivity(), 0, new Intent(Constant.USB_PERMISSION), 0);
        refresh();
    }

    private BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action) || UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) { // USB拔插动作
                System.out.println("USB 插入...");
                refresh();
                return;
            }
            if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) { // USB拔插动作
                System.out.println("USB 拔出...");
                refresh();
                return;
            }
            synchronized (this) {
                if (!intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    showToast("usb permission granted fail.");
                    return;
                }
                if (intent.getParcelableExtra(UsbManager.EXTRA_DEVICE) == null) {
                    showToast("usb device suddenly disappera.");
                    return;
                }
                showToast("after request dev permission and granted pemission , connect printer by usb.");
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fpv_devices, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh:
                refresh();
                return true;
            case R.id.baud_rate: {
                final String[] values = getResources().getStringArray(R.array.baud_rates);
                int pos = Arrays.asList(values).indexOf(String.valueOf(baudRate));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Baud rate");
                builder.setSingleChoiceItems(values, pos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baudRate = Integer.parseInt(values[which]);
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * 请求权限：一般来说有弹框
     */
    private void RequestNormalPermission(UsbManager usbManager, UsbDevice device) {
        if (!usbManager.hasPermission(device)) {
            usbManager.requestPermission(device, mPrtPermissionIntent);// will recall mReceiver
        }
    }

    private void refresh() {
        UsbManager usbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        if (usbManager == null) {
            return;
        }
        UsbSerialProber usbDefaultProber = UsbSerialProber.getDefaultProber();
        listItems.clear();
        for (UsbDevice device : usbManager.getDeviceList().values()) {
            RequestNormalPermission(usbManager, device);
            UsbSerialDriver driver = usbDefaultProber.probeDevice(device);
            if (driver == null) {
                listItems.add(new ListItem(device, 0, null));
                continue;
            }
            for (int port = 0; port < driver.getPorts().size(); port++) {
                listItems.add(new ListItem(device, port, driver));
            }
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public String getTitle() {
        return "FPV串口设备列表";
    }

    @Override
    public CharSequence getDescribe() {
        return "FPV串口调试器";
    }
}
