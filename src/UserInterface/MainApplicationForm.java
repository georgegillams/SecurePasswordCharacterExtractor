package UserInterface;

import Application.ApplicationConstants;
import Application.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplicationForm {

    private JFrame frame;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton addPasswordButton;
    private JLabel label1;
    private JButton removePasswordButton;
    private boolean deleteReady;

    public MainApplicationForm() {

        addPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new RegisterPasswordForm(getFormInstance())).initialise();
            }
        });

        removePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedIndex() < 0) {
                    deleteReady = false;
                    return;
                }
                String selectedPassword = comboBox1.getSelectedItem().toString();
                if(selectedPassword == null || selectedPassword.isEmpty()){
                    deleteReady = false;
                    return;
                }

                if(deleteReady){
                    Context.getPasswordManager().removePassword(selectedPassword);
                    deleteReady = false;
                    updatePasswordIdentifierList();
                }else{
                    deleteReady = true;
                    setDeleteButtonColour();
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateExtractedCharacters();
            }
        });

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateExtractedCharacters();
            }
        });

    }

    public MainApplicationForm getFormInstance() {
        return this;
    }

    public void initialise() {
        frame = new JFrame("Secure Password-character Extractor");
        frame.setContentPane(panel1);
        frame.setSize(1000, 136);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        label1.setText(ApplicationConstants.defaultPasswordOutput);
        updatePasswordIdentifierList();
    }

    public void updatePasswordIdentifierList() {
        deleteReady = false;
        setDeleteButtonColour();

            comboBox1.removeAllItems();
        for (String p:Context.getPasswordManager().getPasswordNames()             ) {
            comboBox1.addItem(p);
        }
        invalidate();
    }

    private void setDeleteButtonColour() {
            removePasswordButton.setOpaque(true);
        if(deleteReady){
            removePasswordButton.setBackground(Color.RED);
            removePasswordButton.setForeground(Color.RED);
        }else{
            removePasswordButton.setBackground(Color.WHITE);
            removePasswordButton.setForeground(Color.BLACK);
        }
    }

    private void invalidate() {
        frame.invalidate();
        panel1.invalidate();
        panel1.repaint();
        frame.repaint();
        panel1.revalidate();
        frame.revalidate();
        panel1.validate();
        frame.validate();
    }

    private void UpdateExtractedCharacters() {
        if(comboBox1.getSelectedIndex() < 0) {
            return;
        }
            String userFriendlyName = comboBox1.getSelectedItem().toString();
            int[] characterNumbers = getCharacterNumbers(textField1.getText());
            StringBuilder sb = new StringBuilder();
            for (int i : characterNumbers) {
                if(i > 0) {
                    sb.append(Context.getPasswordManager().getCharacter(userFriendlyName, i));
                }
            }
            String output = sb.toString();
            if(output.isEmpty()){
                output = ApplicationConstants.defaultPasswordOutput;
            }
            label1.setText(output);
        invalidate();
    }

    public int[] getCharacterNumbers(String text) {
        String[] splitString = text.split(" ");

        int[] characterNumbers = new int[splitString.length];

        int characterNumberCount = 0;
        for (String s:splitString             ) {
            try {
                if(!s.isEmpty()) {
                    characterNumbers[characterNumberCount] = Integer.parseInt(s);
                    characterNumberCount += 1;
                }
            } catch (NumberFormatException e) {
//simply continue...
           }
        }

        return characterNumbers;
    }
}
