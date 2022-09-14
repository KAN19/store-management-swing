package helper;

import domain.dao.ProductDao;
import domain.dao.CustomerDao;
import domain.dao.impl.ProductDaoImpl;
import domain.dao.impl.CustomerDaoImpl;
import domain.model.Customer;

public class DIContainer {
    private static CustomerDao customerDao;
    private static ProductDao productDao;

    private static Customer customer = null;

    private DIContainer() {

    }

    public static CustomerDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }

    public static ProductDao getProductDao() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }


    public static Customer getCurrentUser() {
        return customer;
    }

    public static void setCurrentUser(Customer loginCustomer) {
        customer = loginCustomer;
    }
}
