package Application;

import PasswordManagement.PasswordManager;

public class Context {

    //region static instance
    private static Context contextInstance = null;
    private static String userPin = "";

    public static Context INSTANCE(){
        if(contextInstance == null){
            contextInstance = new Context();
        }
        return contextInstance;
    }
    //endregion

    private PasswordManager passwordManager;
    private boolean isInitialised = false;

    public static String getUserProvidedPin() {
        return userPin;
    }

    public static void setUserProvidedPin(String providedPin) {
        userPin = providedPin;
    }

    public void initialise() {
        passwordManager = new PasswordManager();
        isInitialised = true;
    }

    public static PasswordManager getPasswordManager(){
        return INSTANCE().passwordManager;
    }

    public static boolean isInitialised(){
        return INSTANCE().isInitialised;
    }

}
