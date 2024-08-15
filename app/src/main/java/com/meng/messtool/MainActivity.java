package com.meng.messtool;

import android.*;
import android.content.pm.*;
import android.content.res.*;
import android.os.*;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import com.meng.messtool.menu.*;
import com.meng.messtool.task.*;
import com.meng.tools.*;
import com.meng.tools.app.*;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.REQUEST_CODE_REQUEST_PERMISSION);
        } else {
            init();
        }
    }

    private void init() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        rightList = (ListView) findViewById(R.id.right_list);
        mDrawerLayout.setDrawerListener(toggle);
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

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (SharedPreferenceHelper.isOpenDrawer() && hasFocus && !firstOpened) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            firstOpened = true;
        } else {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerLayout.closeDrawer(GravityCompat.END);
//        new TestTask()
//                .setTitle("goto genshin")
//                .setStatus("自动下载原神中").start();
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
                mDrawerLayout.closeDrawer(Gravity.END);
            } else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                exit();
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return MFragmentManager.getInstance().getCurrent().onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != Constant.REQUEST_CODE_REQUEST_PERMISSION) {
            return;
        }
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            showToast("缺失权限会使应用工作不正常");
        } else {
            init();
        }
    }

    public void exit() {
        AbstractDatabaseHelper.releaseAll();
        if (SharedPreferenceHelper.isExit0()) {
            System.exit(0);
        } else {
            finish();
        }
    }
}
