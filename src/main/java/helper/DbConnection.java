package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String url;

    private static DbConnection instance = null;
    private Connection connection = null;

    private DbConnection() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static DbConnection init(String url) {
        DbConnection.url = url;
        if (instance == null) {
            instance = new DbConnection();
        }
        System.out.println("connection oke");
        return instance;
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
