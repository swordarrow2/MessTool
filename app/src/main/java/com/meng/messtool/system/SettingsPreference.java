package com.meng.messtool.system;

import android.os.*;
import android.preference.*;

import com.meng.messtool.*;

public class SettingsPreference extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName("main");
        addPreferencesFromResource(R.xml.preference);
    }
}
