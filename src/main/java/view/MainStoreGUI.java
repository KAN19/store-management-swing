package view;

import controller.MenuBarController;
import domain.model.dto.ProductDto;
import helper.DIContainer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MainStoreGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox categoryComboBox;
    private JTable productTable;
    private JScrollPane jScrollPane;
    private JButton findByCategoryButton;
    private JTextField detailProductIdField;
    private JTextField detailProductNameField;
    private JTextField detailProductPriceField;
    private JTextField detailProductCategoryField;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem signIn;
    private JMenuItem register;
    private JMenuItem viewInformation;
    private MenuBarController menuBarController;

    public MainStoreGUI() {
        initGUI();
        createMenuBar();
        createTable();
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

    public void createTable() {

        Object[][] data = {
                {1, "MSI Modern 14", "140000", "Laptop"},
                {2, "Lenovo ABC", "190000", "Laptop"},
        };

        productTable.setModel(new DefaultTableModel(data, new String[] {
                "Id",
                "Product Name",
                "Price",
                "Category"
        }));
    }

    public List<ProductDto> getProductsList() {
        return null;
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
