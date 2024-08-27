package hq.king.adapter;

import android.content.*;
import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.chat.*;
import hq.king.*;
import hq.king.view.*;
import java.util.*;
import com.meng.tools.*;

public class ChatMsgViewAdapter extends BaseAdapter {

    private LinkedList<ChatMsgEntity> coll;
    private Context ctx;
    private LayoutInflater mInflater;
    private HashMap<String,Bitmap> headCache = new HashMap<>();
    private Animation animation;
    private ListView listview;
    private CharaManager charaManager;

    private int mFirstTop, mFirstPosition;  
    private boolean isScrollDown;  


    public ChatMsgViewAdapter(Context context, CharaManager charaManager, ListView lv, LinkedList<ChatMsgEntity> coll) {
        ctx = context;
        this.coll = coll;
        listview = lv;
        mInflater = LayoutInflater.from(context);
        animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
        //  listview.setOnScrollListener(mOnScrollListener);  
        this.charaManager = charaManager;
    }

    public int getCount() {
        return coll.size();
    }

    public ChatMsgEntity getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
	 	ChatMsgEntity entity = coll.get(position);
	 	return entity.rec ?1: 0;
	}

	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

    public View getView(int position, View convertView, ViewGroup parent) {

    	ChatMsgEntity entity = coll.get(position);

    	ViewHolder viewHolder = null;	
	    if (convertView == null) {
            if (entity.rec) {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_left, null);
            } else {
                convertView = mInflater.inflate(R.layout.chatting_item_msg_right, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.ll = (LinearLayout) convertView.findViewById(R.id.chatting_item_msg_leftLinearLayout_time);
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.img = (CircleImageView) convertView.findViewById(R.id.iv_userhead);


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

        if (entity.date == null || entity.date.equals("")) {
            viewHolder.ll.setVisibility(View.GONE);  
        } else {
            viewHolder.tvSendTime.setText(entity.date);
        }
	    viewHolder.tvUserName.setText(entity.name);
        if (entity.span) {
            SpannableString msp = new SpannableString(" ");
            msp.setSpan(new ImageSpan(ctx, BitmapFactory.decodeFile(FileTool.getAppFile(FunctionSavePath.chat_image,entity.spanPic).getAbsolutePath())), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.tvContent.setText(msp);
        } else {
            viewHolder.tvContent.setText(entity.message);
        }
        Bitmap decodeFile = headCache.get(entity.name);
        if (decodeFile == null) {  
            headCache.put(entity.name, decodeFile = BitmapFactory.decodeFile(FileTool.getAppFile(FunctionSavePath.chat_character, charaManager.get(entity.name).head).getAbsolutePath()));
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
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public CircleImageView img;
    }
}
