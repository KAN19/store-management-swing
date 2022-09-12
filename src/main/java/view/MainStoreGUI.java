package view;

import controller.mainStoreGUI.ComboBoxListener;
import controller.mainStoreGUI.MenuBarListener;
import controller.mainStoreGUI.ProductTableListener;
import domain.dao.CategoryDao;
import domain.dao.ProductDao;
import domain.model.Category;
import domain.model.dto.ProductDto;
import helper.DIContainer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;

public class MainStoreGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox categoryComboBox;
    private JTable productTable;
    private JScrollPane jScrollPane;
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
    private ComboBoxListener comboBoxListener;

    private ListSelectionModel listSelectionModel;

    private Object[][] productData;

    private final ProductDao productDao = DIContainer.getProductDao();

    private final CategoryDao categoryDao = DIContainer.getCategoryDao();


    public MainStoreGUI() {
        initGUI();
        createMenuBar();
        createCategoryComboBox();
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

        comboBoxListener = new ComboBoxListener(this);

        listSelectionModel = productTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productListener = new ProductTableListener(this);

    }

    public void createTable() {
        productData = this.convertProductsToTableData(productDao.getAllProducts());
        productTable.setModel(new DefaultTableModel(productData, new String[] {
                "Id",
                "Product Name",
                "Price",
                "Category"
        }));
    }

    public void createCategoryComboBox() {
        categoryComboBox.addItem(new Category(0L, "All"));
        getCategories().forEach(item -> {
            categoryComboBox.addItem(item);
        });
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryDao.getCategories();
        return categories;
    }

    public Object[][] convertProductsToTableData(List<ProductDto> allProducts) {

        if (allProducts == null) {
            return null;
        }

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

       categoryComboBox.addActionListener(comboBoxListener);
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
        this.setSize(800, 500);
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

    public void filterProductsByCategory(Category category) {
        Object[][] data = null;
        if (Objects.equals(category.getCategoryName(), "All")) {
            data = convertProductsToTableData(productDao.getAllProducts());
        } else {
            data = convertProductsToTableData(productDao.getProductsByCategory(category));
        }

        productTable.setModel(new DefaultTableModel(data, new String[] {
                "Id",
                "Product Name",
                "Price",
                "Category"
        }));
    }


    public JTable getProductTable() {
        return productTable;
    }



}
