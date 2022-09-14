package domain.dao.impl;

import domain.dao.CustomerDao;
import domain.model.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringJoiner;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer login(String username, String password) {
        List<Customer> customersFromFiles = this.getCustomersFromFiles();

        Customer responseCustomer = customersFromFiles.stream()
                .filter(customer -> customer.getUsername().equals(username)
                        && customer.getPassword().equals(password))
                .findAny()
                .orElse(null);

        return responseCustomer;
    }

    @Override
    public boolean register(Customer customer) {
        String defaultUserFileName = "src/main/resources/customers.txt";

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(
                    new File(defaultUserFileName),
                    true));
            writer.println();

            StringJoiner sj = new StringJoiner(",");
            sj.add(customer.getCustomerId());
            sj.add(customer.getFullName());
            sj.add(customer.getAddress());
            sj.add(customer.getPhoneNumber());
            sj.add(customer.getTypeOfMember());
            sj.add(customer.getUsername());
            sj.add(customer.getPassword());
            writer.println(sj);
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return false;
    }

    @Override
    public boolean findUserByUsername(String username) {
        List<Customer> customersFromFiles = getCustomersFromFiles();
        return customersFromFiles
                .stream()
                .anyMatch(customer -> customer.getUsername().equals(username));
    }

}
