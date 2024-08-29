package com.meng.messtool.modules.chat.simulator;

public enum ActionType {

    /*
     *@author 清梦
     *@date 2024-08-26 12:45:12
     */
    TYPE_SET_GROUP_NAME("TYPE_SET_GROUP_NAME"),
    TYPE_STRING_MESSAGE("TYPE_STRING_MESSAGE"),
    TYPE_IMAGE_MESSAGE("TYPE_IMAGE_MESSAGE"),
    TYPE_MESSAGE_RECALL("TYPE_MESSAGE_RECALL"),
    TYPE_CHAT_TIP("TYPE_CHAT_TIP"),
    TYPE_WAIT_MESSAGE("TYPE_WAIT_MESSAGE"),
    TYPE_DIALOG("TYPE_DIALOG");


    public static final String TAG = "ActionType";

    ActionType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    private String friendlyName;

    public String getFriendlyName() {
        return friendlyName;
    }
}
