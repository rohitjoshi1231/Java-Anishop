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
    private static final ValidationUtil validation = new ValidationUtil();

    // Method to add a product to the cart
    public static void addCart(int productId, int quantity) {
        if (validation.validateQuantity(quantity)) {
            CartDAO.addCart(productId, quantity);
        } else {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
    }

    public static ResultSet showSelectedProduct(int productId) {
        return CartDAO.showSelectedProduct(productId);
    }

    // Method to display items in the cart
    public void displayCartItems() {
        try (conn) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SHOW_CART_PRODUCTS);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int cartId = res.getInt("CartId");
                String productId = res.getString("ProductId");
                String quantity = res.getString("Quantity");
                int priceAtAdd = res.getInt("PriceAtAdd");
                double addedAt = res.getDouble("AddedAt");

                System.out.println("CartId: " + cartId + ", ProductId: " + productId + ", Quantity: " + quantity + ", PriceAtAdd: " + priceAtAdd + ", AddedAt: " + addedAt);
            }
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_DISPLAYING_PRODUCT_IN_CART + e);
        }
    }
}
