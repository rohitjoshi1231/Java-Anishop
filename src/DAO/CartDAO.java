package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {

    public static ResultSet showSelectedProduct(int productId) {
        ResultSet res1 = null;
        try (Connection conn = DBConnection.connect()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SELECT_PRODUCT_WITH_ID);
            preparedStatement.setInt(1, productId);
            res1 = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_IN_SHOWING_PRODUCT);
        }
        return res1; // Return the result set (can be null if an error occurred)
    }

    public int addCart(int productId, int quantity) {
        try (Connection conn = DBConnection.connect()) {
            assert conn != null;
            ResultSet productDetails = showSelectedProduct(productId);
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CART);
            while (productDetails.next()) {
                int productId1 = productDetails.getInt("ProductId");
                String productPrice = productDetails.getString("ProductPrice");

                preparedStatement.setInt(1, productId1);
                preparedStatement.setInt(2, quantity);
                preparedStatement.setString(3, productPrice);
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
        return 0;
    }
}
