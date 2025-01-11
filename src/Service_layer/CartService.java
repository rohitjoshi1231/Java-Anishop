package Service_layer;

import Utilities.DBConnection;
import Utilities.ValidationUtil;
import DAO.CartDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartService {
    private static final Connection conn = DBConnection.connect();
    private final static ValidationUtil validation = new ValidationUtil();

    // Method to add a product to the cart
    public static void addCart(int productId, int Quantity) {
        if (validation.validateQuantity(Quantity)) {
            CartDAO.addCart(productId, Quantity);  // Call CartDAO to add the product to the cart
        } else {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
    }

    // Method to display items in the cart
    public void displayCartItems() {
        try (conn) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SHOW_CART_PRODUCTS);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int CartId = res.getInt("CartId");
                String ProductId = res.getString("ProductId");
                String Quantity = res.getString("Quantity");
                int PriceAtAdd = res.getInt("PriceAtAdd");
                double AddedAt = res.getDouble("AddedAt");

                System.out.println("CartId: " + CartId + ", ProductId: " + ProductId +
                        ", ProductDescription: " + Quantity + ", PriceAtAdd: " + PriceAtAdd +
                        ", AddedAt: " + AddedAt);
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_DISPLAYING_PRODUCT_IN_CART + e);
        }
    }
}
