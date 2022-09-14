package presentation;

import domain.dao.CustomerDao;
import domain.dao.ProductDao;
import domain.model.Product;
import domain.model.dto.ProductDto;
import helper.DIContainer;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final CustomerDao customerDao = DIContainer.getCustomerDao();
    private final ProductDao productDao = DIContainer.getProductDao();

    public Menu() {
        displayMainMenu();
    }

    public void displayWelcomeScreen() {
            System.out.println("Hello welcome screen ne");
            scanner.nextInt();
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

                scanner.nextLine();
                break;
            }
            case 2:
                System.out.println(
                        "==========================   LOGIN  ===========================");
                scanner.nextLine();
                break;
            case 3:
                System.out.println(
                        "=======================   VIEW CUSTOMER INFORMATION   =======================");

                scanner.nextLine();
                break;
            case 4:
                System.out.println(
                        "==========================   VIEW PRODUCTS  ========================");
                this.displayAllProducts();
                System.out.println("\n--- Enter to continue");
                scanner.nextLine();
                break;
            case 5:
                System.out.println(
                        "========================  SEARCH PRODUCT BY CATEGORY  ========================");
                System.out.print("Enter category ");
                String searchingCategory = scanner.nextLine();
                this.displayProductsByCategory(searchingCategory);
                System.out.println("\n--- Enter to continue");
                scanner.nextLine();
                break;
            default:
                break;
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
