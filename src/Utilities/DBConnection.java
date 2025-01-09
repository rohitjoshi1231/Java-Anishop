package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/javaanishop";
    private static final String user = "root";
    private static final String password = "riya1234";

    public static Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Connection established successfully!");
                return con;
            }
        } catch (Exception e) {
            System.out.println("Error occurred while connecting to the database: " + e.getMessage());
        }
        return null; // Return null if connection fails
    }

}
