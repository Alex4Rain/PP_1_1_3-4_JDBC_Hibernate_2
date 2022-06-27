package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String NAME_USER = "root";
    private static final String PASSWORD = "Xtrfyjd1990";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Connection connection;


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        return connection;
    }
}
