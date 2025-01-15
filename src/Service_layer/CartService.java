package Service_layer;

import DAO.CartDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.DBConnection;
import Utilities.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private static final ValidationUtil validation = new ValidationUtil();

    public List<CartProduct> displayCartItems() {
        List<CartProduct> cartItems = new ArrayList<>();
        String query = "SELECT cart.ProductId, cart.Quantity, cart.PriceAtAdd, " +
                "products.ProductName, products.ProductDescription " +
                "FROM cart " +
                "JOIN products ON cart.ProductId = products.ProductId";

        // Get a valid connection from DBConnection
        try (Connection conn = DBConnection.connect();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                String productId = res.getString("ProductId");
                String productName = res.getString("ProductName");
                String productDescription = res.getString("ProductDescription");
                String quantity = res.getString("Quantity");
                int priceAtAdd = res.getInt("PriceAtAdd");

                cartItems.add(new CartProduct(productId, productName, productDescription, quantity, priceAtAdd));
            }
        } catch (SQLException e) {
            System.out.println("Error while displaying products in the cart: " + e.getMessage());
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

    // public static ResultSet showSelectedProduct(int productId) {
    //     return CartDAO.showSelectedProduct(productId);
    // }

    public void addProductToBag(int productId) {
        CartDAO.addToBag(productId);
    }

    public class CartProduct {
        private String productId;
        private String productName;
        private String productDescription;
        private String quantity;
        private int priceAtAdd;

        public CartProduct(String productId, String productName, String productDescription, String quantity, int priceAtAdd) {
            this.productId = productId;
            this.productName = productName;
            this.productDescription = productDescription;
            this.quantity = quantity;
            this.priceAtAdd = priceAtAdd;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public String getQuantity() {
            return quantity;
        }

        public int getPriceAtAdd() {
            return priceAtAdd;
        }
    }


}
