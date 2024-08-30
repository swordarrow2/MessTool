package com.meng.messtool.modules.chat.editor;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.modules.chat.simulator.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.util.*;

public class MainAdapter extends BaseAdapter {

    /*
     *@author 清梦
     *@date 2024-08-27 00:12:42
     */
    public static final String TAG = "MainAdapter";

    private LinkedList<ChatScriptAction> coll;
    private Context ctx;
    private LayoutInflater mInflater;

    private int pointer = 0;

    MainAdapter(Context context, LinkedList<ChatScriptAction> coll) {
        ctx = context;
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public void setPointer(int i) {
        pointer = i;
    }

    public int getCount() {
        return coll.size();
    }

    public ChatScriptAction getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ChatScriptAction action = coll.get(position);

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);
            viewHolder = new ViewHolder();
            viewHolder.tv1 = (TextView) convertView.findViewById(android.R.id.text1);
            viewHolder.tv2 = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == pointer) {
            convertView.setBackgroundColor(Color.argb(0xFF, 0x7F, 0xCA, 0x00));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        viewHolder.tv1.setText(action.action.toString());
        switch (action.action) {
            case TYPE_SET_GROUP_NAME:
                viewHolder.tv2.setText(action.content);
                break;
            case TYPE_STRING_MESSAGE:
                viewHolder.tv2.setText(String.format("%s:%s", action.from, action.content));
                break;
            case TYPE_IMAGE_MESSAGE:
                SpannableString msp = new SpannableString(String.format("%s:%s", action.from, action.content));
                msp.setSpan(new ImageSpan(ctx, BitmapFactory.decodeFile(FileTool.getAppFile(FileSavePath.CHAT_IMAGE, action.content).getAbsolutePath())), action.from.length() + 1, msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.tv2.setText(msp);
                break;
            case TYPE_MESSAGE_RECALL:
                //TODO
                break;
            case TYPE_CHAT_TIP:
                viewHolder.tv2.setText(String.format("tip:%s", action.content));
                break;
            case TYPE_DIALOG:
                viewHolder.tv2.setText(String.format("dialog-title:%s,content:%s", action.from, action.content));
                break;
            default:

                break;
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv1;
        TextView tv2;
    }
}
