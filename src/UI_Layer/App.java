//package UI_Layer;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//import java.util.ArrayList;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//class Product {
//    int productId;
//    String productName;
//    String productDescription;
//    int productStock;
//    double productPrice;
//
//    Product(int productId, String productName, String productDescription, int productStock, double productPrice) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productDescription = productDescription;
//        this.productStock = productStock;
//        this.productPrice = productPrice;
//    }
//}
//
//class HomePageUi {
//    List<Product> productList;
//    List<Product> cartList;
//    JPanel mainPanel;
//    CardLayout cardLayout;
//    JFrame frame;
//
//    HomePageUi(List<Product> productList) {
//        this.productList = productList;
//        this.cartList = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            cartList.add(new Product(i, "Product " + i, "Description of Product " + i, 100, 100 + i * 10));
//        }
//
//        this.mainPanel = new JPanel();
//        this.cardLayout = new CardLayout();
//        this.mainPanel.setLayout(cardLayout);
//    }
//
//    public JPanel createCartPanel() {
//        JPanel cartPanel = new JPanel();
//        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
//        cartPanel.setBackground(Color.BLACK);
//
//        if (cartList.isEmpty()) {
//            JLabel emptyLabel = new JLabel("Cart is empty");
//            emptyLabel.setForeground(Color.WHITE);
//            emptyLabel.setFont(new Font("Arial", Font.BOLD, 18));
//            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            cartPanel.add(emptyLabel);
//        } else {
//            for (Product product : cartList) {
//                JPanel productPanel = createProductItemPanel(product);
//                cartPanel.add(productPanel);
//            }
//        }
//
//        return cartPanel;
//    }
//
//    private void switchPanel(String cardName) {
//        cardLayout.show(mainPanel, cardName);
//    }
//
//    public JPanel createHomePanel() {
//        JPanel homePanel = new JPanel();
//        homePanel.setLayout(new BorderLayout());
//        homePanel.setSize(480, 700);
//
//        JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//        contentPanel.setBackground(Color.BLACK);
//
//        for (Product product : productList) {
//            JPanel productPanel = createProductItemPanel(product);
//            contentPanel.add(productPanel);
//        }
//
//        JScrollPane scrollPane = new JScrollPane(contentPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//
//        JPanel bottomNavPanel = new JPanel();
//        bottomNavPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        bottomNavPanel.setBackground(Color.BLACK);
//
//        JButton homeButton = new JButton("Home");
//        JButton cartButton = new JButton("Cart");
//        JButton profileButton = new JButton("Profile");
//
//        homeButton.addActionListener(e -> switchPanel("Home"));
//        cartButton.addActionListener(e -> switchPanel("Cart"));
//        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Profile section coming soon!"));
//
//        bottomNavPanel.add(homeButton);
//        bottomNavPanel.add(cartButton);
//        bottomNavPanel.add(profileButton);
//
//        homePanel.add(scrollPane, BorderLayout.CENTER);
//        homePanel.add(bottomNavPanel, BorderLayout.SOUTH);
//
//        return homePanel;
//    }
//
//    private JPanel createProductItemPanel(Product product) {
//        JPanel productPanel = new JPanel();
//        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
//        productPanel.setBackground(Color.BLACK);
//        productPanel.setPreferredSize(new Dimension(450, 120));
//        productPanel.setMaximumSize(new Dimension(450, 120));
//        productPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        productPanel.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.lightGray),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//
//        JLabel productNameLabel = new JLabel("Name: " + product.productName);
//        JLabel productDescLabel = new JLabel("Desc: " + product.productDescription);
//        JLabel productPriceLabel = new JLabel("Price: ₹" + product.productPrice);
//
//        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        productDescLabel.setFont(new Font("Arial", Font.ITALIC, 14));
//        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        productNameLabel.setForeground(Color.white);
//        productDescLabel.setForeground(Color.GRAY);
//        productPriceLabel.setForeground(Color.GREEN);
//
//        productPanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(null, product.productName + " clicked. Price: ₹" + product.productPrice);
//            }
//        });
//
//        productPanel.add(productNameLabel);
//        productPanel.add(productDescLabel);
//        productPanel.add(productPriceLabel);
//
//        return productPanel;
//    }
//
//    public JPanel getMainPanel() {
//        return mainPanel;
//    }
//
//    // Method to initialize the app and create the main window
//    public void initialize() {
//        // Create the home panel and cart panel
//        JPanel homePanel = createHomePanel();
//        JPanel cartPanel = createCartPanel();
//
//        // Add panels to the card layout
//        getMainPanel().add(homePanel, "Home");
//        getMainPanel().add(cartPanel, "Cart");
//
//        // Create the frame and display the app
//        frame = new JFrame("E-Commerce App");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(480, 700);
//        frame.add(getMainPanel());
//        frame.setVisible(true);
//    }
//}
//
//
