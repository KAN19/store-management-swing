package controller.user;

import view.RegisterGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener {
    private final RegisterGUI registerDialog;

    public RegisterListener(RegisterGUI registerDialog) {
        this.registerDialog = registerDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (registerDialog.validateInput()) {
            registerDialog.register();
            registerDialog.disposeGUI();
        }
    }
}
