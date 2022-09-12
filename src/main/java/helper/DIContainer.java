package helper;

import domain.dao.CategoryDao;
import domain.dao.ProductDao;
import domain.dao.UserDao;
import domain.dao.impl.CategoryDaoImpl;
import domain.dao.impl.ProductDaoImpl;
import domain.dao.impl.UserDaoImpl;
import domain.model.Category;
import domain.model.Product;
import domain.model.User;
import domain.model.dto.UserDto;

public class DIContainer {
    private static UserDao userDao;
    private static ProductDao productDao;
    private static CategoryDao categoryDao;
    private static UserDto userDto = null;

    private DIContainer() {

    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
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
