package UI_Layer;

import Service_layer.HomePageService;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HomePage {

    private static HomePageUi home = null;
    

    public static void main(String[] args) {

        

        // Future integration: Fetch and display product data

        ResultSet data = HomePageService.showProducts();
        try {
            while (data != null && data.next()) {
                int productId = data.getInt("ProductId");
                String productName = data.getString("ProductName");
                String productDescription = data.getString("ProductDescription");
                int productStock = data.getInt("ProductStock");
                double productPrice = data.getDouble("ProductPrice");
                home = new HomePageUi(productId, productName, productDescription, productStock, productPrice);
                System.out.println("ProductID: " + productId + ", ProductName: " + productName + ", ProductDescription: " + productDescription + ", ProductStock: " + productStock + ", ProductPrice: " + productPrice);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (data != null) {
                    data.getStatement().close();
                    data.close();
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }


        // Create and configure the main JFrame
        JFrame frame = new JFrame("Home");
        home.setupHomePageLayout(frame);
        // Display the frame
        frame.setSize(400, 700);
        frame.setVisible(true);
    }
}




