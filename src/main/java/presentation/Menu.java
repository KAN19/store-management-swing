package presentation;

import domain.dao.CustomerDao;
import domain.dao.ProductDao;
import domain.model.Customer;
import domain.model.Product;
import helper.DIContainer;
import helper.Validator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final CustomerDao customerDao = DIContainer.getCustomerDao();
    private final ProductDao productDao = DIContainer.getProductDao();

    public Menu() {
        displayWelcomeScreen();
    }

    public void displayWelcomeScreen() {
        System.out.println(
                "|==============================================================|");
        System.out.println(
                "|                   COSC2081 GROUP ASSIGNMENT                  |");
        System.out.println(
                "|                 STORE ORDER MANAGEMENT SYSTEM                |");
        System.out.println(
                "|                     Instructor: Mr. Minh Vu                  |");
        System.out.println(
                "|                      S3777228, Le Anh Khoa                   |");
        System.out.println(
                "|                      S3926005, Vu Phan Anh                   |");
        System.out.println(
                "|                      S3914486, Nguyen Ky Anh                 |");
        System.out.println(
                "===============================================================|");

        waitUntilPressKey();
        displayMainMenu();
    }

    public void displayMainMenu() {
        int option = 0;
        do {
            System.out.println("");
            System.out.println(
                    "|==============================   Order Management System  ================================|");
            System.out.println(
                    "|(1) Customer register                                                                     |");
            System.out.println(
                    "|(2) Login                                                                                 |");
            System.out.println(
                    "|(3) View user information                                                                 |");
            System.out.println(
                    "|(4) View products                                                                         |");
            System.out.println(
                    "|(5) Search product by category                                                            |");
            System.out.println(
                    "|(6) Exit                                                                                  |");
            System.out.println(
                    "===========================================================================================|");

            System.out.print("Your selection: ");
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter valid option!");
            }
            scanner.nextLine();

            System.out.println();
            processOption(option);
            System.out.println("\n\n");

        } while (option != 6);
    }

    private void processOption(int option) {
        switch (option) {
            case 1: {
                System.out.println(
                        "=========================   CUSTOMER REGISTER   ==========================");
                this.customerRegister();
                this.waitUntilPressKey();
                break;
            }
            case 2:
                System.out.println(
                        "==========================   LOGIN  ===========================");
                this.customerLogin();
                this.waitUntilPressKey();
                break;
            case 3:
                System.out.println(
                        "=======================   VIEW CUSTOMER INFORMATION   =======================");
                this.displayCurrentCustomer();
                this.waitUntilPressKey();
                break;
            case 4:
                System.out.println(
                        "==========================   VIEW PRODUCTS  ========================");
                this.displayAllProducts();
                this.waitUntilPressKey();
                break;
            case 5:
                System.out.println(
                        "========================  SEARCH PRODUCT BY CATEGORY  ========================");
                System.out.print("Enter category: ");
                String searchingCategory = scanner.nextLine();
                this.displayProductsByCategory(searchingCategory);
                this.waitUntilPressKey();
                break;
            default:
                break;
        }
    }

    private void customerRegister(){
        Validator validator = new Validator();
        String username, password, fullName, address, phoneNumber;

        boolean validUsername = true;
        boolean validPhoneNumber = true;

        do {
            System.out.print("Username: ");
            username = scanner.nextLine();
            validUsername = validator.isValidUsername(username);
            if (!validUsername) {
                System.out.println("Invalid username!");
            }
        } while (!validUsername);

        do {
            System.out.print("Password: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Invalid password!");
            }
        } while (password.isEmpty());

        do {
            System.out.print("Full name: ");
            fullName = scanner.nextLine();
            if (fullName.isEmpty()) {
                System.out.println("Invalid name!");
            }
        } while (fullName.isEmpty());

        do {
            System.out.print("Address: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Invalid Address!");
            }
        } while (address.isEmpty());

        do {
            System.out.print("Phone number: ");
            phoneNumber = scanner.nextLine();
            validPhoneNumber = !phoneNumber.isEmpty() && phoneNumber.matches("\\d+");
            if (!validPhoneNumber) {
                System.out.println("Invalid phone number!");
            }
        } while (!validPhoneNumber);

        Customer newCustomer = new Customer(
                UUID.randomUUID().toString().substring(0, 5),
                fullName,
                address,
                phoneNumber,
                "Regular",
                username,
                password
        );

        if (customerDao.register(newCustomer)) {
            System.out.println("Register successfully");
        } else {
            System.out.println("Error! Something went wrong!");
        }
    }

    private void waitUntilPressKey() {
        System.out.println("\n--- Enter to continue");
        scanner.nextLine();
    }

    private void customerLogin() {
        System.out.print("Username:");
        String username = scanner.nextLine();
        System.out.print("password:");
        String password = scanner.nextLine();

        Customer loginCustomer = customerDao.login(username, password);

        if (loginCustomer == null) {
            System.out.println("Invalid username or password!");
        } else {
            System.out.println("Login successfully!");
            System.out.println("Hello " + loginCustomer.getUsername());
            DIContainer.setCurrentUser(loginCustomer);
        }
    }

    private void displayCurrentCustomer() {
        if (DIContainer.getCurrentUser() == null) {
            System.out.println("Please login before using this feature!");
        } else {
            System.out.println(
                    "ID: " + DIContainer.getCurrentUser().getCustomerId() + "\n"
                            + "Username: " + DIContainer.getCurrentUser().getUsername() + "\n"
                            + "FullName: " + DIContainer.getCurrentUser().getFullName() + "\n"
                            + "PhoneNumber: " + DIContainer.getCurrentUser().getPhoneNumber() + "\n"
                            + "Address: " + DIContainer.getCurrentUser().getAddress() + "\n"
                            + "TypeOfMember: " + DIContainer.getCurrentUser().getTypeOfMember() + "\n"
            );
        }
    }

    private void displayAllProducts() {
        System.out.format("%15s %15s %15s %15s %n", "ID", "Title", "Price", "Category");
        Object[][] productTable = convertListProductsToTableData(productDao.getAllProductsFromFiles());
        for (final Object[] row : productTable) {
            System.out.format("%15s %15s %15s %15s %n", row);
        }
    }

    private void displayProductsByCategory(String category) {
        List<Product> productsByCategory = productDao.getProductsByCategory(category);

        if (productsByCategory.isEmpty()) {
            System.out.println("No product found!");
        }

        System.out.format("%15s %15s %15s %15s %n", "ID", "Title", "Price", "Category");
        Object[][] productTable = convertListProductsToTableData(productsByCategory);
        for (final Object[] row : productTable) {
            System.out.format("%15s %15s %15s %15s %n", row);
        }
    }

    private Object[][] convertListProductsToTableData(List<Product> allProducts) {

        if (allProducts == null) {
            return null;
        }

        Object[][] data = new Object[allProducts.size()][];

        for (int i = 0; i < allProducts.size(); i++) {
            String id = allProducts.get(i).getProductId();
            String productName = allProducts.get(i).getProductName();
            String price = String.format("%,.0f", allProducts.get(i).getPrice());
            String category = allProducts.get(i).getCategory();

            Object[] objects = {id, productName, price, category};

            data[i] = objects;
        }
        return data ;
    }
}
