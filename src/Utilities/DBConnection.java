package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/javaanishop";
    private static final String user = "root";
    private static final String password = "riya1234";

    public static Connection connect() {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully!");
            return con;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        return null;
    }


}
