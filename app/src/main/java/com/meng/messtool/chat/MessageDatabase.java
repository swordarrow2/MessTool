package com.meng.messtool.chat;

import com.meng.messtool.*;
import com.meng.tools.*;
import com.meng.tools.app.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class MessageDatabase {

    /*
     *@author 清梦
     *@date 2024-08-26 17:22:11
     */
    public static final String TAG = "MessageDatabase";

    private ArrayList<ChatScriptAction> chatMsg = new ArrayList<>();

    public MessageDatabase(String scriptName) {
        File file = FileTool.getAppFile(FunctionSavePath.chat_script, "exmaple.json");
        try {
        //    if (!file.exists()) {
                ArrayList<ChatScriptAction> list = new ArrayList<>();
                
                    ChatScriptAction cs1 = new ChatScriptAction();
                    cs1.action = ActionType.TYPE_DATE_TIP;
                    cs1.content = SystemTools.getTimeWithMinute();
                    cs1.delay = 0;
                    cs1.from = "";
                    list.add(cs1);
                

                
                    ChatScriptAction cs2 = new ChatScriptAction();
                    cs2.action = ActionType.TYPE_STRING_MESSAGE;
                    cs2.content = "测试2";
                    cs2.delay = 500;
                    cs2.from = "观星";
                    list.add(cs2); 
                
                
                    ChatScriptAction cs3 = new ChatScriptAction();
                    cs3.action = ActionType.TYPE_STRING_MESSAGE;
                    cs3.content = "测试2233";
                    cs3.delay = 1000;
                    cs3.from = "月下";
                    list.add(cs3); 
                

                        //      } else {
                //       ArrayList<ChatScriptAction> list = GSON.fromJson(FileTool.readString(file), new TypeToken<ArrayList<ChatMsgEntity>>(){}.getType());
                chatMsg.addAll(list);
                FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));
                
       //     }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChatScriptAction> getMsg(int id) {
        return chatMsg;
    }
}
    
