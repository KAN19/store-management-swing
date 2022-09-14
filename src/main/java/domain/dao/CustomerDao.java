package domain.dao;

import domain.model.Customer;
import domain.model.dto.UserDto;

public interface CustomerDao extends BaseDao{

    UserDto signIn(String username, String password) ;

    boolean register(Customer customer) ;

    public boolean findUserByUsername(String userName);
}
