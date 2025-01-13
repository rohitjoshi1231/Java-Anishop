//package UI_Layer;
//
//import Service_layer.HomePageService;
//
//import javax.swing.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomePage {
//
//    public static void main(String[] args) {
//        // Fetch the product data from the database
//        ResultSet res = null;
//        List<Product> products = new ArrayList<>();
//
//        try {
//            res = HomePageService.showProducts();
//
//            if (res == null) {
//                System.out.println("No data returned from the database.");
//            } else {
//                // Convert ResultSet to List of Product objects
//                while (res.next()) {
//                    int productId = res.getInt("productId");
//                    String productName = res.getString("productName");
//                    String productDescription = res.getString("productDescription");
//                    int productStock = res.getInt("productStock");
//                    double productPrice = res.getDouble("productPrice");
//
//                    // Add the product to the list
//                    products.add(new Product(productId, productName, productDescription, productStock, productPrice));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error fetching product data: " + e.getMessage(), "Database Error",
//                    JOptionPane.ERROR_MESSAGE);
//        } finally {
//            // Close the ResultSet
//            try {
//                if (res != null) {
//                    res.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Create HomePage UI with dynamic product data
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Home Page");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(500, 700);
//
//            // Pass the product list to HomePageUi
//            HomePageUi homePageUi = new HomePageUi(products);
//            frame.add(homePageUi.createHomePanel());
//
//            frame.setVisible(true);
//        });
//    }
//}
