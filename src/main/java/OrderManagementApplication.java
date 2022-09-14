import domain.dao.CustomerDao;
import domain.dao.impl.CustomerDaoImpl;
import domain.model.Customer;
import domain.model.Product;
import helper.DbConnection;

import java.util.List;

public class OrderManagementApplication {

    public static void main(String[] args) {
        connectToDatabase();
        CustomerDao customerDao = new CustomerDaoImpl();
//        List<Customer> customersFromFiles = customerDao.getCustomersFromFiles();
//        customersFromFiles.forEach(System.out::println);

        customerDao.register(new Customer("C007",
                "John Smith",
                "02 West Drive",
                "0424125598",
                "Regular", "ngueynkiet", "2222222"));
    }

    public static void connectToDatabase() {
        DbConnection.init("jdbc:sqlite:src/main/resources/OrderManagementSystem.db");
    }

}
