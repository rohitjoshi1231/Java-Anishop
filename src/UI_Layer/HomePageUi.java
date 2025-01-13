package UI_Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class HomePageUi {

    int ProductId;
    String ProductName;
    String ProductDescription;
    int ProductStock;
    double ProductPrice;

    HomePageUi(int ProductId, String ProductName, String ProductDescription, int ProductStock, double ProductPrice) {
        this.ProductName = ProductName;
        this.ProductId = ProductId;
        this.ProductDescription = ProductDescription;
        this.ProductStock = ProductStock;
        this.ProductPrice = ProductPrice;
    }

    public void setupHomePageLayout(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use CardLayout to manage different panels (fragments)
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);  // Create a panel with CardLayout

        // Add the home panel
        JPanel homePanel = createProductPanel(frame); // Home page content
        contentPanel.add(homePanel, "Home");

        // Add the cart panel
        JPanel cartPanel = createCartPanel();  // Cart page content
        contentPanel.add(cartPanel, "Cart");

        // Wrap the content panel in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add the scrollable panel to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add the bottom navigation bar
        setupBottomNavigation(frame, cardLayout, contentPanel);
    }

    private JPanel createProductPanel(JFrame frame) {
        // Create a panel with a GridLayout to display product and description pairs
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // 1 column, unlimited rows, with spacing between them
        panel.setBackground(Color.BLACK);

        // Load the image icon
        ImageIcon productIcon = new ImageIcon("C:\\Users\\rash5\\OneDrive\\Desktop\\Auction Android App\\AniShop\\src\\Utilities\\Images\\product.png");

        // Resize the image (example: 32x32 pixels)
        Image image = productIcon.getImage(); // Convert to Image
        Image resizedImage = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH); // Resize the image
        productIcon = new ImageIcon(resizedImage); // Set the resized image back to ImageIcon

        // Create a panel for each product (vertical layout for image and labels)
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 rows: 1 for name, 1 for description
        productPanel.setBackground(new Color(37, 37, 37, 128));  // Dark gray with 50% opacity

        // Create the product name and description labels
        JLabel name = new JLabel(ProductName);  // Use the ProductName from constructor
        JLabel des = new JLabel(ProductDescription);  // Use the ProductDescription from constructor

        // Set fonts and properties
        name.setFont(new Font("Arial", Font.BOLD, 20));
        des.setFont(new Font("Arial", Font.ITALIC, 15));
        des.setForeground(Color.white);
        des.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10)); // Add padding

        name.setIcon(productIcon);
        name.setForeground(Color.white);
        name.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add a mouse listener to handle click events
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action to perform when the label is clicked
                JOptionPane.showMessageDialog(frame, "Label clicked: " + ProductName);
            }
        });

        // Add the name and description labels to the product panel (GridLayout)
        productPanel.add(name);
        productPanel.add(des);

        // Add the product panel to the main panel (this will go into the GridLayout)
        panel.add(productPanel);

        return panel;
    }

    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(Color.LIGHT_GRAY);
        cartPanel.setLayout(new BorderLayout());  // Use BorderLayout

        // Add the label at the top of the cart panel
        cartPanel.add(new JLabel("Your Cart"), BorderLayout.NORTH);

        // Create a panel for the content inside the cart (this is where the products will be added)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));  // Stack components vertically
        contentPanel.setBackground(Color.WHITE); // Set the background color for the content panel

        // Check if the cart is empty or has products
        // Add product details here (for example, product name, quantity, price)
        contentPanel.add(new JLabel("Product 1"));
        contentPanel.add(new JLabel("Product 2"));

        // Create a scroll pane and add content panel to it
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 200));  // Optional size for the scroll pane

        // Add the scrollable content panel to the cart panel
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the checkout button and set it at the bottom
        JButton buy = new JButton("Checkout Items");
        buy.setPreferredSize(new Dimension(150, 40));  // Set preferred size for the button (optional)
        cartPanel.add(buy, BorderLayout.SOUTH);  // Place button at the bottom

        return cartPanel;
    }

    private void setupBottomNavigation(JFrame frame, CardLayout cardLayout, JPanel contentPanel) {
        // Create a panel for bottom navigation
        JPanel bottomNavPanel = new JPanel();
        bottomNavPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomNavPanel.setBackground(new Color(0, 0, 0, 128)); // Semi-transparent black background

        // Create buttons for bottom navigation
        JButton homeButton = new JButton("Home");
        JButton searchButton = new JButton("Cart");
        JButton profileButton = new JButton("Profile");

        // Add ActionListeners for each button
        homeButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));

        searchButton.addActionListener(e -> cardLayout.show(contentPanel, "Cart"));
        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Profile Button Clicked"));

        // Add buttons to the bottom navigation panel
        bottomNavPanel.add(homeButton);
        bottomNavPanel.add(searchButton);
        bottomNavPanel.add(profileButton);

        // Add the bottom navigation panel to the frame
        frame.add(bottomNavPanel, BorderLayout.SOUTH);
    }
}













// package UI_Layer;

// import Service_layer.ProductService;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class HomePageUi {

//     private ProductService productService; // Updated to fetch products

//     public HomePageUi() {
//         productService = new ProductService(); // Initialize productService
//     }

//     public void setupHomePageLayout(JFrame frame) {
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Use CardLayout to manage different panels
//         CardLayout cardLayout = new CardLayout();
//         JPanel contentPanel = new JPanel(cardLayout);

//         // Create panels for different pages
//         JScrollPane homePanel = createProductPanel(frame); // Dynamically load products
//         JPanel cartPanel = createCartPanel();
//         JPanel profilePanel = createProfilePanel();

//         // Add panels to CardLayout
//         contentPanel.add(homePanel, "Home");
//         contentPanel.add(cartPanel, "Cart");
//         contentPanel.add(profilePanel, "Profile");

//         // Add the content panel to the frame
//         frame.add(contentPanel, BorderLayout.CENTER);

//         // Add the bottom navigation bar
//         setupBottomNavigation(cardLayout, contentPanel);

//         // Show the home panel by default
//         cardLayout.show(contentPanel, "Home");

//         // Frame properties
//         frame.setSize(800, 600);
//         frame.setVisible(true);
//     }

//     private JScrollPane createProductPanel(JFrame frame) {
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Display products line-by-line
//         panel.setBackground(Color.BLACK);

//         try {
//             // Fetch products from the database
//             ResultSet rs = productService.getAllProducts();
//             while (rs.next()) {
//                 String productName = rs.getString("productName");
//                 String productDescription = rs.getString("product_description");
//                 int productStock = rs.getInt("product_stock");
//                 double productPrice = rs.getDouble("product_price");

//                 // Product display components
//                 JLabel nameLabel = new JLabel(productName);
//                 JLabel descriptionLabel = new JLabel("Description: " + productDescription);

//                 nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
//                 nameLabel.setForeground(Color.WHITE);
//                 nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//                 descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
//                 descriptionLabel.setForeground(Color.LIGHT_GRAY);

//                 // Mouse listener for product click
//                 nameLabel.addMouseListener(new MouseAdapter() {
//                     @Override
//                     public void mouseClicked(MouseEvent e) {
//                         JOptionPane.showMessageDialog(frame, "Product clicked: " + nameLabel.getText());
//                     }
//                 });

//                 // Add labels and spacing between them
//                 panel.add(nameLabel);
//                 panel.add(descriptionLabel);
//                 panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between products
//             }
//         } catch (SQLException e) {
//             e.printStackTrace(); // Handle any database errors
//         }

//         // Wrap the panel in a JScrollPane
//         JScrollPane scrollPane = new JScrollPane(panel);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

//         return scrollPane;
//     }

//     private JPanel createCartPanel() {
//         JPanel cartPanel = new JPanel(new BorderLayout());
//         cartPanel.setBackground(Color.WHITE);

//         cartPanel.add(new JLabel("Your Cart", SwingConstants.CENTER), BorderLayout.NORTH);

//         JPanel contentPanel = new JPanel();
//         contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//         contentPanel.setBackground(Color.WHITE);

//         contentPanel.add(new JLabel("Product 1"));
//         contentPanel.add(new JLabel("Product 2"));

//         JScrollPane scrollPane = new JScrollPane(contentPanel);
//         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

//         cartPanel.add(scrollPane, BorderLayout.CENTER);

//         JButton buy = new JButton("Checkout Items");
//         cartPanel.add(buy, BorderLayout.SOUTH);

//         return cartPanel;
//     }

//     private JPanel createProfilePanel() {
//         JPanel profilePanel = new JPanel(new BorderLayout());
//         profilePanel.setBackground(Color.WHITE);

//         JLabel label = new JLabel("Profile Page", SwingConstants.CENTER);
//         label.setFont(new Font("Arial", Font.BOLD, 24));
//         profilePanel.add(label, BorderLayout.CENTER);

//         return profilePanel;
//     }

//     private void setupBottomNavigation(CardLayout cardLayout, JPanel contentPanel) {
//         JPanel bottomNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//         bottomNavPanel.setBackground(Color.BLACK);

//         JButton homeButton = new JButton("Home");
//         JButton cartButton = new JButton("Cart");
//         JButton profileButton = new JButton("Profile");

//         homeButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));
//         cartButton.addActionListener(e -> cardLayout.show(contentPanel, "Cart"));
//         profileButton.addActionListener(e -> cardLayout.show(contentPanel, "Profile"));

//         bottomNavPanel.add(homeButton);
//         bottomNavPanel.add(cartButton);
//         bottomNavPanel.add(profileButton);

//         contentPanel.getParent().add(bottomNavPanel, BorderLayout.SOUTH);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             JFrame frame = new JFrame("Home Page UI");
//             HomePageUi homePageUi = new HomePageUi();
//             homePageUi.setupHomePageLayout(frame);
//         });
//     }
// }
