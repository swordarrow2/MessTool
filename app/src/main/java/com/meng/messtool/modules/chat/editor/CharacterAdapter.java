package com.meng.messtool.modules.chat.editor;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.modules.chat.simulator.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.util.*;

public class CharacterAdapter extends BaseAdapter {

    /*
     *@author 清梦
     *@date 2024-08-30 00:03:24
     */
    public static final String TAG = "CharacterAdapter";

    private LinkedList<ChatCharacter> coll;
    private Context context;
    private LayoutInflater mInflater;
    private HashMap<String, Bitmap> headCache = new HashMap<>();

    CharacterAdapter(Context context, LinkedList<ChatCharacter> coll) {
        this.context = context;
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return coll.size();
    }

    public ChatCharacter getItem(int position) {
        if (coll == null || position >= coll.size()) {
            return null;
        }
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ChatCharacter character = coll.get(position);

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.provider_select_file_item, null);
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.nick = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nick.setText(character.name);

        Bitmap decodeFile = headCache.get(character.head);
        if (decodeFile == null) {
            headCache.put(character.head, decodeFile = BitmapFactory.decodeFile(FileTool.getAppFile(FileSavePath.CHAT_CHARACTER, character.head).getAbsolutePath()));
        }
        viewHolder.avatar.setImageBitmap(decodeFile);

        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView nick;
    }
}
