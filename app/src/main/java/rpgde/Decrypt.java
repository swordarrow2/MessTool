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
	 * Runs the Command
	 *
	 * @param args - Command-Line commands
	 */
	public void run(String[] args) {

		// Set Path to Project
		pathToProject = args[1];
		// Set Output-Dir
		try {
			outputDir = args[2];
		} catch (ArrayIndexOutOfBoundsException arEx) {
			outputDir = Config.DEFAULT_OUTPUT_DIR;
		}

		System.out.println("Set Output-Dir to: \"" + outputDir + "\"");

		if (args.length >= 4)
			verifyDir = Boolean.parseBoolean(args[3]);
		if (args.length >= 5)
			ignoreFakeHeader = Boolean.parseBoolean(args[4]);
		if (args.length >= 6) {
			if (!args[5].toLowerCase().equals("auto")) {
				key = args[5].toLowerCase();
			}
		}
		if (args.length >= 7) {
			headerLen = Integer.parseInt(args[6]);

			// Ensure headerLen is at least 1 else default
			if (headerLen < 1)
				headerLen = Decrypter.DEFAULT_HEADER_LEN;
		}
		if (args.length >= 8)
			signature = args[7].trim().toLowerCase();
		if (args.length >= 9)
			version = args[8].trim().toLowerCase();
		if (args.length >= 10)
			remain = args[9].trim().toLowerCase();

		handleFiles();
	}

	/**
	 * Handles Files to decrypt
	 */
	private void handleFiles() {
		try {
			RPG_Project rpgProject = new RPG_Project(pathToProject, verifyDir);
			Decrypter decrypter;

			if (key == null) {
				decrypter = new Decrypter();
			} else {
				decrypter = new Decrypter(key);
			}

			rpgProject.setOutputPath(outputDir);
			decrypter.setIgnoreFakeHeader(ignoreFakeHeader);
			decrypter.setHeaderLen(headerLen);
			decrypter.setSignature(signature);
			decrypter.setVersion(version);
			decrypter.setRemain(remain);
			rpgProject.decryptFilesCmd(decrypter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
