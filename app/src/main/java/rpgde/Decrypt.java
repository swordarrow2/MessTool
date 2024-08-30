package rpgde;

class Decrypt {
    private String pathToProject;
    private String outputDir;

    private String key = null;
    private boolean verifyDir = false;
    private boolean ignoreFakeHeader = true;
    private int headerLen = Decrypter.DEFAULT_HEADER_LEN;
    private String signature = Decrypter.DEFAULT_SIGNATURE;
    private String version = Decrypter.DEFAULT_VERSION;
    private String remain = Decrypter.DEFAULT_REMAIN;


    /**
     * Handles Files to decrypt
     */
    public void handleFiles() {
        try {
            RPG_Project rpgProject = new RPG_Project(getProjectPath(), isVerifyDir());
            Decrypter decrypter;

            if (getKey() == null) {
                decrypter = new Decrypter();
            } else {
                decrypter = new Decrypter(getKey());
            }

            rpgProject.setOutputPath(getOutputPath());
            decrypter.setIgnoreFakeHeader(isIgnoreFakeHeader());
            decrypter.setHeaderLen(getHeaderLen());
            decrypter.setSignature(getSignature());
            decrypter.setVersion(getVersion());
            decrypter.setRemain(getRemain());
            rpgProject.decryptFilesCmd(decrypter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProjectPath() {
        return pathToProject;
    }

    public void setPathToProject(String pathToProject) {
        this.pathToProject = pathToProject;
    }

    public String getOutputPath() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isVerifyDir() {
        return verifyDir;
    }

    public void setVerifyDir(boolean verifyDir) {
        this.verifyDir = verifyDir;
    }

    public boolean isIgnoreFakeHeader() {
        return ignoreFakeHeader;
    }

    public void setIgnoreFakeHeader(boolean ignoreFakeHeader) {
        this.ignoreFakeHeader = ignoreFakeHeader;
    }

    public int getHeaderLen() {
        return headerLen;
    }

    public void setHeaderLen(int headerLen) {
        this.headerLen = headerLen;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

}
