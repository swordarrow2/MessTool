package com.meng.messtool.system.base;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

import com.meng.messtool.*;

public abstract class BaseFragment extends Fragment {

    /*
     *@author 清梦
     *@date 2024-04-19 09:45:07
     */
    public static final String TAG = "BaseFragment";

    public abstract String getVersionName();

    public abstract String getTitle();

    public CharSequence getDescribe() {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getDescribe() != null) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add(0, Constant.FUNCTION_NOTE_MENU_ID, 0, "功能说明");
        item.setIcon(R.drawable.ic_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Constant.FUNCTION_NOTE_MENU_ID) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Messtool")
                    .setMessage(getDescribe())
                    .setPositiveButton("确定", null)
                    .create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(final String msgAbbr, final String msgOrigin) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showToast(msgAbbr, msgOrigin);
        }
    }

    public void showToast(final String msg) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showToast(msg);
        }
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

    public void scanBarcode() {
        Intent intent = new Intent("com.meng.barcode.scan");
        startActivityForResult(intent, Constant.REQUEST_CODE_SCAN_BARCODE);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
