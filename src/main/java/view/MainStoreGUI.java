package view;

import controller.MenuBarController;
import helper.DIContainer;

import javax.swing.*;

public class MainStoreGUI extends JFrame {
    private JPanel mainPanel;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem signIn;
    private JMenuItem register;
    private JMenuItem viewInformation;
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
        jMenu.add(viewInformation);

        jMenuBar.add(jMenu);

        this.setJMenuBar(jMenuBar);
    }

    public void initGUI() {
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("User");
        signIn = new JMenuItem("Sign In");
        register = new JMenuItem("Register");

        viewInformation = new JMenuItem("View User");
        viewInformation.setEnabled(false);

        menuBarController = new MenuBarController(this);
    }

    public void subscribeToController() {
       signIn.setActionCommand("displaySignInGUI");
       signIn.addActionListener(menuBarController);

       register.setActionCommand("displayRegisterGUI");
       register.addActionListener(menuBarController);

       viewInformation.setActionCommand("viewInformation");
       viewInformation.addActionListener(menuBarController);
    }

    public void userSignIn() {
        SignInGUI.getSignInGUI(this);
    }

    public void userRegister() {
        RegisterGUI.getRegisterGUI(this);
    }

    public void viewUserInformation() {
        JOptionPane.showMessageDialog(this,
                "Username: " + DIContainer.getCurrentUser().getUsername() + "\n"
                + "FullName: " + DIContainer.getCurrentUser().getFullName() + "\n"
                + "PhoneNumber: " + DIContainer.getCurrentUser().getPhoneNumber() + "\n"
                + "TypeOfMember: " + DIContainer.getCurrentUser().getTypeOfMember() + "\n"
        );
    }

    public void enableViewInformationMenuItem() {
        viewInformation.setEnabled(true);
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
