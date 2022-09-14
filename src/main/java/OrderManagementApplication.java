import domain.dao.CustomerDao;
import domain.dao.impl.CustomerDaoImpl;
import domain.model.Customer;
import domain.model.Product;
import helper.DbConnection;
import presentation.Menu;

import java.util.List;

public class OrderManagementApplication {

    public static void main(String[] args) {
        connectToDatabase();
        CustomerDao customerDao = new CustomerDaoImpl();
//        List<Customer> customersFromFiles = customerDao.getCustomersFromFiles();
//        customersFromFiles.forEach(System.out::println);

        new Menu();
    }

    public static void connectToDatabase() {
        DbConnection.init("jdbc:sqlite:src/main/resources/OrderManagementSystem.db");
    }

}
