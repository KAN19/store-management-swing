package view;

import controller.MenuBarController;

import javax.swing.*;

public class MainStoreGUI extends JFrame {
    private JPanel mainPanel;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem signIn;
    private JMenuItem register;
    private MenuBarController menuBarController;

    public MainStoreGUI() {
        initGUI();
        createMenuBar();

        subscribeToController();
        displayGUI();
    }

    public void createMenuBar() {
        jMenu.add(signIn);
        jMenu.add(register);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
    }

    public void initGUI() {
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("User");
        signIn = new JMenuItem("Sign In");
        register = new JMenuItem("Register");

        menuBarController = new MenuBarController(this);
    }

    public void subscribeToController() {
       signIn.setActionCommand("signIn");
       signIn.addActionListener(menuBarController);

    }

    public void userSignIn() {
        SignInGUI.getSignInGUI(this);
    }

    public void displayGUI() {
        this.add(mainPanel);
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setTitle("Main store");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
