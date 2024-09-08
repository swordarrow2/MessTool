package com.meng.messtool.modules.fpvtool.gui.pages;


import android.view.*;
import android.widget.*;

import com.meng.messtool.modules.fpvtool.gui.*;

public class PageTest implements IFpvConfigPage {


    private FpvConfigMainTabFragment host;
    private final int tabIndex;

    public PageTest(FpvConfigMainTabFragment host, int tabIndex) {
        this.host = host;
        this.tabIndex = tabIndex;
    }

    @Override
    public View getMainView(FrameLayout tabContent) {
        TextView textView = new TextView(host.getActivity());
        textView.setId(getName().hashCode());
        textView.setTextSize(100);
        textView.setText("vsnljenclkdnclkd");
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return textView;
    }

    @Override
    public void processRecieved(final byte[] data) {
    }

    @Override
    public String getName() {
        return "测试页面";
    }

}
