package com.meng.messtool.modules.chat.simulator;

import com.google.gson.reflect.*;
import com.meng.tools.*;
import com.meng.tools.app.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class CharacterManager {

    /*
     *@author 清梦
     *@date 2024-08-26 17:45:24
     */
    public static final String TAG = "CharacterManager";

    private LinkedHashSet<ChatCharacter> friends = new LinkedHashSet<>();//{{

    public CharacterManager() {
        File file = FileTool.getAppFile(FunctionSavePath.chat_character, "chara.json"); //new File(path);
        try {
            if (!file.exists()) {
                LinkedHashSet<ChatCharacter> list = new LinkedHashSet<>();
                list.add(new ChatCharacter("观星", "star.jpg"));
                list.add(new ChatCharacter("月下", "moon.jpg"));
                FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));
            } else {
                LinkedHashSet<ChatCharacter> fromJson = GSON.fromJson(FileTool.readString(file), new TypeToken<LinkedHashSet<ChatCharacter>>(){}.getType());
                friends.addAll(fromJson);        
            } 
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatCharacter get(String name) {
        for (ChatCharacter f : friends) {
            if (f.name.equals(name)) {
                return f;  
            }
        }
        return new ChatCharacter("", "");
    }
}
