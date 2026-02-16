package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {
    public static Connection makeConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(Config.URL_DB, Config.USERNAME, Config.PASSWORD);
            System.out.println("connected....");
        } catch (SQLException e) {
            System.out.println("Erreur Connection" + e.getMessage());
        }
        return con;
    }
}
