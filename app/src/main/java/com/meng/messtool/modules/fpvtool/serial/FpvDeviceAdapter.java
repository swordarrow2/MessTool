package com.meng.messtool.modules.fpvtool.serial;

import android.content.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;

import java.util.*;

/*
 *package  com.examples.usbserial
 *@author  清梦
 *@date    2024/8/15 15:46
 */
public class FpvDeviceAdapter extends ArrayAdapter<ListItem> {
    private List<ListItem> listItems;

    public FpvDeviceAdapter(@NonNull Context context, @NonNull List<ListItem> listItems) {
        super(context, 0, listItems);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fpv_device_list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ListItem item = listItems.get(position);

        if (item.driver == null) {
            holder.text1.setText("<no driver>");
        } else {
            String manufacturerName = item.device.getManufacturerName();
            holder.text1.setText(
                    String.format(Locale.CHINA, "%s : %s(%s port:%d)",
                            manufacturerName,
                            item.device.getProductName(),
                            item.driver.getClass().getSimpleName().replace("SerialDriver", ""),
                            item.port));
            holder.text2.setText(String.format(Locale.US, "Vendor %04X, Product %04X, version %s",
                    item.device.getVendorId(),
                    item.device.getProductId(), item.device.getVersion()));
            if (manufacturerName != null) {
                manufacturerName = manufacturerName.toLowerCase();
                if (manufacturerName.startsWith("inav")) {
                    holder.imageView.setImageResource(R.drawable.inav_icon_128);
                } else if (manufacturerName.startsWith("betaflight")) {
                    holder.imageView.setImageResource(R.drawable.bf_icon_128);
                }
            }
        }
        return convertView;
    }

    private final class ViewHolder {
        private ImageView imageView;
        private TextView text1;
        private TextView text2;
    }
}
