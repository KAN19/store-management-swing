package view;

import controller.user.SignInListener;
import domain.dao.UserDao;
import domain.model.dto.UserDto;
import helper.DIContainer;

import javax.swing.*;

public class SignInGUI extends JDialog {
    private static SignInGUI objectInstance;
    private final MainStoreGUI mainStoreGUI;
    private final UserDao userDao = DIContainer.getUserDao();
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JPanel buttonPanel;
    private SignInListener signInListener;

    private SignInGUI(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
        subscribeToController();
        displayGUI();
    }

    public static SignInGUI getSignInGUI(MainStoreGUI mainStoreGUI) {
        if (objectInstance == null) {
            objectInstance = new SignInGUI(mainStoreGUI);
        }
        objectInstance.setVisible(true);
        objectInstance.setAlwaysOnTop(true);
        objectInstance.requestFocus();
        return objectInstance;
    }

    public boolean signIn() {

        UserDto userDto = userDao
                .signIn(usernameField.getText(),
                        String.valueOf(passwordField.getPassword()));

        if (userDto != null) {
            System.out.println("Sign in oke");
            JOptionPane.showMessageDialog(this, "Hello " + userDto.getUsername());
        } else {
            System.out.println("Sai mk hoac username");
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);

        }

        return false;
    }

    public void subscribeToController() {
        signInListener = new SignInListener(this);
        signInButton.setActionCommand("signIn");
        signInButton.addActionListener(signInListener);
    }

    public void displayGUI() {
        this.add(mainPanel);
        this.setVisible(true);
        this.setSize(300, 250);
        this.setTitle("Sign In");
        this.setLocationRelativeTo(mainStoreGUI);
    }

    public void disposeGUI() {
        this.dispose();
        objectInstance = null;
    }

}
