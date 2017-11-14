package PasswordManagement;

import Application.Context;
import Application.FileConstants;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class EncryptedPasswordStorage {

    private static Key key;

    private static final byte[] salt = {
            (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
            (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17
    };

    public static void storePassword(String uuid, String password) {

        char[] characters = password.toCharArray();

        int characterNumber = 0;
        for (char c : characters                    ) {
            characterNumber += 1;
            String fileName = getFilenameString(uuid, characterNumber);
            writeBytesToFile(encryptString("" + c), fileName);
        }
    }

    private static String getFilenameString(String uuid, int characterNumber) {
        return FileConstants.applicationDataDirectory + uuid + "_" + characterNumber;
    }

    private static byte[] applyCypher(Cipher cipher, byte[] bytes)  {
        try {
            return cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encryptString(String value) {
        Cipher cipher = generateCipher(Cipher.ENCRYPT_MODE, (FileConstants.encryptionKey + Context.getUserProvidedPin()).toCharArray() );
        return applyCypher(cipher, value.getBytes());
    }

    private static void writeBytesToFile(byte[] value, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(value);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static char getCharacter(String uuid, int characterNumber) {
            String fileName = getFilenameString(uuid, characterNumber);

            byte[] fileContent = readFileToBytes(fileName);
            String decryptedFileContents = decryptBytes(fileContent);

            return decryptedFileContents.toCharArray()[0];
    }

    private static byte[] readFileToBytes(String fileName) {
        try {
        File file = new File(fileName);
        FileInputStream fin = new FileInputStream(file);
        byte fileContent[] = new byte[(int) file.length()];
        fin.read(fileContent);
            return fileContent;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decryptBytes(byte[] value) {
        Cipher cipher = generateCipher(Cipher.DECRYPT_MODE, (FileConstants.encryptionKey + Context.getUserProvidedPin()).toCharArray() );
        try {
        byte[] textDecrypted =  applyCypher(cipher, value);
            return new String(textDecrypted, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Cipher generateCipher(int cipherMode, char[] encryptionKey) {
        Cipher cipher = null;
        try {
        PBEKeySpec keySpec = new PBEKeySpec(encryptionKey);
        key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
        cipher = Cipher.getInstance("PBEWithMD5AndDES");
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 42);
            cipher.init(cipherMode, key, pbeParamSpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    public static void deleteEncryptedFiles(String uuid) {
        final File folder = new File(FileConstants.applicationDataDirectory);
        final File[] files = folder.listFiles( new FilenameFilter() {
            @Override
            public boolean accept( final File dir,
                                   final String name ) {
                return name.contains(uuid);
            }
        } );
        for ( final File file : files ) {
            if ( !file.delete() ) {
                System.err.println( "Can't remove " + file.getAbsolutePath() );
            }
        }
    }

    public static void setUserPinOnFile(String newPin){
        byte[] hash = generateHash(newPin);
        writeBytesToFile(hash, FileConstants.userPinFileName);
    }

    public static byte[] getUserPinHashFromFile(){
        return readFileToBytes(FileConstants.userPinFileName);
    }

    public static boolean checkUserPinAgainstFile(String userProvidedPin){
        return Arrays.equals(getUserPinHashFromFile(), generateHash(userProvidedPin));
    }

    private static byte[] generateHash(String s) {
        try{
            MessageDigest  messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.getBytes());
            return new String(messageDigest.digest()).getBytes();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
