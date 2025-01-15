package DAO;

import Service_layer.Product;
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
    public static Product showSelectedProduct(int productId) {
        Product product = null; // Assuming you have a Product class to hold product details
        try (Connection conn = DBConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.SELECT_PRODUCT_WITH_ID)) {

            preparedStatement.setInt(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product();
                    product.setProductId(productId);
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setProductDescription(resultSet.getString("ProductDescription"));
                    product.setProductStock(resultSet.getInt("ProductStock"));
                    product.setProductPrice(resultSet.getDouble("ProductPrice"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return product; // Return the product object or null if not found
    }

    public static List<Map<String, Object>> showProduct(int productId) {
        List<Map<String, Object>> productData = new ArrayList<>();

        String selectQuery = SqlQueries.SELECT_PRODUCT_WITH_ID; // Ensure this query has a WHERE clause like: SELECT *
        // FROM products WHERE ProductId = ?

        try (Connection conn = DBConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, productId); // Set the productId parameter

            try (ResultSet res = preparedStatement.executeQuery()) { // Execute query to fetch data
                while (res.next()) {
                    Map<String, Object> product = new HashMap<>();
                    product.put("ProductId", res.getInt("ProductId"));
                    product.put("ProductName", res.getString("ProductName"));
                    product.put("ProductDescription", res.getString("ProductDescription"));
                    product.put("ProductStock", res.getInt("ProductStock"));
                    product.put("ProductPrice", res.getDouble("ProductPrice"));

                    // Add the product map to the list
                    productData.add(product);
                }
            }

        } catch (SQLException e) {
            // Instead of printing to console, use a logger for better error handling
            System.out.println("Error retrieving product details: " + e.getMessage());
            e.printStackTrace(); // Optional: for detailed stack trace information
        }

        return productData;
    }

    // Fetch product details based on the ProductId
    // public static List<Map<String, Object>> showProduct(int productId) {
    // String query = "SELECT (ProductId, ProductName, ProductDescription,
    // ProductStock, ProductPrice) FROM products WHERE ProductId = ?";
    // List<Map<String, Object>> productData = new ArrayList<>();

    // try (Connection conn = DBConnection.connect()) {
    // assert conn != null;
    // try (PreparedStatement stmt = conn.prepareStatement(query)) {

    // stmt.setInt(1, productId);

    // try (ResultSet res = stmt.executeQuery()) {
    // while (res.next()) {
    // Map<String, Object> row = new HashMap<>();
    // row.put("ProductId", res.getInt("ProductId"));
    // row.put("Quantity", res.getInt("ProductStock"));
    // row.put("ProductPrice", res.getDouble("ProductPrice"));
    // row.put("ProductName", res.getString("ProductName"));
    // row.put("ProductDescription", res.getString("ProductDescription"));

    // productData.add(row);
    // }
    // }
    // }
    // } catch (SQLException e) {
    // System.out.println("Error retrieving product details: " + e.getMessage());
    // }

    // return productData;
    // }

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
                // Get ProductId
                int productIdFromMap = (int) product.get("ProductId");

                // Handle Quantity - Default to 1 if not available
                Object quantityObj = product.get("ProductStock"); // Check if this is available in the Map
                int quantity = (quantityObj != null) ? (int) quantityObj : 1; // Default quantity to 1

                // Get PriceAtAdd
                double priceAtAdd = (double) product.get("ProductPrice"); // Assuming PriceAtAdd exists

                // Set parameters in the prepared statement
                preparedStatement.setInt(1, productIdFromMap); // Set ProductId
                preparedStatement.setInt(2, quantity); // Set Quantity
                preparedStatement.setDouble(3, priceAtAdd); // Set PriceAtAdd

                // Execute the insert query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Product added to cart. Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            System.out.println("Error while adding product to the cart: " + e.getMessage());
        }
    }

    public static void addCart(int productId, int quantity) {
        Product product = showSelectedProduct(productId);
        if (product != null) {
            try (Connection conn = DBConnection.connect()) {
                String insertQuery = SqlQueries.INSERT_CART;
                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setInt(2, quantity);
                preparedStatement.setDouble(3, product.getProductPrice());

                preparedStatement.executeUpdate();
                System.out.println("Product added to cart.");
            } catch (SQLException e) {
                System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
            }
        } else {
            System.out.println("Product not found.");
        }
    }

}
