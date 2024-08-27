package hq.king;
import com.google.gson.reflect.*;
import com.meng.tools.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import com.meng.messtool.*;

public class CharaManager {

    private LinkedHashSet<ChatChara> friends = new LinkedHashSet<ChatChara>();//{{
    
    public CharaManager() {
        File file = FileTool.getAppFile(FunctionSavePath.chat_character, "chara.json"); //new File(path);
        try {
            if (!file.exists()) {
                LinkedHashSet<ChatChara> list = new LinkedHashSet<>();
                list.add(new ChatChara("观星", "star.jpg"));
                list.add(new ChatChara("月下", "moon.jpg"));
                FileTool.saveToFile(file, GSON.toJson(list).getBytes(StandardCharsets.UTF_8));
            } else {
                LinkedHashSet<ChatChara> fromJson = GSON.fromJson(FileTool.readString(file), new TypeToken<LinkedHashSet<ChatChara>>(){}.getType());
                friends.addAll(fromJson);        
            } 
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatChara get(String name) {
        for (ChatChara f : friends) {
            if (f.name.equals(name)) {
                return f;  
            }
        }
        return new ChatChara();
    }
}
