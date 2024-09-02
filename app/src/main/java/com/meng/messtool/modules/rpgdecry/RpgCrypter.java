package com.meng.messtool.modules.rpgdecry;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.customview.MaterialDesign.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.system.task.*;


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
    private MDEditText etRemain;
    private CheckBox cbMv;
    private Button btnStart;


    private String key = null;
    private boolean verifyDir = false;
    private boolean ignoreFakeHeader = true;
    private int headerLen = Decrypter.DEFAULT_HEADER_LEN;
    private String signature = Decrypter.DEFAULT_SIGNATURE;
    private String version = Decrypter.DEFAULT_VERSION;
    private String remain = Decrypter.DEFAULT_REMAIN;

    // Encrypter options
    private boolean toMV = true;


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
        btnStart = (Button) view.findViewById(R.id.game_rpg_cry_button);


        rbEncry.setOnCheckedChangeListener(this);
        rbDecry.setOnCheckedChangeListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbDecry.isChecked()) {

                    BackgroundTask bt = new BackgroundTask() {
                        @Override
                        public void run() {
                            try {
                                RPG_Project rpgProject = new RPG_Project(getProjectPath(), isVerifyDir());
                                Decrypter decrypter = new Decrypter(getKey());
                                rpgProject.setOutputPath(getOutputPath());
                                decrypter.setIgnoreFakeHeader(isIgnoreFakeHeader());
                                decrypter.setHeaderLen(getHeaderLen());
                                decrypter.setSignature(getSignature());
                                decrypter.setVersion(getVersion());
                                decrypter.setRemain(getRemain());
                                rpgProject.decryptFilesCmd(decrypter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bt.start();
                } else if (rbEncry.isChecked()) {
                    BackgroundTask bt = new BackgroundTask() {
                        @Override
                        public void run() {
                            try {
                                RPG_Project rpgProject = new RPG_Project(getProjectPath(), false);
                                Decrypter encrypter = new Decrypter(getKey());
                                rpgProject.setOutputPath(getOutputPath());
                                rpgProject.setMV(isToMV());
                                encrypter.setHeaderLen(getHeaderLen());
                                encrypter.setSignature(getSignature());
                                encrypter.setVersion(getVersion());
                                encrypter.setRemain(getRemain());
                                rpgProject.encryptFilesCmd(encrypter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bt.start();
                } else {
                    showToast("功能选择错误");
                }
            }
        });
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

    public String getProjectPath() {
        return etPathToProject.getString();
    }


    public String getOutputPath() {
        return etOutputDir.getString();
    }


    public String getKey() {
        return etKey.getString();
    }

    public boolean isVerifyDir() {
        return cbVerifyDir.isChecked();
    }

    public boolean isIgnoreFakeHeader() {
        return cbIgnoreFakeHeader.isChecked();
    }

    public int getHeaderLen() {
        return etHeaderLen.getInt();
    }

    public String getSignature() {
        return etSignature.getString();
    }

    public String getVersion() {
        return etVersion.getString();
    }

    public String getRemain() {
        return etRemain.getString();
    }

    public boolean isToMV() {
        return cbMv.isChecked();
    }
}

