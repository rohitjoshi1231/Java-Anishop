package Service_layer;

import Utilities.DBConnection;
import Utilities.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CartDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;

public class CartService {
    private static final Connection conn = DBConnection.connect();
    private static final ValidationUtil validation = new ValidationUtil();
    private static final CartDAO cart = new CartDAO();

    public static void addCart(int productId, int Quantity) {
        if (validation.validateQuantity(Quantity)) {
            cart.addCart(productId, Quantity);
        } else {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
    }

    public void displayCartItems() {
        try (conn) {
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
