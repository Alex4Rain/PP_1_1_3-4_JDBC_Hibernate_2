package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String NAME_USER = "root";
    private static final String PASSWORD = "Xtrfyjd1990";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Connection connection;
    private static Statement statement;

    private static Connection getConnection() {
        try {
            return connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public static Statement getStatement() {
        try {
            return statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
