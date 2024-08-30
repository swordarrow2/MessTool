package rpgde;

import com.meng.tools.*;
import com.meng.tools.app.*;

import java.io.*;

public class App {

    public void start() {
        Decrypt decrypt = new Decrypt();
        decrypt.setPathToProject("/storage/emulated/0/backups/assets/www/");
        File appFile = FileTool.getAppFile(FileSavePath.RPG_DECRYPT, "");
        decrypt.setOutputDir(appFile.getAbsolutePath());
        decrypt.handleFiles();
    }

}
