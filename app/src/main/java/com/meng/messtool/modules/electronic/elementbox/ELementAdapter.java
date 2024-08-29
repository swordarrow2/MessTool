package com.meng.messtool.modules.electronic.elementbox;

import android.app.*;
import android.graphics.*;
import android.media.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;

import java.util.*;

class ELementAdapter extends BaseAdapter {

    private List<Element> allElelement;
    private WeakHashMap<String, Bitmap> cache = new WeakHashMap<>();
    private Activity activity;

    ELementAdapter(Activity activity) {
        this.activity = activity;
        this.allElelement = ElectronicDatabase.getInstance().getAllELement();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        List<Element> newMe = ElectronicDatabase.getInstance().getAllELement();
        allElelement.clear();
        allElelement.addAll(newMe);
    }

    public void cleanThumb(Element element) {
        cache.remove(element._name);
    }

    public int getCount() {
        return allElelement.size();
    }

    public Element getItem(int position) {
        return allElelement.get(position);
    }

    public long getItemId(int position) {
        return allElelement.get(position).hashCode();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ELementAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.electronic_element_list_item, null);
            holder = new ELementAdapter.ViewHolder();
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_medicine_thumbnail);
            holder.name = (TextView) convertView.findViewById(R.id.list_item_medicine_name);
            holder.count = (TextView) convertView.findViewById(R.id.list_item_medicine_in_slot);
            holder.describe = (TextView) convertView.findViewById(R.id.list_item_medicine_describe);
            convertView.setTag(holder);
        } else {
            holder = (ELementAdapter.ViewHolder) convertView.getTag();
        }

        Element element = allElelement.get(position);

        holder.name.setText(element._name);
        holder.count.setText(String.valueOf(element._rest));
        holder.describe.setText(element._describe);
        Bitmap bitmap = null;
        if (element._picture != null && element._picture.length > 0) {
            bitmap = cache.get(element._name);
            if (bitmap == null) {
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(element._picture, 0, element._picture.length);
                int nwidth = 256;    //h/w=nh/nw   h*nw/w=nh
                int nheight = bitmap1.getHeight() * nwidth / bitmap1.getWidth();
                bitmap = ThumbnailUtils.extractThumbnail(bitmap1, nwidth, nheight);
                cache.put(element._name, bitmap);
            }
        }
        holder.thumbnail.setVisibility(bitmap == null ? View.GONE : View.VISIBLE);
        holder.thumbnail.setImageBitmap(bitmap);
        return convertView;
    }

    private class ViewHolder {
        private ImageView thumbnail;
        private TextView name;
        private TextView count;
        private TextView describe;
    }

}