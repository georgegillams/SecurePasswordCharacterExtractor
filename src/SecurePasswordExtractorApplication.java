import Application.FileConstants;
import UserInterface.UserPinForm;
import UserInterface.UserPinSetForm;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecurePasswordExtractorApplication {

    public static void main(String[] args){
        setSystemLookAndFeel();

        //TODO Initialise GUIs
        if(!new File(FileConstants.passwordIdentifiersFile).exists()){
            //Delete all files to make sure we really are starting afresh...
            File applicationDataDirectory = new File(FileConstants.applicationDataDirectory);
            if(!applicationDataDirectory.exists()){
                applicationDataDirectory.mkdir();
            }

            for ( final File file : applicationDataDirectory.listFiles() ) {
                if ( !file.delete() ) {
                    System.err.println( "Can't remove " + file.getAbsolutePath() );
                }
            }

            //recreate the password-identifiers file
            try {
                new FileOutputStream(new File(FileConstants.passwordIdentifiersFile)).close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //setup a new user-pin
            (new UserPinSetForm()).initialise();
        }else{
            (new UserPinForm()).initialise();
        }



//        PasswordManagement.PasswordManager pm = Application.Context.getPasswordManager();
//        pm.addPassword("TSB", "123");
//System.out.print(pm.getCharacter("TSB", 1));
//System.out.print(pm.getCharacter("TSB", 2));
//System.out.print(pm.getCharacter("TSB", 3));
//        pm.removePassword("TSB");

    }

    private static void setSystemLookAndFeel() {
        try {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
    }
    catch (UnsupportedLookAndFeelException e) {
        // handle exception
    }
    catch (ClassNotFoundException e) {
        // handle exception
    }
    catch (InstantiationException e) {
        // handle exception
    }
    catch (IllegalAccessException e) {
        // handle exception
    }
    }

}
