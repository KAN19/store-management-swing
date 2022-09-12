package domain.dao.impl;

import domain.dao.UserDao;
import domain.model.dto.UserDto;
import helper.DbConnection;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImplTest extends TestCase {
    private UserDao userDao;
    private DbConnection connection;

    public void setUp() throws Exception {
        super.setUp();
        connection = DbConnection.init("jdbc:sqlite:src/main/resources/OrderManagementSystem.db");
        userDao = new UserDaoImpl();
    }

    public void testSignIn() {
        UserDto userDto
                = new UserDto("admin", "admin", "0907", "admin", "admin");

        try {
            UserDto userDto1 = userDao.signIn("admin", "admin");
            assertEquals(userDto1.getUsername(), userDto.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}