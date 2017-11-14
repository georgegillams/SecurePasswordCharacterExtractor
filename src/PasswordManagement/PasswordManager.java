package PasswordManagement;

public class PasswordManager {

    private RegisteredPasswords registeredPasswords;

    public PasswordManager(){
        registeredPasswords = new RegisteredPasswords();
    }

    public void addPassword(String userFriendlyName, String password){
        String uuid = registeredPasswords.registerNewPassword(userFriendlyName);
        EncryptedPasswordStorage.storePassword(uuid, password);
    }

    public void removePassword(String userFriendlyName){
        String uuid = registeredPasswords.removePassword(userFriendlyName);
        EncryptedPasswordStorage.deleteEncryptedFiles(uuid);
    }

    public char getCharacter(String userFriendlyName, int characterNumber){
        String uuid = registeredPasswords.getUuid(userFriendlyName);
        return EncryptedPasswordStorage.getCharacter(uuid, characterNumber);
    }

    public String[] getPasswordNames() {
        return registeredPasswords.getPasswordNames();
    }
}
