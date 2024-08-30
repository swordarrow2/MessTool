package com.meng.messtool.modules.chat.simulator;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.customview.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.util.*;

public class ChatScriptAdapter extends BaseAdapter {

    /*
     *@author 清梦
     *@date 2024-08-26 17:39:05
     */
    public static final String TAG = "ChatMsgAdapter";

    private LinkedList<ChatScriptAction> coll;
    private LayoutInflater mInflater;
    private HashMap<String, Bitmap> headCache = new HashMap<>();
    private Animation animation;
    private CharacterManager charaManager;

    private ChatScriptEngine engine;

    public ChatScriptAdapter(Context context, ChatSimulator simulator, ChatRoomInfo room, LinkedList<ChatScriptAction> coll) {
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
        charaManager = new CharacterManager();
        engine = new ChatScriptEngine(context, simulator, room);
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
            if (action.isSelf) {
                convertView = mInflater.inflate(R.layout.chat_item_msg_right, null);
            } else {
                convertView = mInflater.inflate(R.layout.chat_item_msg_left, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.chatting_item_msg_leftLinearLayout_time);
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.img = (CircleImageView) convertView.findViewById(R.id.iv_userhead);
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.chatting_item_msg_leftRelativeLayout);

            if (coll.size() - 1 == position) {
                convertView.startAnimation(animation);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        engine.processAction(viewHolder, action);

        Bitmap decodeFile = headCache.get(action.from);
        if (decodeFile == null) {
            headCache.put(action.content, decodeFile = BitmapFactory.decodeFile(FileTool.getAppFile(FileSavePath.CHAT_CHARACTER, charaManager.get(action.from).head).getAbsolutePath()));
        }
        viewHolder.img.setImageBitmap(decodeFile);

        return convertView;

    }

    static class ViewHolder {
        public LinearLayout linearLayout;
        public RelativeLayout relativeLayout;
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public ImageView img;
    }
}
