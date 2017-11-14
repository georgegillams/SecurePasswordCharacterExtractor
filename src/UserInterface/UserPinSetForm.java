package UserInterface;

import PasswordManagement.EncryptedPasswordStorage;
import javafx.application.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPinSetForm {
    private JPasswordField passwordField1;
    private JPanel panel1;
    private JButton setUserPinButton;

    public void initialise() {
        JFrame frame = new JFrame("User Pin Set");
        frame.setContentPane(panel1);
        frame.setSize(400, 88);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public UserPinSetForm() {
        setUserPinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncryptedPasswordStorage.setUserPinOnFile(new String(passwordField1.getPassword()));
                System.exit(0);
            }
        });
    }

}
