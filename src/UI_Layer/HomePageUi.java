package UI_Layer;

import Service_layer.HomePageService;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static UI_Layer.MainFrame.createBottomNav;
import static UI_Layer.MainFrame.createProductPanel;

public class HomePageUi {

    public static JPanel createHomePanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(new Color(30, 30, 30));


        // Create a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane();
        JPanel homeContentPanel = new JPanel();
        homeContentPanel.setLayout(new BoxLayout(homeContentPanel, BoxLayout.Y_AXIS));
        homeContentPanel.setBackground(new Color(30, 30, 30)); // Dark background

        JPanel productPanel = fetchAndDisplay();
        homeContentPanel.add(productPanel);

        // Set the content to scroll pane
        scrollPane.setViewportView(homeContentPanel);
        homePanel.add(scrollPane, BorderLayout.CENTER);

        // Add Bottom Navigation
        JPanel bottomNav = createBottomNav(cardLayout, mainPanel);
        homePanel.add(bottomNav, BorderLayout.SOUTH);

        // Revalidate and repaint to ensure the updated content is displayed
        homePanel.revalidate();
        homePanel.repaint();

        cardLayout.show(mainPanel, "Home");

        return homePanel;
    }

    public static JPanel fetchAndDisplay() {
        JPanel productContainer = new JPanel();
        productContainer.setLayout(new BoxLayout(productContainer, BoxLayout.Y_AXIS));
        productContainer.setBackground(new Color(30, 30, 30)); // Dark background

        ResultSet data = HomePageService.showProducts();
        try {
            while (data != null && data.next()) {
                int productId = data.getInt("ProductId");
                String productName = data.getString("ProductName");
                String productDescription = data.getString("ProductDescription");
                double productPrice = data.getDouble("ProductPrice");

                // Create a product panel for each product
                JPanel productPanel = createProductPanel(productId, productName, productDescription, productPrice);
                productContainer.add(productPanel);
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

        return productContainer;
    }

}