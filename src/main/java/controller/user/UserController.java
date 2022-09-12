package controller.user;

import view.MainStoreGUI;
import view.SignInGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserController implements ActionListener {

    private final SignInGUI userDiaLog;

    public UserController(SignInGUI jDialog) {
        this.userDiaLog = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "signIn")) {
            userDiaLog.signIn();
            userDiaLog.disposeGUI();
        }
    }
}
