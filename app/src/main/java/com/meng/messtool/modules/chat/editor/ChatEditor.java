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
                file = FileTool.getAppFile(FileSavePath.chat_script, "script" + System.currentTimeMillis() + ".json");
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
                            public void onClick(DialogInterface dia, final int whichInsert) {

                                LinearLayout ll = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.chat_script_editor_add_action, null);
                                final Spinner spinner = (Spinner) ll.findViewById(R.id.chat_script_editor_add_actionSpinner);
                                final MDEditText etFrom = (MDEditText) ll.findViewById(R.id.chat_script_editor_add_action_from);
                                final CheckBox cbFrom = (CheckBox) ll.findViewById(R.id.chat_script_editor_add_actionCheckBox_from_self);
                                final MDEditText etContent = (MDEditText) ll.findViewById(R.id.chat_script_editor_add_action_content);
                                final MDEditText etWait = (MDEditText) ll.findViewById(R.id.chat_script_editor_add_action_wait);
                                final MDEditText etMessageId = (MDEditText) ll.findViewById(R.id.chat_script_editor_add_action_message_id);
                                final Button btnSelectFrom = (Button) ll.findViewById(R.id.chat_script_editor_add_actionButton_select_from);
                                btnSelectFrom.setOnClickListener(new OnClickListener() {

                                    @Override
                                    public void onClick(View p1) {

                                        final CharacterAdapter characterAdapter = new CharacterAdapter(getActivity(), charaManager.getAllCharacter());
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("选择角色")
                                                .setSingleChoiceItems(characterAdapter, 0, new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dia, int whichCharacter) {
                                                        dia.dismiss();
                                                        etFrom.setText(characterAdapter.getItem(whichCharacter).name);
                                                    }
                                                })
                                                .create().show();
                                    }
                                });
                                cbFrom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                    @Override
                                    public void onCheckedChanged(CompoundButton p1, boolean p2) {
                                        btnSelectFrom.setEnabled(!p2);
                                        etFrom.setEnabled(!p2);
                                    }
                                });
                                spinner.setAdapter(new ArrayAdapter<ActionType>(getActivity(), android.R.layout.simple_list_item_1, ActionType.values()));
                                spinner.setSelection(1, true);
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("编辑动作")
                                        .setView(ll)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dia, int whichAction) {
                                                mDataArrays.add(pointer + whichInsert, new ChatScriptAction(
                                                        ActionType.values()[spinner.getSelectedItemPosition()],
                                                        etContent.getString(),
                                                        etFrom.getString(),
                                                        etWait.getInt(),
                                                        cbFrom.isChecked(),
                                                        etMessageId.getInt()));
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
