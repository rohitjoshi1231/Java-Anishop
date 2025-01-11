package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.*;

public class ProductDAO {

    private final Connection conn = DBConnection.connect();

    public static int addProduct(String productName, String productDescription, int productStock, double productPrice) {

        try (Connection conn = DBConnection.connect()) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productDescription);
            preparedStatement.setInt(3, productStock);
            preparedStatement.setDouble(4, productPrice);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_REGISTER);
        }
        return 0;
    }

    public ResultSet productDetails() {
        ResultSet res1 = null;
        try {
            String ProductData = SqlQueries.SELECT_ALL_PRODUCTS;
            Statement userData = conn.createStatement();
            // Execute the query and get the result set
            res1 = userData.executeQuery(ProductData);
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_IN_SHOWING_PRODUCT);
        }
        return res1; // Return the result set (can be null if an error occurred)
    }

}
