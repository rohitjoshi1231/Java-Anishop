package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePageDAO {
    private final static Connection conn = DBConnection.connect();

    public ResultSet showProducts() {
        try {
            assert conn != null;
            String userDataQuery = SqlQueries.SELECT_ALL_PRODUCTS;
            Statement productData = conn.createStatement();
            return productData.executeQuery(userDataQuery);

        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_REGISTER);
            e.printStackTrace();
        }
        return null;
    }
}
