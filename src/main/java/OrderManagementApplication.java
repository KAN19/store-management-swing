import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrderManagementApplication {

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:src/main/resources/" + fileName;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The db path is " + conn.getMetaData().getURL());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Create failed");
        }
    }

    public static void main(String[] args) {
        createNewDatabase("OrderManagementSystem.db");
    }
}
