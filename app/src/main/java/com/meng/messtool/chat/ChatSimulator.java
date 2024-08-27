package com.meng.messtool.chat;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.meng.messtool.*;
import hq.king.*;
import hq.king.adapter.*;
import hq.king.view.*;
import java.util.*;
import com.meng.tools.*;

public class ChatSimulator extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-26 00:31:38
     */
    public static final String TAG = "ChatSimulator";
    private Button chat_title_back,chat_bottom_send;
    private TextView chat_title_nick;
    private EditText chat_bottom_edit;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;
    private LinkedList<ChatMsgEntity> mDataArrays = new LinkedList<ChatMsgEntity>();
    private MessageDB messageDB;
    public CharaManager charaManager;

    @Override
    public String getTitle() {
        return "聊天模拟器";
    }

    @Override
    public String getVersionName() {
        return "V1.0.0";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        final String[] items = FileTool.getAppFile(FunctionSavePath.chat_script, "").list();
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
            .setTitle("选择脚本")
            .setItems(items, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dia, int which) {
                    init(items[which]);
                }
            })
            .setPositiveButton("确定", null)
            .setNegativeButton("取消", null)
            .create();
        dialog.show();

    } 

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.chat_content_list);
        chat_title_nick = (TextView) view.findViewById(R.id.chat_title_nick);
        chat_title_back = (Button) view.findViewById(R.id.chat_title_back);
        chat_bottom_send = (Button) view.findViewById(R.id.chat_bottom_send);
        chat_bottom_edit = (EditText) view.findViewById(R.id.chat_bottom_edit);
    }

    private void init(String scriptName)  {
        messageDB = new MessageDB(scriptName);
        charaManager = new CharaManager();
        mAdapter = new ChatMsgViewAdapter(getActivity(), charaManager, mListView, mDataArrays);
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
                    ChatMsgEntity entity = new ChatMsgEntity();
                    entity.date = getDate();
                    entity.rec = false;
                    entity.name = "快乐的清洁工";
                    entity.message = contString;
                    onMessage(entity);
                    //            initData();
                }
            });
        chat_title_nick.setText("我们是正经群");

        initData();
//        new Thread(new Runnable(){
//
//                @Override
//                public void run() {
//                    onMessage(new ChatMsgEntity("数据删除","","爱莉希雅死了"));
//                    onMessage(new ChatMsgEntity("数据删除","","爱莉希雅死了"));          
//                    onMessage(new ChatMsgEntity("数据删除","","爱莉希雅死了"));
//                    onMessage(new ChatMsgEntity("数据删除","","爱莉希雅死了"));
//                    onMessage(new ChatMsgEntity("数据删除","","爱莉希雅死了"));          
//                    
//                    onMessage(new ChatMsgEntity("月下","","月下不仅活了还大了"));
//                    onMessage(new ChatMsgEntity("月下","","月下不仅活了还大了"));
//                    onMessage(new ChatMsgEntity("月下","","月下不仅活了还大了"));
//                    onMessage(new ChatMsgEntity("月下","","月下不仅活了还大了"));
//                    
//                    
//                    
//                }
//            }).start();
    }
    /**
     * 加载消息历史，从数据库中读出
     */
    public void initData() {
        final List<ChatMsgEntity> list = messageDB.getMsg("观星".hashCode());
        final Random random = new Random();
        if (list.size() > 0) {
            new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        for (ChatMsgEntity entity : list) {
                            try {
                                Thread.sleep(entity.delay == -1 ?random.nextInt(3000): entity.delay);
                            } catch (InterruptedException e) {}
                            onMessage(entity);
                        }

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {}
                        getActivity(). runOnUiThread(new Runnable(){

                                @Override
                                public void run() {
                                    new AlertDialog.Builder(getActivity())
                                        .setTitle("提示")
                                        .setMessage("该群因传播色情信息已被封禁")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dia, int which) {
                                                getActivity(). finish();
                                            }
                                        })             
                                        .create().show();
                                }
                            });
                    }
                }).start();

            //     Collections.reverse(mDataArrays);
        }

    }


    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));


        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
        return sbBuffer.toString();
    }

    private void onMessage(final ChatMsgEntity msg) {
        getActivity().      runOnUiThread(new Runnable(){

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
