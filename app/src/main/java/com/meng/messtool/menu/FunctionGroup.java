package com.meng.messtool.menu;

import com.meng.tools.app.*;

public enum FunctionGroup {

    /*
     *@author 清梦
     *@date 2024-06-26 09:48:49
     */

    GROUP_DEVELOPING("正在开发"),
    //    GROUP_FPV("FPV工具"),
    GROUP_BOX_ARRAY("物品存放盒"),
    GROUP_PICTURE("图片处理"),
    GROUP_VIDEO("视频"),
    GROUP_AUDIO("声音"),
    GROUP_ELECTRONIC("电子开发"),
    GROUP_DEFAULT("默认"),
    GROUP_TOY("玩具"),
    GROUP_LONG_TIME_NO_USE("待重验证"),
    GROUP_DEPRECATED("已弃用"),
    GROUP_SYSTEM("系统");

    public static final String TAG = "FunctionGroup";

    private String name;
    private final String key;

    FunctionGroup(String name) {
        this.name = name;
        key = toString();
    }

    public boolean isShow() {
        return SharedPreferenceHelper.isShowGroup(key);
    }

    public String getName() {
        return name;
    }

}
