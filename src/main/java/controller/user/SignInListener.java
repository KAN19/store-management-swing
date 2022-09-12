package controller.user;

import view.SignInGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInListener implements ActionListener {

    private final SignInGUI userDiaLog;

    public SignInListener(SignInGUI jDialog) {
        this.userDiaLog = jDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userDiaLog.signIn();
        userDiaLog.disposeGUI();
    }
}
