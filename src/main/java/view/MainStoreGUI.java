package view;

import controller.mainStoreGUI.MenuBarListener;
import controller.mainStoreGUI.ProductTableListener;
import domain.dao.ProductDao;
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
    private MenuBarListener menuBarListener;
    private ProductTableListener productListener;

    private ListSelectionModel listSelectionModel;

    private Object[][] productData;

    private final ProductDao productDao = DIContainer.getProductDao();


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

        menuBarListener = new MenuBarListener(this);

        listSelectionModel = productTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productListener = new ProductTableListener(this);

    }

    public void createTable() {
        productData = this.getProductsListForTable();
        productTable.setModel(new DefaultTableModel(productData, new String[] {
                "Id",
                "Product Name",
                "Price",
                "Category"
        }));
    }

    public Object[][] getProductsListForTable() {

        List<ProductDto> allProducts = productDao.getAllProducts();

        Object[][] data = new Object[allProducts.size()][];

        for (int i = 0; i < allProducts.size(); i++) {
            Long id = allProducts.get(i).getId();
            String productName = allProducts.get(i).getProductName();
            String price = String.format("%,.0f", allProducts.get(i).getPrice());
            String category = allProducts.get(i).getCategory().getCategoryName();

            Object[] objects = {id, productName, price, category};

            data[i] = objects;
        }
        return data ;
    }

    public void subscribeToController() {
       signIn.setActionCommand("displaySignInGUI");
       signIn.addActionListener(menuBarListener);

       register.setActionCommand("displayRegisterGUI");
       register.addActionListener(menuBarListener);

       viewInformation.setActionCommand("viewInformation");
       viewInformation.addActionListener(menuBarListener);

       listSelectionModel.addListSelectionListener(productListener);
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

    public void updateDetailProduct(int selectedRow) {
        detailProductIdField.setText(productData[selectedRow][0].toString());
        detailProductNameField.setText(productData[selectedRow][1].toString());
        detailProductPriceField.setText(productData[selectedRow][2].toString());
        detailProductCategoryField.setText(productData[selectedRow][3].toString());
    }

    public JTable getProductTable() {
        return productTable;
    }


}
