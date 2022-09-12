package controller;

import view.MainStoreGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuBarController implements ActionListener {

    private final MainStoreGUI mainStoreGUI;

    public MenuBarController(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (Objects.equals(e.getActionCommand(), "displaySignInGUI")) {
            mainStoreGUI.userSignIn();
        }

        if (Objects.equals(e.getActionCommand(), "displayRegisterGUI")) {
            mainStoreGUI.userRegister();
        }

        if (Objects.equals(e.getActionCommand(), "viewInformation")) {
            mainStoreGUI.viewUserInformation();
        }
    }

}
