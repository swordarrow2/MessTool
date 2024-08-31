package com.meng.messtool.modules.rpgdecry;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.messtool.system.base.*;


/*
 *package  com.meng.messtool.modules.rpgdecry
 *@author  清梦
 *@date    2024/8/31 13:04
 */
public class RpgCrypter extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    private RadioButton rbEncry;
    private RadioButton rbDecry;
    private MDEditText etPathToProject;
    private MDEditText etOutputDir;
    private MDEditText etKey;
    private CheckBox cbVerifyDir;
    private CheckBox cbIgnoreFakeHeader;
    private MDEditText etHeaderLen;
    private MDEditText etSignature;
    private MDEditText etVersion;
    private MDEditText etRemain;    private CheckBox cbMv;


    @Override
    public String getTitle() {
        return MainActivity.DEFAULT_TITLE;
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public RpgCrypter() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_rpg_cry_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbEncry = (RadioButton) view.findViewById(R.id.game_rpg_cry_main_rb_encry);
        rbDecry = (RadioButton) view.findViewById(R.id.game_rpg_cry_main_rb_decry);
        etPathToProject = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_path_to_project);
        etOutputDir = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_output_dir);
        etKey = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_key);
        cbVerifyDir = (CheckBox) view.findViewById(R.id.game_rpg_cry_main_cb_verify_dir);
        cbIgnoreFakeHeader = (CheckBox) view.findViewById(R.id.game_rpg_cry_main_cb_ignore_fake_header);
        etHeaderLen = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_header_len);
        etSignature = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_signature);
        etVersion = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_version);
        etRemain = (MDEditText) view.findViewById(R.id.game_rpg_cry_main_et_remain);
        cbMv = (CheckBox) view.findViewById(R.id.game_rpg_cry_main_cb_mv);


        rbEncry.setOnCheckedChangeListener(this);
        rbDecry.setOnCheckedChangeListener(this);
        welcome();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.game_rpg_cry_main_rb_decry:
                cbVerifyDir.setEnabled(false);
                cbIgnoreFakeHeader.setEnabled(false);
                cbMv.setEnabled(true);
                break;
            case R.id.game_rpg_cry_main_rb_encry:
                cbVerifyDir.setEnabled(true);
                cbIgnoreFakeHeader.setEnabled(true);
                cbMv.setEnabled(false);
                break;
        }
    }

    private void welcome() {
    }
}
