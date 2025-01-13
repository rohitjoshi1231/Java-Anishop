package UI_Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// Product class to represent product details
class Product {
    int productId;
    String productName;
    String productDescription;
    int productStock;
    double productPrice;

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
    List<Product> productList;
    List<Product> cartList = new ArrayList<>();
    JPanel mainPanel;

    HomePageUi(List<Product> productList) {
        this.productList = productList;
        // Add sample products to the cart for demonstration
        cartList.add(new Product(101, "Sample Cart Product 1", "Cart Description 1", 5, 99.99));
        cartList.add(new Product(102, "Sample Cart Product 2", "Cart Description 2", 3, 149.99));
    }

    public JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Your Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cartPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.BLACK);

        if (cartList.isEmpty()) {
            JLabel emptyLabel = new JLabel("Cart is empty");
            emptyLabel.setForeground(Color.WHITE);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 18));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemsPanel.add(emptyLabel);
        } else {
            for (Product product : cartList) {
                JPanel productPanel = createProductItemPanel(product);
                itemsPanel.add(productPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        cartPanel.add(scrollPane, BorderLayout.CENTER);
        cartPanel.add(createBottomNavPanel(), BorderLayout.SOUTH);

        return cartPanel;
    }

    public JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BorderLayout());
        profilePanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Your Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name: John Doe");
        JLabel emailLabel = new JLabel("Email: johndoe@example.com");
        JLabel membershipLabel = new JLabel("Membership: Premium");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        membershipLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        nameLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        membershipLabel.setForeground(Color.WHITE);

        contentPanel.add(nameLabel);
        contentPanel.add(emailLabel);
        contentPanel.add(membershipLabel);

        profilePanel.add(contentPanel, BorderLayout.CENTER);
        profilePanel.add(createBottomNavPanel(), BorderLayout.SOUTH);

        return profilePanel;
    }

    private void switchPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JPanel createHomePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        homePanel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Available Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        homePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);

        for (Product product : productList) {
            JPanel productPanel = createProductItemPanel(product);
            contentPanel.add(productPanel);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        homePanel.add(scrollPane, BorderLayout.CENTER);
        homePanel.add(createBottomNavPanel(), BorderLayout.SOUTH);

        return homePanel;
    }

    private JPanel createBottomNavPanel() {
        JPanel bottomNavPanel = new JPanel();
        bottomNavPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomNavPanel.setBackground(Color.DARK_GRAY);

        JButton homeButton = new JButton("Home");
        JButton cartButton = new JButton("Cart");
        JButton profileButton = new JButton("Profile");

        homeButton.addActionListener(e -> switchPanel(createHomePanel()));
        cartButton.addActionListener(e -> switchPanel(createCartPanel()));
        profileButton.addActionListener(e -> switchPanel(createProfilePanel()));

        bottomNavPanel.add(homeButton);
        bottomNavPanel.add(cartButton);
        bottomNavPanel.add(profileButton);

        return bottomNavPanel;
    }

    private JPanel createProductItemPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBackground(Color.BLACK);
        productPanel.setPreferredSize(new Dimension(450, 120));
        productPanel.setMaximumSize(new Dimension(450, 120));
        productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel productNameLabel = new JLabel("Name: " + product.productName);
        JLabel productDescLabel = new JLabel("Description: " + product.productDescription);
        JLabel productPriceLabel = new JLabel("Price: ₹" + product.productPrice);

        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productDescLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        productNameLabel.setForeground(Color.WHITE);
        productDescLabel.setForeground(Color.GRAY);
        productPriceLabel.setForeground(Color.GREEN);

        productPanel.add(productNameLabel);
        productPanel.add(productDescLabel);
        productPanel.add(productPriceLabel);

        productPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cartList.add(product);
                JOptionPane.showMessageDialog(null, product.productName + " added to cart.");
            }
        });

        return productPanel;
    }


}
