import domain.dao.UserDao;
import domain.model.dto.UserDto;
import helper.DIContainer;
import helper.DbConnection;
import view.MainStoreGUI;
import view.WelcomeGUI;

import java.sql.SQLException;

public class OrderManagementApplication {

    public static void main(String[] args) {
        connectToDatabase();
        new WelcomeGUI();
    }

    public static void connectToDatabase() {
        DbConnection.init("jdbc:sqlite:src/main/resources/OrderManagementSystem.db");
    }

}
