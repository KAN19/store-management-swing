package domain.dao;

import domain.model.Customer;

public interface CustomerDao extends BaseDao{

    Customer login(String username, String password) ;

    boolean register(Customer customer) ;

    public boolean findUserByUsername(String userName);
}
