package Application;

import javax.crypto.KeyGenerator;
import java.security.Key;

public class FileConstants {

    public static String applicationDataDirectory = "applicationData/";
    public static String passwordIdentifiersFile = applicationDataDirectory + "passwords.txt";
    public static String userPinFileName = applicationDataDirectory + "userPin.txt";
    public static String passwordFileDelimiter = "32a3b2da";

    public static String encryptionKey = "1bcde4993ed7410eaa8d90a74e75cdba";
}
