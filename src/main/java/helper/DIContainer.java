package helper;

import domain.dao.UserDao;
import domain.dao.impl.UserDaoImpl;
import domain.model.User;
import domain.model.dto.UserDto;

public class DIContainer {
    private static UserDao userDao;
    private static UserDto userDto;

    private DIContainer() {

    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}
