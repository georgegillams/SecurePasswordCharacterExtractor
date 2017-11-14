package UserInterface;

import Application.Context;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPasswordForm {
    private final MainApplicationForm mainApplicationForm;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton addButton;
    private JPanel panel1;
    private JFrame frame;

    public RegisterPasswordForm(MainApplicationForm mainApplicationForm) {
        this.mainApplicationForm = mainApplicationForm;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userFirendlyName = textField1.getText();
                String password = new String(passwordField1.getPassword());
                Context.getPasswordManager().addPassword(userFirendlyName, password);
                mainApplicationForm.updatePasswordIdentifierList();
                frame.dispose();
            }
        });
    }

    public void initialise() {
        frame = new JFrame("Register new Password");
        frame.setContentPane(panel1);
        frame.setSize(1000, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
