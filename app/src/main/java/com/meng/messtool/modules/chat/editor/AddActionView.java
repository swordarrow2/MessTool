package com.meng.messtool.modules.chat.editor;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.modules.chat.simulator.*;

public class AddActionView extends LinearLayout {

    /*
     *@author 清梦
     *@date 2024-08-27 20:58:46
     */
    public static final String TAG = "AddActionView";

    private Spinner spinner;
    private CheckBox cbFrom;
    private EditText etFrom,etContent,etWait,etMessageId;
    private Button btnSelectFrom;

    public AddActionView(Context context) {
        super(context);
        init(context);
    }

    public AddActionView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.function_chat_script_editor_add_action, this);
        spinner = (Spinner) findViewById(R.id.function_chat_script_editor_add_actionSpinner);
        etFrom = (EditText) findViewById(R.id.function_chat_script_editor_add_action_from);
        cbFrom = (CheckBox)findViewById(R.id.function_chat_script_editor_add_actionCheckBox_from_self);
        etContent = (EditText)findViewById(R.id.function_chat_script_editor_add_action_content);
        etWait = (EditText)findViewById(R.id.function_chat_script_editor_add_action_wait);
        etMessageId = (EditText)findViewById(R.id.function_chat_script_editor_add_action_message_id);
        btnSelectFrom = (Button)findViewById(R.id.function_chat_script_editor_add_actionButton_select_from);
        cbFrom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton p1, boolean p2) {
                    btnSelectFrom.setEnabled(!p2);
                    etFrom.setEnabled(!p2);
                }
            });
            spinner.setAdapter(new ArrayAdapter<ActionType>(context,android.R.layout.simple_list_item_1,ActionType.values()));
    }

    public ChatScriptAction getAction() {
        return new ChatScriptAction(
            ActionType.values()[spinner.getSelectedItemPosition()],
            etContent.getText().toString(),
            etFrom.getText().toString(),
            Integer.parseInt(etWait.getText().toString()),
            cbFrom.isChecked(),
            Integer.parseInt(etMessageId.getText().toString()));
    }
}
