package view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class WelcomeGUI extends JFrame {
    private JButton goButton;
    private JPanel mainPanel;

    public WelcomeGUI() {
        this.add(mainPanel);
        this.setVisible(true);
        this.setSize(400, 400);
        this.setTitle("Welcome!");
        this.setLocationRelativeTo(null);

        goButton.addActionListener(e -> {
            new MainStoreGUI();
            disposeGUI();
        });
    }

    public void disposeGUI() {
        this.dispose();
    }
}
