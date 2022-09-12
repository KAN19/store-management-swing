package controller;

import view.MainStoreGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarController implements ActionListener {

    private final MainStoreGUI mainStoreGUI;

    public MenuBarController(MainStoreGUI mainStoreGUI) {
        this.mainStoreGUI = mainStoreGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        mainStoreGUI.userSignIn();
    }

}
