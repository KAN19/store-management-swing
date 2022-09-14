package view;

import controller.user.RegisterListener;
import domain.dao.CustomerDao;
import domain.model.Customer;
import helper.DIContainer;

import javax.swing.*;

public class RegisterGUI extends JDialog implements BaseGUI{
    private JTextField usernameField;
    private JPanel mainPanel;
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private static RegisterGUI objectInstance;

    private RegisterListener registerListener;

    private final MainStoreGUI mainStoreGUI;

    private final CustomerDao customerDao = DIContainer.getUserDao();

    private RegisterGUI(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
        subscribeToController();
        displayGUI();
    }

    public static RegisterGUI getRegisterGUI(MainStoreGUI mainStoreGUI) {
        if (objectInstance == null) {
            objectInstance = new RegisterGUI(mainStoreGUI);
        }
        objectInstance.setVisible(true);
        objectInstance.setAlwaysOnTop(true);
        objectInstance.requestFocus();
        return objectInstance;
    }

    public void register() {

    }

    public boolean validateInput() {
        if (usernameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (customerDao.findUserByUsername(usernameField.getText())) {
            JOptionPane.showMessageDialog(this, "Username has been taken!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fullNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (phoneNumberField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your phone number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Long.parseLong(phoneNumberField.getText());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid phone number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (String.valueOf(passwordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your password", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!String.valueOf(passwordField.getPassword())
                .equals(String.valueOf(confirmPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(this, "Your confirm password does not matched", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
    }

    @Override
    public void subscribeToController() {
        registerListener = new RegisterListener(this);
        registerButton.setActionCommand("register");
        registerButton.addActionListener(registerListener);
    }

    @Override
    public void displayGUI() {
        this.add(mainPanel);
        this.setVisible(true);
        this.setSize(350, 400);
        this.setTitle("Register");
        this.setLocationRelativeTo(mainStoreGUI);
    }


    @Override
    public void disposeGUI() {
        this.dispose();
        objectInstance = null;
    }
}
