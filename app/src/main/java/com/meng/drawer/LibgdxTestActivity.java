package com.meng.drawer;

import android.os.*;
import android.util.*;

import com.badlogic.gdx.backends.android.*;
import com.meng.drawer.gui.*;
import com.meng.messtool.*;
import com.meng.tools.app.*;

public class LibgdxTestActivity extends AndroidApplication {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ResourcesManager.pathBase = "/storage/emulated/0/AppProjects/ZunRunner/2un/";
        setContentView(initializeForView(new PicMain(), new AndroidApplicationConfiguration()));
    }

    @Override
    public void setTheme(int resid) {
        switch (SharedPreferenceHelper.getTheme()) {
            case "芳":
                super.setTheme(R.style.green);
                break;
            case "红":
                super.setTheme(R.style.red);
                break;
            case "黑":
                super.setTheme(R.style.black);
                break;
            case "紫":
                super.setTheme(R.style.purple);
                break;
            case "蓝":
                super.setTheme(R.style.blue);
                break;
            default:
                super.setTheme(R.style.green);
                break;
        }
        TypedValue outValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.theme_id, outValue, true);
        System.out.print("theme:" + outValue.data);
    }
}
