package rpgde;

public class CMD {
    public static final int STATUS_OK = 0;
    public static final int STATUS_INFO = 2;
    public static final int STATUS_WARNING = 1;
    public static final int STATUS_ERROR = -1;
    public static final String HELP_INDENT = "  ";

    private final String[] args;

    /**
     * CMD Constructor
     *
     * @param args - CMD Args
     */
    public CMD(String[] args) {
        this.args = args;
    }

    /**
     * Runs the Command
     */
    public void runCMD() {
        System.out.println(
                Config.PROGRAM_NAME + " - " + Config.VERSION + " by " + Const.CREATOR + " | Command-Line Version");
        processArgs("", "/storage/emulated/0/AppProjects/test/www/", "/storage/emulated/0/AppProjects/test/result/");
    }

    /**
     * Process Command-Line Arguments
     */
    private void processArgs(String... args) {
        new Decrypt().run(args);
    }

}
