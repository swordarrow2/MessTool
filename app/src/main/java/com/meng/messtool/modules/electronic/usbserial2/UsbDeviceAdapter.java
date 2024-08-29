package com.meng.messtool.modules.electronic.usbserial2;

import android.content.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.hoho.android.usbserial.driver.*;
import com.meng.messtool.*;

import java.util.*;

/*
 *package  com.examples.usbserial
 *@author  清梦
 *@date    2024/8/15 15:46
 */
public class UsbDeviceAdapter extends ArrayAdapter<ListItem> {
    private List<ListItem> listItems;

    public UsbDeviceAdapter(@NonNull Context context, @NonNull List<ListItem> listItems) {
        super(context, 0, listItems);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        ListItem item = listItems.get(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.electronic_usbserial2_device_list_item, parent, false);
        }
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        UsbSerialPort mUsbSerialPort = item.driver.getPorts().get(0);

        if (item.driver == null) {
            text1.setText("<no driver>");
        } else {
            text1.setText(
                    String.format(Locale.CHINA, "%s : %s(%s port:%d)",
                            item.device.getManufacturerName(),
                            item.device.getProductName(),
                            item.driver.getClass().getSimpleName().replace("SerialDriver", ""),
                            item.port));
        }
        text2.setText(String.format(Locale.US, "Vendor %04X, Product %04X, version %s",
                item.device.getVendorId(),
                item.device.getProductId(), item.device.getVersion()));
        return view;
    }
}
