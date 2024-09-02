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
                FunctionGroup functionGroup = entry.getKey();
                SubMenu subMenu = menu.addSubMenu(0, functionGroup.ordinal(), 0, functionGroup.getName());
                for (FunctionName functionName : entry.getValue()) {
                    Debuger.addLog(TAG, "add function", functionName.getName());
                    MenuItem item = subMenu.add(0, functionName.ordinal(), 0, functionName.getName());
                    setIcon(functionName, item, functionGroup);
                }
            }
        } else {
            for (Map.Entry<FunctionGroup, LinkedList<FunctionName>> entry : menuEntry.entrySet()) {
                for (FunctionName functionName : entry.getValue()) {
                    MenuItem item = menu.add(functionName.getGroup().ordinal(), functionName.ordinal(), 0, functionName.getName());
                    Debuger.addLog(TAG, "add function", functionName.getName());
                    setIcon(functionName, item, entry.getKey());
                }
            }
        }
    }

    private void setIcon(FunctionName functionName, MenuItem item, FunctionGroup key) {
        if (functionName.getIconId() != -1) {
            item.setIcon(functionName.getIconId());
            return;
        }
        switch (key) {
            case GROUP_DEVELOPING:
                item.setIcon(R.drawable.ic_dev_to);
                break;
            case GROUP_DEPRECATED:
                item.setIcon(R.drawable.ic_close);
                break;
            case GROUP_SYSTEM:
                item.setIcon(R.drawable.ic_information_outline);
                break;
            case GROUP_BOX_ARRAY:
            case GROUP_MEDIA:
                item.setIcon(android.R.drawable.ic_menu_gallery);
                break;
            case GROUP_ELECTRONIC:
                item.setIcon(R.drawable.ic_developer_board);
                break;
            case GROUP_TOY:
                item.setIcon(R.drawable.ic_menu);
                break;
            case GROUP_DEFAULT:
            case GROUP_LONG_TIME_NO_USE:
                item.setIcon(R.drawable.ic_menu);
        }
    }
}
