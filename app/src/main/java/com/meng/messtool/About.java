package com.meng.messtool;

import android.os.*;
import android.view.*;
import android.widget.*;
import com.meng.messtool.*;
import com.meng.messtool.customview.*;

public class About extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-25 16:58:58
     */
    public static final String TAG = "About";

    private MengWebView mWebView;

    @Override
    public String getTitle() {
        return MainActivity.DEFAULT_TITLE;
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_system_about, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWebView = (MengWebView) view.findViewById(R.id.function_system_about_WebView);
        welcome();
    }

    private void welcome() {
        mWebView.loadUrl("file:///android_asset/about/about.html");
        mWebView.addJavascriptInterface(getActivity(),"mainactivity");
    }
}
