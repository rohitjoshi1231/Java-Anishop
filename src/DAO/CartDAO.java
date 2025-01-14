package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDAO {

    // Method to show a selected product by productId
    public static ResultSet showSelectedProduct(int productId) {

        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.connect();
            if (conn == null) {
                System.out.println("Connection not established.");
                return null;
            }
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SELECT_PRODUCT_WITH_ID);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return resultSet; // Ensure you close this ResultSet later in your calling code
    }

    // Fetch product details based on the ProductId
    public static List<Map<String, Object>> showProduct(int productId) {
        String query = "SELECT ProductId, ProductStock, ProductPrice FROM products WHERE ProductId = ?";
        List<Map<String, Object>> productData = new ArrayList<>();

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            assert conn != null;
            stmt.setInt(1, productId);

            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("ProductId", res.getInt("ProductId"));
                    row.put("Quantity", res.getInt("ProductStock")); // Assuming this maps to Quantity in cart
                    row.put("PriceAtAdd", res.getInt("ProductPrice"));
                    productData.add(row);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product details: " + e.getMessage());
        }

        return productData;
    }

    // Add product to the cart
    public static void addToBag(int productId) {
        System.out.println("Fetching product with ID: " + productId);

        // Fetch product data
        List<Map<String, Object>> products = showProduct(productId);

        if (products.isEmpty()) {
            System.out.println("No product found with the given product ID.");
            return;
        }

        try (Connection conn = DBConnection.connect()) {
            assert conn != null;

            // Insert query for the cart table
            String insertQuery = "INSERT INTO cart (ProductId, Quantity, PriceAtAdd) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

            // Iterate over the product data and insert each product into the cart
            for (Map<String, Object> product : products) {
                preparedStatement.setInt(1, (int) product.get("ProductId")); // Set ProductId
                preparedStatement.setInt(2, (int) product.get("Quantity")); // Set Quantity
                preparedStatement.setInt(3, (int) product.get("PriceAtAdd")); // Set PriceAtAdd

                // Execute the insert query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Product added to cart. Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            System.out.println("Error while adding product to the cart: " + e.getMessage());
        }
    }

    // Method to add a product to the cart
    public static void addCart(int productId, int quantity) {
        try (Connection conn = DBConnection.connect()) {
            assert conn != null;
            ResultSet productDetails = showSelectedProduct(productId);
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CART);

            while (productDetails != null && productDetails.next()) {
                int productIdValue = productDetails.getInt("ProductId");
                String productPrice = productDetails.getString("ProductPrice");

                preparedStatement.setInt(1, productIdValue);
                preparedStatement.setInt(2, quantity);
                preparedStatement.setString(3, productPrice);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
    }
}
