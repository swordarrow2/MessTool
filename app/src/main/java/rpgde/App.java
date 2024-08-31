package rpgde;

import com.meng.tools.*;
import com.meng.tools.app.*;

import java.io.*;

public class App {

    public void start() {
        CryManager cryManager = new CryManager();
        cryManager.setPathToProject("/storage/emulated/0/backups/assets/www/");
        File appFile = FileTool.getAppFile(FileSavePath.RPG_DECRYPT, "");
        cryManager.setOutputDir(appFile.getAbsolutePath());
        cryManager.decry();
    }
    public void start2() {
        CryManager cryManager = new CryManager();
        cryManager.setPathToProject(FileTool.getAppFile(FileSavePath.RPG_DECRYPT, "").getAbsolutePath());
        File appFile = new File("/storage/emulated/0/backups/as/");
        cryManager.setOutputDir(appFile.getAbsolutePath());
        cryManager.encry();
    }
}
