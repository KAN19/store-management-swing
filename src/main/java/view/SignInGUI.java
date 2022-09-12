package view;

import com.sun.tools.javac.Main;
import controller.user.UserController;
import domain.dao.UserDao;
import domain.model.dto.UserDto;
import helper.DIContainer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;

public class SignInGUI extends JDialog {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JPanel buttonPanel;

    private UserController userController;
    private static SignInGUI objectInstance;

    private final MainStoreGUI mainStoreGUI;


    private final UserDao userDao = DIContainer.getUserDao();

    public static SignInGUI getSignInGUI(MainStoreGUI mainStoreGUI) {
        if (objectInstance == null) {
            objectInstance = new SignInGUI(mainStoreGUI);
        }
        objectInstance.setVisible(true);
        objectInstance.setAlwaysOnTop(true);
        objectInstance.requestFocus();
        return objectInstance;
    }

    private SignInGUI(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
        subscribeToController();
        displayGUI();
    }

    public boolean signIn() {
        try {
            UserDto userDto = userDao
                    .signIn(usernameField.getText(),
                            String.valueOf(passwordField.getPassword()));

            if (userDto!= null) {
                System.out.println("Sign in oke");
                JOptionPane.showMessageDialog(this, "Hello " + userDto.getUsername());
            } else {
                System.out.println("Sai mk hoac username");
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {
            System.out.println("loi roi");
            throw new RuntimeException(e);
        }
        return false;
    }

    public void subscribeToController() {
        userController = new UserController(this);
        signInButton.setActionCommand("signIn");
        signInButton.addActionListener(userController);
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
