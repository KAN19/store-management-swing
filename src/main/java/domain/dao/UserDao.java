package domain.dao;

import domain.model.User;
import domain.model.dto.UserDto;

import java.sql.SQLException;

public interface UserDao extends BaseDao{

    UserDto signIn(String username, String password) ;

    Integer register(User user) ;

    public boolean findUserByUsername(String userName);
}
