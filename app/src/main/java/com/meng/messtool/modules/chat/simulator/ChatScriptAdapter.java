package com.meng.messtool.modules.chat.simulator;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.meng.messtool.*;
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
    private Context ctx;
    private LayoutInflater mInflater;
    private HashMap<String,Bitmap> headCache = new HashMap<>();
    private Animation animation;
    //  private ListView listview;
    private CharacterManager charaManager;

    private int mFirstTop, mFirstPosition;  
    private boolean isScrollDown;  
    private ChatRoomInfo room;

    public ChatScriptAdapter(Context context, CharacterManager charaManager, ChatRoomInfo room, LinkedList<ChatScriptAction> coll) {
        ctx = context;
        this.coll = coll;
        //  listview = lv;
        mInflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
        //  listview.setOnScrollListener(mOnScrollListener);  
        this.charaManager = charaManager;
        this.room = room;
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


    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ChatScriptAction action = coll.get(position);

        ViewHolder viewHolder = null;   
        if (convertView == null) {
            if (action.isSelf) {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_right, null);
            } else {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_left, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.ll = (LinearLayout) convertView.findViewById(R.id.chatting_item_msg_leftLinearLayout_time);
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.img = (CircleImageView) convertView.findViewById(R.id.iv_userhead);
            viewHolder.rl = (RelativeLayout)convertView.findViewById(R.id.chatting_item_msg_leftRelativeLayout);

//            for (int i=0;i<listview.getChildCount();i++){  
//                View view = listview.getChildAt(i);  
//                view.clearAnimation();  
//            }  
//            //然后给当前item添加上动画  
//            if (isScrollDown) {  
//                convertView.startAnimation(animation);  
//            }    
            if (coll.size() - 1 == position) {
                convertView.startAnimation(animation);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ll.setVisibility(View.GONE);                        
        viewHolder.rl.setVisibility(View.GONE);                        

        switch (action.action) {
            case TYPE_SET_GROUP_NAME:
                room.getTvTitle().setText(action.content);
                break;
            case TYPE_STRING_MESSAGE:
                viewHolder.rl.setVisibility(View.VISIBLE);                                        
                viewHolder.tvUserName.setText(action.from);
                viewHolder.tvContent.setText(action.content);
                break;
            case TYPE_IMAGE_MESSAGE:
                viewHolder.rl.setVisibility(View.VISIBLE);                                        
                viewHolder.tvUserName.setText(action.from);
                SpannableString msp = new SpannableString(action.content);
                msp.setSpan(new ImageSpan(ctx, BitmapFactory.decodeFile(FileTool.getAppFile(FunctionSavePath.chat_image, action.content).getAbsolutePath())), 0, action.content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.tvContent.setText(msp);
                break;
            case TYPE_MESSAGE_RECALL:
                //TODO
                break;
            case TYPE_CHAT_TIP:
                viewHolder.ll.setVisibility(View.VISIBLE);                        
                viewHolder.tvSendTime.setText(action.content);                
                break;
            case TYPE_DIALOG:
                new AlertDialog.Builder(ctx)
                    .setTitle(action.from)
                    .setMessage(action.content)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dia, int which) {

                        }
                    }).create().show();
                break;
            default:

                break;
        }


        Bitmap decodeFile = headCache.get(action.from);
        if (decodeFile == null) {  
            headCache.put(action.content, decodeFile = BitmapFactory.decodeFile(FileTool.getAppFile(FunctionSavePath.chat_character, charaManager.get(action.from).head).getAbsolutePath()));
        }
        viewHolder.img.setImageBitmap(decodeFile);

        return convertView;

    }


    AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {  
        @Override  
        public void onScrollStateChanged(AbsListView view, int scrollState) {  

        }  

        @Override  
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {  
            View firstChild = view.getChildAt(0);  
            if (firstChild == null) return;  
            int top = firstChild.getTop();  
            /** 
             * firstVisibleItem > mFirstPosition表示向下滑动一整个Item 
             * mFirstTop > top表示在当前这个item中滑动 
             */  
            isScrollDown = firstVisibleItem > mFirstPosition || mFirstTop > top;  
            mFirstTop = top;  
            mFirstPosition = firstVisibleItem;  
        }  
    };  

    static class ViewHolder { 
        public LinearLayout ll;
        public RelativeLayout rl;
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public CircleImageView img;
    }
}
