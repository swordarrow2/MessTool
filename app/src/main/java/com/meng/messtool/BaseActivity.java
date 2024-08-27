package com.meng.messtool;

import android.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.meng.tools.*;
import com.meng.tools.app.*;
import java.util.*;
import java.util.concurrent.*;

import android.app.AlertDialog;
import android.support.v7.widget.Toolbar;

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
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.system_base_activity_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.REQUEST_CODE_REQUEST_PERMISSION);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != Constant.REQUEST_CODE_REQUEST_PERMISSION) {
            return;
        }
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            showToast("缺失权限会使应用工作不正常");
        } else {
            init();
        }
    }
    
    public void init() {
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLinearLayout = (LinearLayout) findViewById(R.id.main_linear_layout);
        Debuger.init(this);
        if (SharedPreferenceHelper.isDebugMode()) {
            ThreadPool.executeAtFixedRate(new Runnable() {

                    @Override
                    public void run() {
                        final float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
                        final float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
                        final float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
                        runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setSubtitle(String.format(Locale.CHINA, "max:%.0fM,use:%.2f/%.2fM", maxMemory, freeMemory, totalMemory));
                                }
                            });
                    }
                }, 1000, 1000, TimeUnit.MILLISECONDS);
        }
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
        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.theme_id, outValue, true);
        System.out.print("theme:" + outValue.data);
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
