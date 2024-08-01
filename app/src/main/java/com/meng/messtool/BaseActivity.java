package com.meng.messtool;

import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import com.meng.tools.*;
import com.meng.tools.app.*;

public abstract class BaseActivity extends AppCompatActivity {
    /*
     *@author 清梦
     *@date 2024-07-30 17:00:07
     */
    public static final String TAG = "BaseActivity";
    public int theme = 0;

    private Context context;

    public LinearLayout mainLinearLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.system_base_activity_layout);
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLinearLayout = (LinearLayout) findViewById(R.id.main_linear_layout);
    }

    @Override
    public void setContentView(int layoutResID) {
        getLayoutInflater().inflate(layoutResID, mainLinearLayout);
    }

    @Override
    public void setTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    public void setSubtitle(CharSequence title) {
        toolbar.setSubtitle(title);
    }

    @Override
    public void setTheme(int resid) {
        switch (SharedPreferenceHelper.getTheme()) {
            case "芳":
                super.setTheme(theme = R.style.green);
                break;
            case "红":
                super.setTheme(theme = R.style.red);
                break;
            case "黑":
                super.setTheme(theme = R.style.black);
                break;
            case "紫":
                super.setTheme(theme = R.style.purple);
                break;
            case "蓝":
                super.setTheme(theme = R.style.blue);
                break;
            default:
                super.setTheme(theme = R.style.green);
                break;
        }
    }

    public void showToast(final String msgAbbr, final String msgOrigin) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Snackbar.make(mainLinearLayout, msgAbbr, 5000)
                        .setAction("查看全文", msgOrigin.trim().length() == 0 ? null : new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher)
                                        .setTitle("全文").setMessage(msgOrigin).setNegativeButton("复制", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface p1, int p2) {
                                        AndroidContent.copyToClipboard(msgOrigin);
                                        showToast("复制成功");
                                    }
                                }).setPositiveButton("确定", null).show();
                            }
                        }).show();
            }
        });
    }

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Snackbar.make(mainLinearLayout, msg, 5000)
                        .setAction("查看全文", getLines(msg) < 2 && msg.length() < 40 ? null : new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher)
                                        .setTitle("全文").setMessage(msg).setNegativeButton("复制", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface p1, int p2) {
                                        AndroidContent.copyToClipboard(msg);
                                        showToast("复制成功");
                                    }
                                }).setPositiveButton("确定", null).show();
                            }
                        }).show();
            }
        });
    }

    private int getLines(String s) {
        int l = 0;
        for (char c : s.toCharArray()) {
            if (c == '\n') {
                ++l;
            }
        }
        return l;
    }

    public void scanBarcode() {
        Intent intent = new Intent("com.meng.barcode.scan");
        startActivityForResult(intent, Constant.REQUEST_CODE_SCAN_BARCODE);
    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_FILE);
    }

    public void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_FILE);
    }

    public void selectFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECT_FILE);
    }

}
