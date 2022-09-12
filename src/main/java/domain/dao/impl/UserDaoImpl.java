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
    public UserDto signIn(String username, String password) throws SQLException {
        PreparedStatement statement = connection
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

        return null;
    }

}
