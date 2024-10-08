package com.meng.messtool.modules.rpgdecry;

import java.util.*;

/**
 * @author Peter Dragicevic
 */
class Finder {
    /**
     * Constructor
     */
    private Finder() {
        // VOID - This is a Static-Class
    }

    /**
     * Search all know places for the System-File
     *
     * @param projectDir - Directory of the Project
     * @return - System-File-Object if found else null
     */
    static RPG_File findSystemFile(String projectDir) {
        String[] filePaths = new String[]{"data/System.json",
                "www/data/System.json"};

        return findFromArray(filePaths, projectDir);
    }

    /**
     * Finds the Project-File
     *
     * @param projectDir - Directory were to search
     * @return - Project File or null if not found
     */
    static RPG_File findProjectFile(String projectDir) {
        String[] potentialPaths = new String[]{"Game.rpgproject", "game.rmmzproject"};

        return findFromArray(potentialPaths, projectDir);
    }

    /**
     * Check if Encryption-Key name is may different from given - Tests all know
     * Encryption-Key Names
     *
     * @param systemFile - System.json File
     * @return - Key-Name or null if not found
     */
    static String testEncryptionKeyNames(RPG_File systemFile) {
        String[] keyNames = new String[]{"encryptionKey"};
        Decrypter d = new Decrypter();
        String result = null;

        for (String keyName : keyNames) {
            try {
                d.detectEncryptionKeyFromJson(systemFile, keyName);

                if (d.getDecryptCode() != null) {
                    result = keyName;
                    break;
                }
            } catch (Exception e) {
                // Void
            }
        }

        return result;
    }

    /**
     * Checks if there is a RPG-Maker file within the Directory
     *
     * @param dir - Path to the Directory
     * @return - true if a File was found else false
     */
    static boolean verifyRPGDir(String dir) {
        if (dir == null) {
            Exception e = new Exception("Dir can't be null!");
            e.printStackTrace();
            return false;
        }

        ArrayList<java.io.File> mainDirFiles = RPG_File.readDirFiles(new java.io.File(dir), false);
        String[] rpgGameCommonFiles = new String[]{"Game.exe", // Mostly with an other name...
                "Game.rpgproject", // Mostly not in the Directory
                "d3dcompiler_47.dll", "ffmpegsumo.dll", "icudtl.dat", "libEGL.dll", "libGLESv2.dll", "nw.pak",
                "package.json", "pdf.dll", "resources.pak"};

        // Check if a File is in there
        for (java.io.File currentFile : mainDirFiles) {
            for (String rpgFile : rpgGameCommonFiles) {
                if (rpgFile.equals(currentFile.getName()))
                    return true;
                else if (rpgFile.toLowerCase().equals(currentFile.getName().toLowerCase()))
                    return true;
            }
        }

        return false;
    }

    /**
     * Finds a File from the given Array
     *
     * @param filePathArray - The array with potential hits
     * @param mainPath      - Main path which is added in front of all files (aka
     *                      search in this dir)
     * @return - Target File or null if not found
     */
    private static RPG_File findFromArray(String[] filePathArray, String mainPath) {
        RPG_File targetFile = null;

        for (String filePath : filePathArray) {
            java.io.File fileIo = new java.io.File(mainPath + filePath);

            if (fileIo.exists() && !fileIo.isDirectory()) {
                try {
                    targetFile = new RPG_File(mainPath + filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return targetFile;
            }
        }

        return null;
    }
}
