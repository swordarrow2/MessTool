package com.meng.messtool.modules.chat.simulator;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.util.*;

public class ChatSimulator extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-26 00:31:38
     */
    public static final String TAG = "ChatSimulator";
    private Button chat_title_back, chat_bottom_send;
    private TextView chat_title_nick;
    private EditText chat_bottom_edit;
    private ListView mListView;
    private ChatScriptAdapter mAdapter;
    private LinkedList<ChatScriptAction> mDataArrays = new LinkedList<ChatScriptAction>();
    private MessageManager messageDB;
    private ChatScriptEngine engine;

    @Override
    public String getTitle() {
        return "聊天模拟器";
    }

    @Override
    public String getVersionName() {
        return "V1.3.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        String[] l = FileTool.getAppFile(FileSavePath.CHAT_SCRIPT, "").list();
        if (l.length == 0) {
            MessageManager.createExample();
        }
        final String[] items = FileTool.getAppFile(FileSavePath.CHAT_SCRIPT, "").list();
        new AlertDialog.Builder(getActivity())
                .setTitle("选择脚本")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dia, int which) {
                        init(items[which]);
                    }
                }).create().show();
    }

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.chat_content_list);
        chat_title_nick = (TextView) view.findViewById(R.id.chat_title_nick);
        chat_title_back = (Button) view.findViewById(R.id.chat_title_back);
        chat_bottom_send = (Button) view.findViewById(R.id.chat_bottom_send);
        chat_bottom_edit = (EditText) view.findViewById(R.id.chat_bottom_edit);
    }

    private void init(String scriptName) {
        messageDB = new MessageManager(scriptName);
        ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
        chatRoomInfo.setTitleView(chat_title_nick);
        engine = new ChatScriptEngine(getActivity(), this, chatRoomInfo);
        mAdapter = new ChatScriptAdapter(getActivity(), this, chatRoomInfo, mDataArrays);
        mListView.setAdapter(mAdapter);

        chat_bottom_send.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                String contString = chat_bottom_edit.getText().toString();
                if (contString.length() > 0) {
                    chat_bottom_edit.setText("");
                } else {
                    showToast("内容不能为空");
                    return;
                }
                ChatScriptAction entity = new ChatScriptAction(
                        ActionType.TYPE_STRING_MESSAGE,
                        contString,
                        "快乐的清洁工",
                        0);
                onMessage(entity);
            }
        });
        initData();
    }

    public void initData() {
        final List<ChatScriptAction> list = messageDB.getScriptActions();
        engine.startAction(list);

    }


    public void onMessage(final ChatScriptAction msg) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mDataArrays.add(msg);
                mAdapter.notifyDataSetChanged();
                //    mListView.setSelection(mListView.getCount() - 1);
                mListView.smoothScrollToPosition(mListView.getCount() - 1);
            }
        });
    }

}
