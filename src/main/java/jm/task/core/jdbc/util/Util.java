package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/user_service";
    private static final String user = "root";
    //without password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
