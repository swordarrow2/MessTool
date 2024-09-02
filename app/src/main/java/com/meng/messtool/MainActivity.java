package com.meng.messtool;

import android.app.AlertDialog;
import android.content.*;
import android.content.res.*;
import android.hardware.usb.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.modules.electronic.usbserial2.*;
import com.meng.messtool.system.*;
import com.meng.messtool.system.base.*;
import com.meng.messtool.system.debug.*;
import com.meng.messtool.system.menu.*;
import com.meng.messtool.system.task.*;
import com.meng.tools.*;
import com.meng.tools.app.*;
import com.meng.tools.app.update.*;

import java.util.concurrent.*;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    public static String DEFAULT_TITLE = "MessTool";
    private DrawerLayout mDrawerLayout;

    public ListView rightList;
    private boolean firstOpened = false;
    private ActionBarDrawerToggle toggle;

    public void openLeftDrawer() {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    public void openRightDrawer() {
        mDrawerLayout.openDrawer(Gravity.END);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }

    @Override
    public void init() {
        super.init();
        if (SharedPreferenceHelper.isFirstUse()) {
            SharedPreferenceHelper.setFirstUse(false);
            new AlertDialog.Builder(this)
                    .setTitle("Messtool")
                    .setMessage("第一次使用的初始化已经完成，请重启软件")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dia, int which) {
                            finish();
                        }
                    }).create().show();
        }
        setContentView(R.layout.system_main_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        rightList = (ListView) findViewById(R.id.right_list);
        mDrawerLayout.addDrawerListener(toggle);
        rightList.setAdapter(BackgroundTaskAdapter.getInstance());
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateMenu(R.menu.menu_dummy);
        MenuManager.getInstance().init(navigationView.getMenu());
        navigationView.inflateHeaderView(R.layout.system_drawer_header);
        ColorStateList csl = ColorStateList.valueOf(0xff000000);
        navigationView.setItemTextColor(csl);

        navigationView.setItemIconTintList(csl);
        navigationView.getHeaderView(0).setVisibility(SharedPreferenceHelper.isShowSJF() ? View.VISIBLE : View.GONE);
        BackgroundTaskAdapter.getInstance().init(this);
        MFragmentManager.getInstance().init(this);
        MFragmentManager.getInstance().showFragment(Welcome.class);
        Intent intent = getIntent();
        if (intent != null) {
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                MFragmentManager.getInstance().showFragment(DevicesFragment.class);
                closeDrawer();
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        intentFilter.addAction(Constant.USB_PERMISSION);
        registerReceiver(usbReceiver, intentFilter);//注册receiver
        ThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                new UpdateChecker(MainActivity.this).checkUpdate();
            }
        });
    }

    private BroadcastReceiver usbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action) || UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                showToast("USB从机已连接");
                if (MFragmentManager.getInstance().getCurrent().getClass() != DevicesFragment.class && MFragmentManager.getInstance().getCurrent().getClass() != TerminalFragment.class) {
                    ThreadPool.executeAfterTime(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MFragmentManager.getInstance().showFragment(DevicesFragment.class);
                                }
                            });
                        }
                    }, 1000, TimeUnit.MILLISECONDS);
                }
            }
            if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) { // USB拔插动作
                showToast("USB从机已断开");
            }
        }
    };


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (SharedPreferenceHelper.isOpenDrawer() && hasFocus && !firstOpened) {
            openLeftDrawer();
            firstOpened = true;
        } else {
            closeDrawer();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        closeDrawer();
        new TestTask()
                .setTitle("goto genshin")
                .setStatus("自动下载原神中").start();
        FunctionName functionName = FunctionName.values()[item.getItemId()];
        setTitle(functionName.getName());
        Debuger.addLog(TAG, "menu click:" + functionName.getName());

        functionName.doAction();
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (toggle != null) {
            toggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
                closeDrawer();
            } else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                finish();
            } else {
                openLeftDrawer();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                closeDrawer();
            } else {
                openLeftDrawer();
            }
            return true;
        }
        return MFragmentManager.getInstance().getCurrent().onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        AbstractDatabaseHelper.releaseAll();
        if (SharedPreferenceHelper.isExit0()) {
            System.exit(0);
        } else {
            super.finish();
        }
    }
}
