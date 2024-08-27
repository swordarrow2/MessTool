package hq.king;

import android.content.Context;
import com.google.gson.Gson;
import hq.king.view.ChatMsgEntity;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import hq.king.util.MyDate;
import com.google.gson.reflect.TypeToken;
import com.meng.tools.*;
import com.meng.messtool.*;

public class MessageDB {

    private ArrayList<ChatMsgEntity> chatMsg = new ArrayList<>();
        
    public MessageDB(String scriptName) {
        File file = FileTool.getAppFile(FunctionSavePath.chat_script, scriptName);
        try {
            if (!file.exists()) {
                ArrayList<ChatMsgEntity> list = new ArrayList<>();
                list.add(new ChatMsgEntity("观星", MyDate.getTime(), "刺客先生给我买这个"));
                list.add(new ChatMsgEntity("观星", MyDate.getTime(), "刺客先生给我买"));
                FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));
            } else {
                ArrayList<ChatMsgEntity> list = GSON.fromJson(FileTool.readString(file), new TypeToken<ArrayList<ChatMsgEntity>>(){}.getType());
                chatMsg.addAll(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ChatMsgEntity> getMsg(int id) {
        return chatMsg;
    }
}
