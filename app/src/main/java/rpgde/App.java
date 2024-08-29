package rpgde;

public class App {
    public static String outputDir;
    public static Preferences preferences;
    private static CMD cmd;

    public static void main(String[] args) {
        // Ensure System output dir always exists
        if (!File.existsDir(Config.DEFAULT_OUTPUT_DIR))
            File.createDirectory(Config.DEFAULT_OUTPUT_DIR);
        cmd = new CMD(args);
        cmd.runCMD();
    }
}
