package domain.dao.impl;

import domain.dao.UserDao;
import domain.model.User;
import domain.model.dto.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    @Override
    public UserDto signIn(String username, String password)  {
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }

            if (Objects.equals(result.getString("password"), password)) {
                return new UserDto(
                        result.getString("username"),
                        result.getString("full_name"),
                        result.getString("phone_number"),
                        result.getString("role"),
                        result.getString("type_of_member"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer register(User user)  {
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("INSERT INTO user " +
                            "(username, password, full_name, phone_number, role, type_of_member) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getTypeOfMember());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findUserByUsername(String username) {
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement("SELECT * FROM user WHERE username = ?");

            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
