package PasswordManagement;

import Application.FileConstants;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;

public class RegisteredPasswords {

    ArrayList<Pair<String, String>> passwords;

    public RegisteredPasswords() {
        passwords = new ArrayList<>();
        readPasswordsFromFile();
    }

    private void readPasswordsFromFile() {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(FileConstants.passwordIdentifiersFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split(FileConstants.passwordFileDelimiter);
                    if (split.length > 1) {
                        passwords.add(new Pair<>(split[0], split[1]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String registerNewPassword(String userFriendlyName) {
        UUID newUuid = UUID.randomUUID();
        passwords.add(new Pair<>(userFriendlyName, newUuid.toString()));
        updateFile();
        return newUuid.toString();
    }

    public String removePassword(String userFriendlyName) {
        final String[] removedUuid = {""};
        passwords.removeIf(new Predicate<Pair<String, String>>() {
            @Override
            public boolean test(Pair<String, String> stringStringPair) {
                if (stringStringPair.getKey().equals(userFriendlyName)) {
                    removedUuid[0] = stringStringPair.getValue();
                    return true;
                }
                return false;
            }
        });
        updateFile();
        return removedUuid.length > 0 ? removedUuid[0] : "";
    }

    private void updateFile() {
        try {
            PrintWriter writer = new PrintWriter(FileConstants.passwordIdentifiersFile, "UTF-8");
            for (Pair passwordPair : passwords) {
                writer.println(passwordPair.getKey() + FileConstants.passwordFileDelimiter + passwordPair.getValue());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUuid(String userFriendlyName) {
        for (Pair<String , String> pair: passwords             ) {
            if(pair.getKey().equals(userFriendlyName)){
                return pair.getValue();
            }
        }
        return null;
    }

    public String[] getPasswordNames() {
        String[] names = new String[passwords.size()];
        int i = 0;
        for (Pair<String, String> pair:passwords             ) {
            names[i] = pair.getKey();
            i += 1;
        }
        return names;
    }
}
