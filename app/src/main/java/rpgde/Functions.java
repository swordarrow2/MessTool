package rpgde;

/**
 * @author Peter Dragicevic
 */
public class Functions {
    /**
     * Returns a boolean Value depending of the String-Content
     *
     * @param str - String to Check
     * @return - true if string contains "true" or "1" else false
     */
    public static boolean strToBool(String str) {
        if (str == null) {
            str = "";
        }
        str = str.toLowerCase();

        return !str.equals("0") || str.equals("true");
    }
}
