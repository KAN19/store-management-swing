package helper;

import domain.dao.CategoryDao;
import domain.dao.ProductDao;
import domain.dao.CustomerDao;
import domain.dao.impl.CategoryDaoImpl;
import domain.dao.impl.ProductDaoImpl;
import domain.dao.impl.CustomerDaoImpl;
import domain.model.dto.UserDto;

public class DIContainer {
    private static CustomerDao customerDao;
    private static ProductDao productDao;
    private static CategoryDao categoryDao;
    private static UserDto userDto = null;

    private DIContainer() {

    }

    public static CustomerDao getUserDao() {
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

    public static CategoryDao getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }

    public static UserDto getCurrentUser() {
        return userDto;
    }

    public static void setCurrentUser(UserDto user) {
        userDto = user;
    }
}
