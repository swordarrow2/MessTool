package com.meng.messtool.modules.chat.simulator;

import com.google.gson.reflect.*;
import com.meng.tools.*;
import com.meng.tools.app.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class MessageManager {

    /*
     *@author 清梦
     *@date 2024-08-26 17:22:11
     */
    public static final String TAG = "MessageManager";

    private ArrayList<ChatScriptAction> chatMsg = new ArrayList<>();

    public MessageManager(String scriptName) {
        
        File file = FileTool.getAppFile(FileSavePath.chat_script, scriptName);
        try {
            ArrayList<ChatScriptAction> list = GSON.fromJson(FileTool.readString(file), new TypeToken<ArrayList<ChatScriptAction>>(){}.getType());
            chatMsg.addAll(list);
            FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createExample() {
        File file = FileTool.getAppFile(FileSavePath.chat_script, "exmaple.json");
        try {
            ArrayList<ChatScriptAction> list = new ArrayList<>();

            list.add(new ChatScriptAction(
                         ActionType.TYPE_SET_GROUP_NAME,
                         "此生无悔入东方来世愿生幻想乡",
                         "",
                         0));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_CHAT_TIP,
                         SystemTools.getTimeWithMinute(),
                         "",
                         0));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "测试2",
                         "观星",
                         1000));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "测试2233",
                         "月下",
                         -1)); 
            list.add(new ChatScriptAction(
                         ActionType.TYPE_SET_GROUP_NAME,
                         "朕的煌国",
                         "",
                         0));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_CHAT_TIP,
                         "\"观星\"修改了群名为\"朕的煌国\"",
                         "",
                         0));
                         
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "刺客先生给我买这个",
                         "观星",
                         1000));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "刺客先生给我买",
                         "观星",
                         1000));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "人类...晚上老地方见",
                         "月下",
                         -1)); 
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "？",
                         "观星",
                         1000));
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "什么老地方",
                         "观星",
                         1000));         

                         
                         
            list.add(new ChatScriptAction(
                         ActionType.TYPE_IMAGE_MESSAGE,
                         "逐火往事.jpg",
                         "魂钢的优势",
                         -1));  
            list.add(new ChatScriptAction(
                         ActionType.TYPE_IMAGE_MESSAGE,
                         "神州折剑.jpg",
                         "你弱爆了",
                         -1));    
            list.add(new ChatScriptAction(
                         ActionType.TYPE_IMAGE_MESSAGE,
                         "翩然若生.jpg",
                         "希儿",
                         -1));     

            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200)); 
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200));          
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200));                    
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200));                   
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200));                    
            list.add(new ChatScriptAction(
                         ActionType.TYPE_STRING_MESSAGE,
                         "?",
                         "仿犹大",
                         200));          
            list.add(new ChatScriptAction(
                         ActionType.TYPE_DIALOG,
                         "\"仿犹大\"解散了本群",
                         "提示",
                         2000));                     

//            ChatScriptAction csDialog = new ChatScriptAction();
//            csDialog.action = ActionType.TYPE_DIALOG;
//            csDialog.content = "此群因传播色情信息已被封禁";
//            csDialog.wait = 0;
//            csDialog.from = "警告";
//            list.add(csDialog);

            FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChatScriptAction> getMsg(int id) {
        return chatMsg;
    }
}
    
