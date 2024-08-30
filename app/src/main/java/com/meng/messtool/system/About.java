package com.meng.messtool.system;

import android.os.*;
import android.view.*;

import com.meng.api.javascript.*;
import com.meng.messtool.*;
import com.meng.messtool.customview.*;
import com.meng.messtool.system.base.*;

public class About extends BaseFragment {

    /*
     *@author 清梦
     *@date 2024-08-25 16:58:58
     */
    public static final String TAG = "About";

    private MengWebView webView;

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
        return inflater.inflate(R.layout.system_about, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = (MengWebView) view.findViewById(R.id.function_system_about_WebView);
        welcome();
    }

    private void welcome() {
        JsApi jo = new JsApi();
        jo.setMainActivity((MainActivity) getActivity());
        webView.loadUrl("file:///android_asset/about/about.html");
        webView.addJavascriptInterface(jo, "obj");

    }
}
