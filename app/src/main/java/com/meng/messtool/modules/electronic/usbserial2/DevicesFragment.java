package com.meng.messtool.modules.electronic.usbserial2;

import android.app.*;
import android.content.*;
import android.hardware.usb.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.driver.*;
import com.meng.messtool.*;

import java.util.*;

public class DevicesFragment extends BaseFragment {

    private final ArrayList<ListItem> listItems = new ArrayList<>();
    private ArrayAdapter<ListItem> listAdapter;
    private int baudRate = 19200;
    private boolean withIoManager = true;
    private ListView listView;
    private PendingIntent mPrtPermissionIntent;
    private UsbDeviceConnection usbDeviceConnection;
    private UsbManager usbManager;

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public String getTitle() {
        return "USB串口设备列表";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.list);
        setHasOptionsMenu(true);
        listAdapter = new UsbDeviceAdapter(getActivity(), listItems);
        listView.setAdapter(listAdapter);
        listView.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.function_electronic_usbserial2_device_list_header, null, false), null, false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem item = listItems.get(position - 1);
                if (item.driver == null) {
                    Toast.makeText(getActivity(), "no driver", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle args = new Bundle();
                    args.putInt("device", item.device.getDeviceId());
                    args.putInt("port", item.port);
                    args.putInt("baud", baudRate);
                    args.putBoolean("withIoManager", withIoManager);
                    TerminalFragment fragment = new TerminalFragment();
                    fragment.setArguments(args);
                    MFragmentManager.getInstance().registFragment(fragment);
                    MFragmentManager.getInstance().showFragment(TerminalFragment.class);

//                    Fragment fragment = new TerminalFragment();
//                    fragment.setArguments(args);
//                    getFragmentManager().beginTransaction().add(R.id.fragment, fragment, "terminal").addToBackStack(null).commit();
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
            if (!Constant.USB_PERMISSION.equals(action)) {

//                return;
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
//                            getUsbInfo(mUsbSerialPort.getDriver().getDevice());
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_devices, menu);
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
            case R.id.read_mode: {
                final String[] values = getResources().getStringArray(R.array.read_modes);
                int pos = withIoManager ? 0 : 1; // read_modes[0]=event/io-manager, read_modes[1]=direct

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Read mode");
                builder.setSingleChoiceItems(values, pos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        withIoManager = (which == 0);
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
            //   showToast("printer dev has no permission,try request it.");
            usbManager.requestPermission(device, mPrtPermissionIntent);// will recall mReceiver
        } else {
            //  showToast("USB权限注册成功。");
        }
    }

    private void refresh() {
        usbManager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);
        UsbSerialProber usbDefaultProber = UsbSerialProber.getDefaultProber();
        UsbSerialProber usbCustomProber = CustomProber.getCustomProber();
        listItems.clear();
        for (UsbDevice device : usbManager.getDeviceList().values()) {
            RequestNormalPermission(usbManager, device);
            UsbSerialDriver driver = usbDefaultProber.probeDevice(device);
            if (driver == null) {
                driver = usbCustomProber.probeDevice(device);
            }
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


}
