package com.meng.messtool.modules.chat.editor;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.meng.messtool.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.messtool.modules.chat.simulator.*;
import com.meng.messtool.system.base.*;
import com.meng.tools.*;
import com.meng.tools.app.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class ChatEditor extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-27 00:06:49
     */
    public static final String TAG = "ChatEditor";

    private ListView mListView;
    private LinkedList<ChatScriptAction> mDataArrays = new LinkedList<ChatScriptAction>();
    private MainAdapter adapter;
    public CharacterManager charaManager;
    public FloatingButton fab;
    private int pointer = 0;
    private File file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_script_editor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save) {
            if (file == null) {
                file = FileTool.getAppFile(FunctionSavePath.chat_script, "script" + System.currentTimeMillis() + ".json");
            }
            FileTool.saveToFile(file, GSON.toJson(mDataArrays).getBytes(StandardCharsets.UTF_8));
        }
        return true;
    }

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.list);
        fab = (FloatingButton) view.findViewById(R.id.fab_select);
    }

    private void init() {
        fab.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View p1) {
                CharSequence[] items = {"在所选条目上方插入", "在所选条目下方插入"};
                new AlertDialog.Builder(getActivity())
                        .setTitle("选择位置")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dia, final int which1) {

                                final AddActionView aav = new AddActionView(getActivity());
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("编辑动作")
                                        .setView(aav)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dia, int which2) {
                                                mDataArrays.add(pointer + which1, aav.getAction());
                                            }
                                        }).setNegativeButton("取消", null).create().show();
                                adapter.notifyDataSetChanged();
                            }
                        }).create().show();
            }
        });
        adapter = new MainAdapter(getActivity(), mDataArrays);
        charaManager = new CharacterManager();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                pointer = p3;
                adapter.setPointer(p3);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public String getTitle() {
        return "聊天脚本编辑器";
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public CharSequence getDescribe() {
        return "聊天模拟器的脚本编辑器";
    }
}
