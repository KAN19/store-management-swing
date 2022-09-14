package domain.dao;

import domain.model.Product;
import domain.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface BaseDao { ;

    default List<Customer> getCustomersFromFiles()  {
        String defaultUserFileName = "src/main/resources/customers.txt";

        List<Customer> customers = new ArrayList<>();

        Scanner input = null;
        try {
            input = new Scanner(new File(defaultUserFileName));
            input.useDelimiter("-|\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(input.hasNext()) {
            String nextLine = input.nextLine();
            if (nextLine.isEmpty()) {
                continue;
            }
            if (nextLine.charAt(0) == '#') {
                continue;
            }
            String[] tokens = nextLine.split(",");
            Customer customer = new Customer();
            customer.setCustomerId(tokens[0].trim());
            customer.setFullName(tokens[1].trim());
            customer.setAddress(tokens[2].trim());
            customer.setPhoneNumber(tokens[3].trim());
            customer.setTypeOfMember(tokens[4].trim());
            customer.setUsername(tokens[5].trim());
            customer.setPassword(tokens[6].trim());

            customers.add(customer);
        }

        return customers;
    }

    default List<Product> getAllProductsFromFiles() {
        String defaultProductsFileName = "src/main/resources/items.txt";

        List<Product> products = new ArrayList<>();

        Scanner input = null;
        try {
            input = new Scanner(new File(defaultProductsFileName));
            input.useDelimiter("-|\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(input.hasNext()) {
            String nextLine = input.nextLine();
            if (nextLine.charAt(0) == '#') {
                continue;
            }
            String[] tokens = nextLine.split(",");
            Product product = new Product();
            product.setProductId(tokens[0].trim());
            product.setProductName(tokens[1].trim());
            product.setPrice(Double.parseDouble(tokens[2].trim()));
            product.setCategory(tokens[3].trim());

            products.add(product);
        }
        return products;
    }
}
