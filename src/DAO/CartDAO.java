package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {
    private static final Connection conn = DBConnection.connect();

    // Method to show a selected product by productId
    public static ResultSet showSelectedProduct(int productId) {
        ResultSet res1 = null;
        try (conn) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SELECT_PRODUCT_WITH_ID);
            preparedStatement.setInt(1, productId);
            res1 = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_IN_SHOWING_PRODUCT);
        }
        return res1; // Return the result set (can be null if an error occurred)
    }

    // Method to add a product to the cart
    public static void addCart(int productId, int Quantity) {
        try (conn) {
            assert conn != null;
            ResultSet productDetails = showSelectedProduct(productId);
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CART);
            while (productDetails.next()) {
                int productId1 = productDetails.getInt("ProductId");
                String productPrice = productDetails.getString("ProductPrice");

                preparedStatement.setInt(1, productId1);
                preparedStatement.setInt(2, Quantity);
                preparedStatement.setString(3, productPrice);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
    }
}
