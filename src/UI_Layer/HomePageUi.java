package UI_Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;  // Import java.util.List for generics

// Product class to represent product details
class Product {
    int productId;
    String productName;
    String productDescription;
    int productStock;
    double productPrice;

    // Constructor to initialize the product data
    Product(int productId, String productName, String productDescription, int productStock, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }
}

// HomePageUi class to create the user interface for the home page
class HomePageUi {
    List<Product> productList;  // List to hold dynamic products

    // Constructor to accept the product list
    HomePageUi(List<Product> productList) {
        this.productList = productList;
    }

    // Method to create the home page panel
    public JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setSize(480, 700); // Set the size to 480x700

        // Create product listing
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Layout for content
        contentPanel.setBackground(Color.BLACK);

        // Add each product to the panel dynamically from the productList
        for (Product product : productList) {
            JPanel productPanel = createProductItemPanel(product);
            contentPanel.add(productPanel); // Add product to content panel
        }

        // Wrap the content panel in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create and add a bottom navigation panel
        JPanel bottomNavPanel = new JPanel();
        bottomNavPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centered navigation buttons
        bottomNavPanel.setBackground(Color.BLACK);

        JButton homeButton = new JButton("Home");
        JButton searchButton = new JButton("Cart");
        JButton profileButton = new JButton("Profile");

        bottomNavPanel.add(homeButton);
        bottomNavPanel.add(searchButton);
        bottomNavPanel.add(profileButton);

        // Add scrollable content and bottom navigation panel to the home page
        homePanel.add(scrollPane, BorderLayout.CENTER);
        homePanel.add(bottomNavPanel, BorderLayout.SOUTH);

        return homePanel;
    }

    // Method to create the product item panel for each product
    private JPanel createProductItemPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); // Stack components vertically
        productPanel.setBackground(Color.BLACK);  // Set a solid background color
        productPanel.setPreferredSize(new Dimension(450, 120));
        productPanel.setMaximumSize(new Dimension(450, 120));
        productPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left

        // Add shadow effect to product panel by setting a border with a 3D effect
        productPanel.setBorder(BorderFactory.createCompoundBorder(
                // BorderFactory.createLineBorder(new Color(0, 0, 0, 100), 5), // Shadow effect
                BorderFactory.createLineBorder(Color.lightGray), // Shadow effect
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding for better view
        ));

        // Create labels for product name, description, and price
        JLabel productNameLabel = new JLabel("Name: " + product.productName);
        JLabel productDescLabel = new JLabel("Desc: " + product.productDescription);
        JLabel productPriceLabel = new JLabel("Price: ₹" + product.productPrice);

        // Styling
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productDescLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        productNameLabel.setForeground(Color.white);
        productDescLabel.setForeground(Color.GRAY);
        productPriceLabel.setForeground(Color.GREEN);

        // Add a mouse listener to handle click events (for product details view)
        productPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action to perform when product is clicked
                JOptionPane.showMessageDialog(null, product.productName + " clicked. Price: ₹" + product.productPrice);
            }
        });

        // Add the labels to the product panel
        productPanel.add(productNameLabel);
        productPanel.add(productDescLabel);
        productPanel.add(productPriceLabel);

        return productPanel;
    }

    // Main method to test the home page UI

}
