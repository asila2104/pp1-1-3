package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/user_service";
    private static final String user = "root";
    //without password
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getThisConnection() {
        return connection;
    }
}
