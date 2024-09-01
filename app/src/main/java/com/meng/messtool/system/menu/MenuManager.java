package com.meng.messtool.system.menu;

import android.view.*;

import com.meng.messtool.*;
import com.meng.messtool.system.debug.*;
import com.meng.tools.app.*;

import java.util.*;

public class MenuManager {

    /*
     *@author 清梦
     *@date 2024-06-26 13:33:47
     */
    public static final String TAG = "MenuManager";

    private static MenuManager instance;

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    private LinkedHashMap<FunctionGroup, LinkedList<FunctionName>> menuEntry = new LinkedHashMap<>();

    public void init(Menu menu) {
        Debuger.addLog(TAG, "init");
        menu.clear();
        for (FunctionGroup group : FunctionGroup.values()) {
            if (!group.isShow()) {
                Debuger.addLog(TAG, "hideGroup", group.toString());
                continue;
            }
            menuEntry.put(group, new LinkedList<FunctionName>());
        }
        for (FunctionName name : FunctionName.values()) {
            FunctionGroup group = name.getGroup();
            if (!group.isShow()) {
                continue;
            }
            menuEntry.get(group).add(name);
        }
        if (SharedPreferenceHelper.isShowGroupName()) {
            for (Map.Entry<FunctionGroup, LinkedList<FunctionName>> entry : menuEntry.entrySet()) {
                FunctionGroup key = entry.getKey();
                SubMenu subMenu = menu.addSubMenu(0, key.ordinal(), 0, key.getName());
                for (FunctionName fn : entry.getValue()) {
                    MenuItem item = subMenu.add(0, fn.ordinal(), 0, fn.getName());
                    switch (key) {
                        case GROUP_DEVELOPING:
                            item.setIcon(R.drawable.ic_edit);
                            break;
                        case GROUP_DEPRECATED:
                            item.setIcon(R.drawable.ic_close);
                            break;
                        case GROUP_SYSTEM:
                            item.setIcon(R.drawable.ic_progress);
                            break;
                        case GROUP_BOX_ARRAY:
                        case GROUP_MEDIA:
                            item.setIcon(android.R.drawable.ic_menu_gallery);
                            break;
                        case GROUP_ELECTRONIC:
                        case GROUP_DEFAULT:
                        case GROUP_TOY:
                        case GROUP_LONG_TIME_NO_USE:
                            item.setIcon(R.drawable.ic_menu);
                    }
                    Debuger.addLog(TAG, "add function", fn.getName());
                }
            }
        } else {
            for (Map.Entry<FunctionGroup, LinkedList<FunctionName>> entry : menuEntry.entrySet()) {
                for (FunctionName fn : entry.getValue()) {
                    MenuItem item = menu.add(fn.getGroup().ordinal(), fn.ordinal(), 0, fn.getName());
                    item.setIcon(R.drawable.ic_menu);
                    Debuger.addLog(TAG, "add function", fn.getName());
                }
            }
        }
    }
}
