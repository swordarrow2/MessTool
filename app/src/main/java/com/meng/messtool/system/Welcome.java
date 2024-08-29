package com.meng.messtool.system;

import android.os.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.*;
import com.meng.messtool.system.base.*;

public class Welcome extends BaseFragment {

    private TextView textView;

    @Override
    public String getTitle() {
        return MainActivity.DEFAULT_TITLE;
    }

    @Override
    public String getVersionName() {
        return "V0.0.1";
    }

    public Welcome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.text_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.aboutTextView);
        welcome();
    }

    private void welcome() {
        textView.setText("选择想要使用的功能吧");
    }
}

