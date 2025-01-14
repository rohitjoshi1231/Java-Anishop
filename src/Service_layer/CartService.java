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
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private static final Connection conn = DBConnection.connect();
    private static final ValidationUtil validation = new ValidationUtil();


    // Method to fetch cart items from the database
    public List<CartProduct> displayCartItems() {
        List<CartProduct> cartItems = new ArrayList<>();
        try (conn) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SHOW_CART_PRODUCTS);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                String productId = res.getString("ProductId");
                String quantity = res.getString("Quantity");
                int priceAtAdd = res.getInt("PriceAtAdd");
                cartItems.add(new CartProduct(productId, quantity, priceAtAdd));
            }
        } catch (SQLException e) {
            System.out.println("Error while displaying products in the cart: " + e);
        }
        return cartItems;
    }
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

    public void addProductToBag(int productId) {
        CartDAO.addToBag(productId);
    }

    // Create a CartProduct class to hold cart item details
    public class CartProduct {
        private String productId;
        private String quantity;
        private int priceAtAdd;

        public CartProduct(String productId, String quantity, int priceAtAdd) {
            this.productId = productId;
            this.quantity = quantity;
            this.priceAtAdd = priceAtAdd;
        }

        public String getProductId() {
            return productId;
        }

        public String getQuantity() {
            return quantity;
        }

        public int getPriceAtAdd() {
            return priceAtAdd;
        }
    }

}
