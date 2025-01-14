package UI_Layer;

import DAO.CartDAO;
import Service_layer.CartService;
import Service_layer.CartService.CartProduct;
import Service_layer.HomePageService;
import Service_layer.UserService;
import Utilities.Constants.SqlQueries;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


class MainFrame extends JFrame {
    public CardLayout cardLayout;
    public JPanel mainPanel;

    private final UserService userService = new UserService();

    public MainFrame() {
        setTitle("Ani-Shop E-Commerce-App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 690);

        mainPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout) mainPanel.getLayout();

        // Create and add the Login panel
        JPanel loginPanel = createLoginPanel();
        mainPanel.add(loginPanel, "Login");

        // Create and add the Register panel
        JPanel registerPanel = createRegisterPanel();
        mainPanel.add(registerPanel, "Register");

        // Create and add the Home Page panel
        JPanel homePanel = createHomePanel();
        mainPanel.add(homePanel, "Home");


        // Show the frame
        setContentPane(mainPanel);
        setVisible(true);
    }

    /*** Login Page ***/
    public JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for absolute positioning
        panel.setBackground(new Color(30, 30, 30)); // Dark background

        // Create and set the welcome label
        JLabel label = new JLabel("Welcome Back :)");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(0, 20, 480, 50);
        label.setForeground(Color.WHITE);
        panel.add(label);

        // Add a username label
        JLabel emailLabel = new JLabel("Enter Email ID:");
        JTextField emailText = new JTextField(20);
        field(emailLabel, panel, emailText, 50, 100, 150, 25, 200, 100, 200, 25);

        // Add a password label
        JLabel passwordLabel = new JLabel("Your Password:");
        JPasswordField passwordText = new JPasswordField(20);

        field(passwordLabel, panel, passwordText, 50, 150, 190, 25, 200, 150, 200, 25);

        // Add a login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 200, 100, 25);
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        UserService userService = new UserService();
        // Add action listener to handle login
        loginButton.addActionListener(e -> {
            String emailId = emailText.getText();
            String password = new String(passwordText.getPassword());

            try {
                // Call the service layer to authenticate the user
                userService.loginUser(emailId, password);
                // If login is successful, show success message
                JOptionPane.showMessageDialog(panel, "Login successful! :) ");
                cardLayout.show(mainPanel, "Home");
            } catch (Exception ex) {
                // Debugging print to ensure exception is caught
                System.out.println("Error caught: " + ex.getMessage());

                // Show error message if login fails
                JOptionPane.showMessageDialog(panel, "Login Failed: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create a "Not registered? Register" clickable text
        JLabel registerLabel = new JLabel("<html>Don't have an account? <a href=''>Register here.</a></html>");
        registerLabel.setForeground(Color.WHITE); // Set color to white
        registerLabel.setBounds(150, 250, 230, 25);
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "Register");
            }
        });
        panel.add(registerLabel);

        return panel;
    }

    // private CardLayout cardLayout;
    // private JPanel mainPanel;

    public JPanel createProductDescriptionPanel(int productID, JPanel mainPanel, CardLayout cardLayout) {
        // Create the main panel
        JPanel panel = new JPanel();
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 30)); // Dark background

        // Variables to store product details
        String productName = "Unknown Product";
        String productDescription = "No description available.";
        int productStock = 0;
        double productPrice = 0.0;

        // Fetch product details from the database
        ResultSet data = CartDAO.showSelectedProduct(productID);
        try {
            if (data != null && data.next()) {
                productName = data.getString("ProductName");
                productDescription = data.getString("ProductDescription");
                productStock = data.getInt("ProductStock");
                productPrice = data.getDouble("ProductPrice");
            } else {
                JOptionPane.showMessageDialog(panel, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return panel; // Return the empty panel if no product is found
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
            JOptionPane.showMessageDialog(panel, "Error retrieving product details: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (data != null) {
                    data.getStatement().close();
                    data.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }

        // Create and add labels to the panel
        JLabel nameLabel = createLabel(productName, new Font("Arial", Font.BOLD, 26), Color.RED, 0, 20, 480, 50);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel);

        JLabel descriptionLabel = createLabel("<html><p style='width:300px;'>" + productDescription + "</p></html>",
                new Font("Arial", Font.PLAIN, 14), Color.WHITE, 40, 80, 400, 60);
        panel.add(descriptionLabel);

        JLabel stockLabel = createLabel("Stock: " + (productStock > 0 ? productStock + " available" : "Out of stock"),
                new Font("Arial", Font.PLAIN, 14), productStock > 0 ? Color.GREEN : Color.RED, 40, 150, 200, 25);
        panel.add(stockLabel);

        JLabel priceLabel = createLabel("Price: ₹" + productPrice, new Font("Arial", Font.BOLD, 16), Color.RED, 40, 180,
                200, 25);
        panel.add(priceLabel);

        // Add to Bag Button
        JButton addToBagButton = new JButton("Add to Bag");
        addToBagButton.setBounds(150, 230, 150, 40);
        addToBagButton.setBackground(Color.RED);
        addToBagButton.setForeground(Color.WHITE);
        addToBagButton.setFont(new Font("Arial", Font.BOLD, 16));
        addToBagButton.setBorder(null);
        addToBagButton.setFocusPainted(false);
        panel.add(addToBagButton);

        //Go Back to Home Button
        JButton goBackHome = new JButton("Back to Home");
        goBackHome.setBounds(150, 300, 150, 40);
        goBackHome.setBackground(Color.RED);
        goBackHome.setForeground(Color.WHITE);
        goBackHome.setFont(new Font("Arial", Font.BOLD, 16));
        goBackHome.setBorder(null);
        goBackHome.setFocusPainted(false);
        panel.add(goBackHome);

        // Add Action Listener to Button
        int finalProductStock = productStock;
        String finalProductName = productName;

        CartService service = new CartService();

        addToBagButton.addActionListener(_ -> {
            if (finalProductStock > 0) {
                // Logic for adding the product to the bag (cart)

                service.addProductToBag(productID);

                JOptionPane.showMessageDialog(panel, finalProductName + " has been added to your bag!");
            } else {
                JOptionPane.showMessageDialog(panel, "Sorry, this product is out of stock.", "Out of Stock",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        goBackHome.addActionListener(_ -> {
            try {
                if (mainPanel != null && cardLayout != null) {
                    System.out.println("Navigating back to Home...");
                    cardLayout.show(mainPanel, "Home");
                    //mainPanel.revalidate();
                    //mainPanel.repaint();
                } else {
                    System.out.println("mainPanel or cardLayout is null!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error navigating back to Home: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create bottom navigation and add it to the bottom of the panel
        JPanel bottomNav = createBottomNav();
        panel.add(bottomNav, BorderLayout.SOUTH); // Add the bottomNav to the SOUTH region

        return panel;
    }

    // Helper method to create a JLabel
    private JLabel createLabel(String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    public JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 30)); // Dark background

        // Create and set the title label
        JLabel label = new JLabel("Welcome to Ani-Shop :)");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(0, 20, 480, 50);
        label.setForeground(Color.WHITE);
        panel.add(label);

        // Add a name label and text field
        JLabel nameLabel = new JLabel("Enter Your Name:");
        JTextField nameText = new JTextField(20);

        field(nameLabel, panel, nameText, 50, 90, 150, 25, 200, 90, 200, 25);

        // Add an email label and text field

        JLabel emailLabel = new JLabel("Enter Email ID:");
        JTextField emailText = new JTextField(20);

        field(emailLabel, panel, emailText, 50, 120, 150, 25, 200, 120, 200, 25);

        // Add a password label and password field

        JLabel passwordLabel = new JLabel("Enter Password:");
        JPasswordField passwordText = new JPasswordField(20);
        field(passwordLabel, panel, passwordText, 50, 150, 150, 25, 200, 150, 200, 25);

        // Add a gender label and radio buttons
        JLabel genderLabel = new JLabel("Choose Gender:");
        genderLabel.setBounds(50, 180, 150, 25);
        genderLabel.setForeground(Color.WHITE);
        panel.add(genderLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        radioButtonTemplate(maleButton, panel, 200, 180, 70, 25);

        JRadioButton femaleButton = new JRadioButton("Female");
        radioButtonTemplate(femaleButton, panel, 280, 180, 80, 25);

        JRadioButton otherButton = new JRadioButton("Other");
        radioButtonTemplate(otherButton, panel, 370, 180, 70, 25);

        JLabel ageLabel = new JLabel("Enter Your Age:");
        JTextField ageText = new JTextField(20);
        field(ageLabel, panel, ageText, 50, 210, 150, 25, 200, 210, 200, 25);

        JLabel contactLabel = new JLabel("Your Contact Number:");
        JTextField contactText = new JTextField(20);
        field(contactLabel, panel, contactText, 50, 240, 150, 25, 200, 240, 200, 25);

        // Add a register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 300, 100, 25);
        registerButton.setBackground(Color.RED);
        registerButton.setForeground(Color.WHITE);
        panel.add(registerButton);

        // Add action listener to handle registration
        registerButton.addActionListener(_ -> {
            String name = nameText.getText();
            String password = new String(passwordText.getPassword());
            String age = ageText.getText();
            String email = emailText.getText();
            String phoneNumber = contactText.getText();
            char gender = ' '; // Default value
            if (name.isEmpty() || password.isEmpty() || age.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                    || gender == ' ') {
                JOptionPane.showMessageDialog(panel, "Please fill the required details.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution if fields are not filled
            }

            if (maleButton.isSelected()) {
                gender = 'M'; // Male
            } else if (femaleButton.isSelected()) {
                gender = 'F'; // Female
            } else if (otherButton.isSelected()) {
                gender = 'O'; // Other
            }

            try {
                // Call the service layer to register the user
                userService.registerUser(email, password, name, gender, Integer.parseInt(age), phoneNumber);

                // If registration is successful, show success message
                JOptionPane.showMessageDialog(panel, "Registration successful.. :) ");

                // Switch to Log in UI after successful registration
                cardLayout.show(mainPanel, "Login");
            } catch (Exception ex) {
                // In case of any error during the registration process, show error message
                // JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Registration Failed",
                //         JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(panel, "Error" + ex.getMessage(), "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create an "Already registered? Login" clickable text
        JLabel loginLabel = new JLabel("<html>Already have an account? <a href=''>Login here.</a></html>");

        loginLabel.setBounds(150, 360, 250, 25); // Position and size
        loginLabel.setForeground(Color.WHITE); // Set the color for the clickable text
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });
        panel.add(loginLabel);

        return panel;
    }

    public JPanel createHomePanel() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(new Color(30, 30, 30)); // Dark background

        // Create a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane();
        JPanel homeContentPanel = new JPanel();
        homeContentPanel.setLayout(new BoxLayout(homeContentPanel, BoxLayout.Y_AXIS));
        homeContentPanel.setBackground(new Color(30, 30, 30)); // Dark background

        JPanel productPanel = fetchAndDisplay();
        if (productPanel != null) {
            homeContentPanel.add(productPanel);
        }

        // Set the content to scroll pane
        scrollPane.setViewportView(homeContentPanel);
        homePanel.add(scrollPane, BorderLayout.CENTER);

        // Add Bottom Navigation
        JPanel bottomNav = createBottomNav();
        homePanel.add(bottomNav, BorderLayout.SOUTH);

        return homePanel;
    }

    private JPanel createProductPanel(String... details) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBackground(new Color(40, 40, 40)); // Slightly lighter dark

        // Add a border with elevation effect (shadow-like)
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 2), // Red border
                // for
                // highlight
                BorderFactory.createEmptyBorder(20, 10, 20, 10) // Padding inside the border
        ));

        // Set a slight shadow effect using background color and border
        panel.setBackground(new Color(40, 40, 40));
        panel.setOpaque(true);

        for (String detail : details) {
            JLabel label = new JLabel("<html>&nbsp;&nbsp;" + detail + "</html>");
            label.setFont(new Font("Arial", Font.PLAIN, 15)); // Keep the font normal for inner content
            label.setForeground(Color.WHITE);
            panel.add(label);
        }


//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(panel, "Product clicked: " + name);
//            }
//        });

        return panel;
    }


//    public JPanel createProductPanel(String name, String description, String price) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 1));
//        panel.setBackground(new Color(40, 40, 40)); // Slightly lighter dark
//
//        // Add a border with elevation effect (shadow-like)
//        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 2), // Red border
//                // for
//                // highlight
//                BorderFactory.createEmptyBorder(20, 10, 20, 10) // Padding inside the border
//        ));
//
//        // Set a slight shadow effect using background color and border
//        panel.setBackground(new Color(40, 40, 40));
//        panel.setOpaque(true);
//
//        // Add product labels
//        // JLabel nameLabel = new JLabel("Product Name: " + name);
//        // nameLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set the font to bold
//        // nameLabel.setForeground(Color.WHITE);
//
//        // JLabel descriptionLabel = new JLabel("Description: " + description);
//        // descriptionLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set the font to bold
//        // descriptionLabel.setForeground(Color.WHITE);
//
//        // JLabel priceLabel = new JLabel("Price: " + price);
//        // priceLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set the font to bold
//        // priceLabel.setForeground(Color.RED);
//
//
//        JLabel nameLabel = new JLabel("<html><b>Product Name:</b>&nbsp;&nbsp;" + name + "</html>");
//        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15)); // Keep the font normal for inner content
//        nameLabel.setForeground(Color.WHITE);
//
//        JLabel descriptionLabel = new JLabel("<html><b>Description:</b>&nbsp;&nbsp;" + description + "</html>");
//        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15)); // Keep the font normal for inner content
//        descriptionLabel.setForeground(Color.WHITE);
//
//        JLabel priceLabel = new JLabel("<html><b>Price:</b>&nbsp;&nbsp;" + price + "</html>");
//        priceLabel.setFont(new Font("Arial", Font.PLAIN, 15)); // Keep the font normal for inner content
//        priceLabel.setForeground(Color.RED);
//
//        panel.add(nameLabel);
//        panel.add(descriptionLabel);
//        panel.add(priceLabel);
//
//        // Add MouseListener for click action on product
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showMessageDialog(panel, "Product clicked: " + name);
//            }
//        });
//
//        return panel;
//    }

    /*** Cart Page */
    private void showCartPage() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(new Color(30, 30, 30)); // Dark background

        JScrollPane scrollPane = new JScrollPane();
        JPanel cartContentPanel = new JPanel();
        cartContentPanel.setLayout(new BoxLayout(cartContentPanel, BoxLayout.Y_AXIS));
        cartContentPanel.setBackground(new Color(30, 30, 30)); // Dark background

        CartService service = new CartService();
        List<CartProduct> cartItems = service.displayCartItems();

        for (CartProduct cartProduct : cartItems) {
            JPanel cartProductPanel = createProductPanel(
                    "Name: " + cartProduct.getProductName(),
                    "Description: " + cartProduct.getProductDescription(),
                    "Quantity: " + cartProduct.getQuantity(),
                    "Price: $" + cartProduct.getPriceAtAdd()
            );
            cartContentPanel.add(cartProductPanel);
        }

        scrollPane.setViewportView(cartContentPanel);
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buyAllPanel = new JPanel();
        buyAllPanel.setBackground(new Color(40, 40, 40));
        JButton buyAllButton = new JButton("Buy All");
        buyAllButton.setBackground(Color.RED);
        buyAllButton.setForeground(Color.WHITE);
        buyAllButton.setPreferredSize(new Dimension(500, 40));
        buyAllButton.addActionListener(_ -> JOptionPane.showMessageDialog(cartPanel, "All products bought!"));

        buyAllPanel.add(buyAllButton);
        cartPanel.add(buyAllPanel, BorderLayout.SOUTH);

        JPanel bottomNav = createBottomNav();
        cartPanel.add(bottomNav, BorderLayout.NORTH);

        mainPanel.add(cartPanel, "Cart");
        cardLayout.show(mainPanel, "Cart");
    }

    private void showProfilePage() {
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(new Color(30, 30, 30)); // Dark background

        JPanel profileContent = new JPanel();
        profileContent.setLayout(new BoxLayout(profileContent, BoxLayout.Y_AXIS));
        profileContent.setBackground(new Color(30, 30, 30)); // Dark background

        // Add profile information with better visibility
        JLabel nameLabel = new JLabel("Name: John Doe");
        nameLabel.setForeground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email: john.doe@example.com");
        emailLabel.setForeground(Color.WHITE);
        JLabel addressLabel = new JLabel("Address: 1234 Elm Street");
        addressLabel.setForeground(Color.WHITE);
        JLabel phoneLabel = new JLabel("Phone: +1 (555) 123-4567");
        phoneLabel.setForeground(Color.WHITE);
        JLabel paymentLabel = new JLabel("Payment Method: Visa **** 1234");
        paymentLabel.setForeground(Color.WHITE);

        profileContent.add(nameLabel);
        profileContent.add(emailLabel);
        profileContent.add(addressLabel);
        profileContent.add(phoneLabel);
        profileContent.add(paymentLabel);

        JScrollPane scrollPane = new JScrollPane(profileContent);
        profilePanel.add(scrollPane, BorderLayout.CENTER);

        // Add Bottom Navigation
        JPanel bottomNav = createBottomNav();
        profilePanel.add(bottomNav, BorderLayout.NORTH);

        mainPanel.add(profilePanel, "Profile");
        cardLayout.show(mainPanel, "Profile");
    }

    private JPanel createBottomNav() {
        JPanel bottomNav = new JPanel();
        bottomNav.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomNav.setBackground(new Color(40, 40, 40)); // Dark background

        JButton homeButton = new JButton("Home");
        JButton cartButton = new JButton("Cart");
        JButton profileButton = new JButton("Profile");

        homeButton.setBackground(new Color(50, 50, 50)); // Dark button background
        cartButton.setBackground(new Color(50, 50, 50));
        profileButton.setBackground(new Color(50, 50, 50));

        homeButton.setForeground(Color.RED);
        cartButton.setForeground(Color.RED);
        profileButton.setForeground(Color.RED);

        bottomNav.add(homeButton);
        bottomNav.add(cartButton);
        bottomNav.add(profileButton);

        // Action listeners for navigation
        homeButton.addActionListener(_ -> cardLayout.show(mainPanel, "Home"));
        cartButton.addActionListener(_ -> showCartPage());
        profileButton.addActionListener(_ -> showProfilePage());

        return bottomNav;
    }

    // Register Page

    public void radioButtonTemplate(JRadioButton radioButton, JPanel panel, int x, int y, int width, int height) {
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(new Color(30, 30, 30));
        radioButton.setForeground(Color.WHITE);
        panel.add(radioButton);

    }

    public void field(JLabel jLabel, JPanel panel, JTextField textField, int x, int y, int width1, int height1, int x1,
                      int y1, int width2, int height2) {
        jLabel.setBounds(x, y, width1, height1);
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel);
        textField.setBounds(x1, y1, width2, height2);
        panel.add(textField);
    }


//    public JPanel fetchCart() {
//        JPanel productContainer = new JPanel();
//        productContainer.setLayout(new BoxLayout(productContainer, BoxLayout.Y_AXIS));
//        productContainer.setBackground(new Color(30, 30, 30)); // Dark background
//
//        ResultSet data = HomePageService.showProducts();
//        try {
//            while (data != null && data.next()) {
//                int cartId = data.getInt("CartId");
//                int productId = data.getInt("ProductId");
//                int productQuantity = data.getInt("Quanitiy");
//                double productPrice = data.getDouble("PriceAtAdd");
//
//                // Create a product panel
//                JPanel productPanel = createProductPanel(cartId, productDescription, String.valueOf(productPrice));
//
//                // Add a MouseListener to navigate to the description page
//                productPanel.addMouseListener(new java.awt.event.MouseAdapter() {
//                    public void mouseClicked(java.awt.event.MouseEvent evt) {
//                        JPanel descriptionPanel = createProductDescriptionPanel(productId, mainPanel, cardLayout);
//
//
//                        // Replace the home panel content with the description panel
//                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(productContainer);
//                        frame.getContentPane().removeAll();
//                        frame.getContentPane().add(descriptionPanel);
//                        frame.revalidate();
//                        frame.repaint();
//                    }
//                });
//
//                productContainer.add(productPanel);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e);
//        } finally {
//            try {
//                if (data != null) {
//                    data.getStatement().close();
//                    data.close();
//                }
//            } catch (SQLException e) {
//                System.out.println("Error: " + e);
//            }
//        }
//
//        return productContainer;
//
//    }
//

    public JPanel fetchAndDisplay() {
        // Parent panel to hold all product panels
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

                // Create a product panel
                JPanel productPanel = createProductPanel(productName, productDescription, String.valueOf(productPrice));

                // Add a MouseListener to navigate to the description page
                productPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        JPanel descriptionPanel = createProductDescriptionPanel(productId, mainPanel, cardLayout);
                        ;

                        // Replace the home panel content with the description panel
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(productContainer);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(descriptionPanel);
                        frame.revalidate();
                        frame.repaint();
                    }
                });

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

    public static void main(String[] args) {
        new MainFrame();

    }
}