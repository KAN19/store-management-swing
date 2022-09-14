package domain.dao.impl;

import domain.dao.CustomerDao;
import domain.model.Customer;
import domain.model.dto.UserDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public UserDto signIn(String username, String password) {

        return null;
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
        return false;
    }

}
