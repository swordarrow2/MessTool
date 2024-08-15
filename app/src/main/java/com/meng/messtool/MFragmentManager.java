package com.meng.messtool;

import android.app.*;

import com.meng.messtool.modules.picture.barcode.*;
import com.meng.tools.app.*;

import java.util.*;

import static com.meng.messtool.ApplicationHolder.*;

public class MFragmentManager {

    /*
     *@author 清梦
     *@date 2024-04-19 09:55:53
     */
    public static final String TAG = "FragmentManager";
    private HashMap<String, BaseFragment> fragments = new HashMap<>();
    private Activity activity;
    private static MFragmentManager instance;
    private BaseFragment current;
    private SettingsPreference setting = new SettingsPreference();

    public static MFragmentManager getInstance() {
        if (instance == null) {
            instance = new MFragmentManager();
        }
        return instance;
    }

    public void init(Activity a) {
        activity = a;
        Debuger.addLog(TAG, "init", activity.getClass());
        FragmentTransaction trans = a.getFragmentManager().beginTransaction();
        trans.add(R.id.fragment, setting);
        BarcodeAwesome frag = new BarcodeAwesome();
        fragments.put(BarcodeAwesome.class.getName(), frag);
        trans.add(R.id.fragment, frag);
        current = frag;
        trans.commit();
    }

    public <T extends BaseFragment> T getFragment(Class<T> c) {
        Debuger.addLog(TAG, "getFragment", c.getName());
        return (T) fragments.get(c.getName());
    }

    public <T extends BaseFragment> void registFragment(T fragment) {
        fragments.put(fragment.getClass().getName(), fragment);
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, fragment);
        transaction.commit();
    }

    public SettingsPreference getSettingPreference() {
        Debuger.addLog(TAG, "getSettingPreference");
        return setting;
    }

    public <T extends BaseFragment> void showFragment(Class<T> c) {
        Debuger.addLog(TAG, "showFragment", c.getName());
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        BaseFragment fragment = fragments.get(c.getName());
        if (fragment == null) {
            try {
                Class<?> cls = Class.forName(c.getName());
                fragment = (BaseFragment) cls.newInstance();
                fragments.put(c.getName(), fragment);
                transaction.add(R.id.fragment, fragment);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                showToast("发生错误：" + e.toString());
                Debuger.addLog(TAG, e.toString());
                ExceptionCatcher.getInstance().uncaughtException(Thread.currentThread(), e);
            }
        }
        current = fragment;
        hideFragment();
        transaction.show(fragment);
        BaseActivity activity = ApplicationHolder.getActivity();
        if (activity != null) {
            activity.setTitle(fragment.getTitle());
        }
        transaction.commit();
        fragment.onResume();
    }

    public void showSettingFragment() {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        hideFragment();
        transaction.show(setting);
        transaction.commit();
    }

    public void hideFragment() {
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        for (BaseFragment f : fragments.values()) {
            ft.hide(f);
            f.onPause();
        }
        ft.hide(setting);
        ft.commit();
    }

    public BaseFragment getCurrent() {
        return current;
    }
}
