package com.meng.messtool.modules.fpvtool.gui.adapter;

import android.content.*;
import android.support.annotation.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;

import java.util.*;

/*
 *package  com.examples.usbserial
 *@author  清梦
 *@date    2024/8/15 15:46
 */
public class SerialReceiveAdapter extends BaseAdapter {
    private List<Pair<String, String>> listItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public SerialReceiveAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void add(Pair<String, String> pair) {
        listItems.add(pair);
    }

    public void add(String c1, String c2) {
        listItems.add(new Pair<>(c1, c2));
    }

    public void add(String c1) {
        listItems.add(new Pair<>(c1, ""));
    }

    public void clear() {
        listItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Pair<String, String> getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.get(position).hashCode();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.two_line_list_item, null);
            holder = new ViewHolder();
            holder.tvFirt = (TextView) convertView.findViewById(android.R.id.text1);
            holder.tvSeconed = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Pair<String, String> item = listItems.get(position);
        holder.tvFirt.setText(item.first);
        holder.tvSeconed.setText(item.second);
        if (item.first.startsWith("receive")) {
            holder.tvFirt.setTextColor(context.getResources().getColor(R.color.colorPrimary, context.getTheme()));
            holder.tvSeconed.setTextColor(context.getResources().getColor(R.color.colorPrimary, context.getTheme()));
        } else if (item.first.startsWith("send")) {
            holder.tvFirt.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme()));
            holder.tvSeconed.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark, context.getTheme()));
        } else {
            holder.tvFirt.setTextColor(context.getResources().getColor(R.color.result_view, context.getTheme()));
            holder.tvSeconed.setTextColor(context.getResources().getColor(R.color.result_view, context.getTheme()));
        }
        return convertView;
    }

    private final class ViewHolder {
        private TextView tvFirt;
        private TextView tvSeconed;
    }
}
