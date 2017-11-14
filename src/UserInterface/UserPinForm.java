package UserInterface;

import Application.Context;
import PasswordManagement.EncryptedPasswordStorage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPinForm {
    private JPasswordField passwordField1;
    private JPanel panel1;
    private JButton checkUserPinButton;
    private JFrame frame;

    public void initialise() {
         frame = new JFrame("User Pin Check");
        frame.setContentPane(panel1);
        frame.setSize(400, 88);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public UserPinForm() {
        checkUserPinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    passwordField1.setForeground(Color.BLACK);
                String userPorvidedPassword = new String(passwordField1.getPassword());
                if(EncryptedPasswordStorage.checkUserPinAgainstFile(userPorvidedPassword)){

                    Context.INSTANCE().initialise();
                    Context.setUserProvidedPin(userPorvidedPassword);

                    (new MainApplicationForm()).initialise();
                    frame.dispose();
                }else{
                    passwordField1.setForeground(Color.RED);
                }
            }
        });
    }

}
