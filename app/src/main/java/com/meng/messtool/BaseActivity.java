package com.meng.messtool;

import android.content.*;
import android.support.v7.app.AppCompatActivity;

import com.meng.tools.app.*;

public abstract class BaseActivity extends AppCompatActivity {
    /*
     *@author 清梦
     *@date 2024-07-30 17:00:07
     */
    public static final String TAG = "BaseFragment";
    public int theme = 0;

    @Override
    public void setTheme(int resid) {
        SharedPreferenceHelper.init(this, "main");
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
