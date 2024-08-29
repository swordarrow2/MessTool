package com.meng.messtool.modules.chat.simulator;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.text.*;
import android.text.style.*;
import android.view.*;
import android.widget.*;

import com.meng.tools.*;
import com.meng.tools.app.*;

public class ChatScriptEngine {

    /*
     *@author 清梦
     *@date 2024-08-26 12:43:34
     */
    public static final String TAG = "ChatEngine";
    private ChatRoomInfo room;
    private Context context;

    public ChatScriptEngine(Context context, ChatRoomInfo room) {
        this.room = room;
        this.context = context;
    }

    public void processAction(ChatScriptAdapter.ViewHolder viewHolder, ChatScriptAction action) {

        viewHolder.linearLayout.setVisibility(View.GONE);
        viewHolder.relativeLayout.setVisibility(View.GONE);

        switch (action.action) {
            case TYPE_SET_GROUP_NAME:
                ((TextView) room.getTitleView()).setText(action.content);
                break;
            case TYPE_STRING_MESSAGE:
                viewHolder.relativeLayout.setVisibility(View.VISIBLE);
                viewHolder.tvUserName.setText(action.from);
                viewHolder.tvContent.setText(action.content);
                break;
            case TYPE_IMAGE_MESSAGE:
                viewHolder.relativeLayout.setVisibility(View.VISIBLE);
                viewHolder.tvUserName.setText(action.from);
                SpannableString msp = new SpannableString(action.content);
                msp.setSpan(new ImageSpan(context, BitmapFactory.decodeFile(FileTool.getAppFile(FileSavePath.chat_image, action.content).getAbsolutePath())), 0, action.content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.tvContent.setText(msp);
                break;
            case TYPE_MESSAGE_RECALL:
                //TODO
                break;
            case TYPE_CHAT_TIP:
                viewHolder.linearLayout.setVisibility(View.VISIBLE);
                viewHolder.tvSendTime.setText(action.content);
                break;
            case TYPE_WAIT_MESSAGE:
                //TODO
                break;
            case TYPE_DIALOG:
                new AlertDialog.Builder(context)
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

    }
}
