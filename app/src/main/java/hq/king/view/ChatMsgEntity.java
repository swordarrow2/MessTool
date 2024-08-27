package hq.king.view;

import hq.king.ChatChara;
import android.graphics.Bitmap;

public class ChatMsgEntity {

    public String name;
    public String date;
    public String message;
    public boolean rec = true;
    public boolean span = false;
    public String spanPic;
    public int delay = 1000;

    public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
        this.name = name;
        this.date = date;
        this.message = text;
        this.rec = isComMsg;
    }

    public ChatMsgEntity(String name, String date, String text) {
        this.name = name;
        this.date = date;
        this.message = text;
    }


    public ChatMsgEntity(ChatChara fe, String message) {
        name = fe.name;
        this.message = message;
    }
    
    public ChatMsgEntity(){}
}
