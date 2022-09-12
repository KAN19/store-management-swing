package domain.dao;

import helper.DbConnection;

import java.sql.Connection;

public interface BaseDao {
    public final Connection connection = DbConnection.getInstance().getConnection();
}
