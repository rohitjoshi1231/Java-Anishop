package UI_Layer;

import Service_layer.HomePageService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    // Main method to test the home page UI
    public static void main(String[] args) {
        // Fetch the product data from the database
        ResultSet res = HomePageService.showProducts();

        // Convert ResultSet to List of Product objects
        List<Product> products = new ArrayList<>();
        try {
            while (res.next()) {
                int productId = res.getInt("productId");
                String productName = res.getString("productName");
                String productDescription = res.getString("productDescription");
                int productStock = res.getInt("productStock");
                double productPrice = res.getDouble("productPrice");

                // Add the product to the list
                products.add(new Product(productId, productName, productDescription, productStock, productPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create HomePageUi with dynamic product data
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.add(new HomePageUi(products).createHomePanel());  // Add the home panel with dynamic products
        frame.setVisible(true);
    }
}
