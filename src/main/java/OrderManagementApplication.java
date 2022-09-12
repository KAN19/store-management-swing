import domain.dao.UserDao;
import domain.model.dto.UserDto;
import helper.DIContainer;
import helper.DbConnection;
import view.MainStoreGUI;

import java.sql.SQLException;

public class OrderManagementApplication {

    public static void main(String[] args) {
        connectToDatabase();
        MainStoreGUI mainStoreGUI = new MainStoreGUI();
        mainStoreGUI.setVisible(true);
//        disconnectToDataBase();
    }

    public static void connectToDatabase() {
        DbConnection.init("jdbc:sqlite:src/main/resources/OrderManagementSystem.db");
    }

    public static void disconnectToDataBase() {
        DbConnection.getInstance().closeConnection();

    }

}
