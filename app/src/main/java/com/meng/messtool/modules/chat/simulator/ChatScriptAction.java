package com.meng.messtool.modules.chat.simulator;

public class ChatScriptAction {
    
    /*
	*@author 清梦
	*@date 2024-08-26 12:44:16
    */
public static final String TAG = "ChatScriptAction";
    
    public ActionType action;
    public String content;
    public String from;
    public int wait;
    public boolean isSelf;
    public int id;

    public ChatScriptAction(ActionType action, String content, String from, int wait, boolean isSelf, int id) {
        this.action = action;
        this.content = content;
        this.from = from;
        this.wait = wait;
        this.isSelf = isSelf;
        this.id = id;
    }

    public ChatScriptAction(ActionType action, String content, String from, int wait) {
        this.action = action;
        this.content = content;
        this.from = from;
        this.wait = wait;
    }

}
