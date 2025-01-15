package UI_Layer;

import Service_layer.CartService;

import javax.swing.*;
import java.awt.*;

import static UI_Layer.HomePageUi.createHomePanel;
import static UI_Layer.LoginUi.createLoginPanel;
import static UI_Layer.ProfileUi.showProfilePage;
import static UI_Layer.UserRegistrationUi.createRegisterPanel;

class MainFrame extends JFrame {


    public MainFrame() {
        setTitle("Ani-Shop E-Commerce-App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 690);

        JPanel mainPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();


        // Create and add the Login panel
        JPanel loginPanel = createLoginPanel(cardLayout, mainPanel);
        mainPanel.add(loginPanel, "Login");

        // Create and add the Register panel
        JPanel registerPanel = createRegisterPanel(cardLayout, mainPanel);
        mainPanel.add(registerPanel, "Register");

        // Create and add the Home Page panel
        JPanel homePanel = createHomePanel(cardLayout, mainPanel);
        mainPanel.add(homePanel, "Home");

        // Show the frame
        setContentPane(mainPanel);
        setVisible(true);
    }


    public static JPanel createProductPanel(int productId, String productName, String productDescription, double productPrice) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1)); // Add extra rows for description, buttons, and quantity
        panel.setBackground(new Color(40, 40, 40)); // Slightly lighter dark

        // Add a border with elevation effect
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 2), // Red border for highlight
                BorderFactory.createEmptyBorder(20, 10, 20, 10) // Padding inside the border
        ));

        panel.setOpaque(true);

        // Add product name
        JLabel nameLabel = new JLabel("<html><strong>&nbsp;&nbsp;" + productName + "</strong></html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        // Add product description
        JLabel descLabel = new JLabel("<html>&nbsp;&nbsp;" + productDescription + "</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(descLabel);

        // Add product price
        JLabel priceLabel = new JLabel("<html>&nbsp;&nbsp;Price: ₹" + productPrice + "</html>");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        priceLabel.setForeground(Color.GREEN);
        panel.add(priceLabel);

        // Quantity Picker Panel
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setBackground(new Color(40, 40, 40));

        // Quantity label
        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityPanel.add(quantityLabel);

        // Decrease button
        JButton minusButton = new JButton("-");
        minusButton.setFont(new Font("Arial", Font.BOLD, 10));
        minusButton.setPreferredSize(new Dimension(40, 25));
        quantityPanel.add(minusButton);

        // Quantity display label
        JLabel quantityDisplay = new JLabel("1");
        quantityDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityDisplay.setForeground(Color.WHITE);
        quantityPanel.add(quantityDisplay);

        // Increase button
        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("Arial", Font.BOLD, 10));
        plusButton.setPreferredSize(new Dimension(40, 25));
        quantityPanel.add(plusButton);

        // Add action listeners for quantity buttons
        final int[] quantity = {1}; // Use an array to allow modification inside lambda
        minusButton.addActionListener(e -> {
            if (quantity[0] > 1) { // Prevent going below 1
                quantity[0]--;
                quantityDisplay.setText(String.valueOf(quantity[0]));
            }
        });

        plusButton.addActionListener(e -> {
            quantity[0]++;
            quantityDisplay.setText(String.valueOf(quantity[0]));
        });

        panel.add(quantityPanel);

        // Add "Add to Bag" button
        JButton addToBagButton = new JButton("Add to Bag");
        addToBagButton.setBackground(new Color(255, 69, 0)); // Orange background
        addToBagButton.setForeground(Color.WHITE); // White text
        addToBagButton.setFocusPainted(false); // Remove focus highlight
        addToBagButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        addToBagButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding for the button

        // Action listener for "Add to Bag" button
        addToBagButton.addActionListener(e -> {
            CartService.addCart(productId, quantity[0]);
            JOptionPane.showMessageDialog(panel, productName + " added to bag" + " of Quantity: " + quantity[0]);

        });

        panel.add(addToBagButton);

        return panel;
    }


    public static JPanel createProductPanel(String... details) {
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

        return panel;
    }


    public static JPanel createBottomNav(CardLayout cardLayout, JPanel mainPanel) {
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

        // Pass the initialized mainPanel and cardLayout
        homeButton.addActionListener(_ -> createHomePanel(cardLayout, mainPanel));
        cartButton.addActionListener(_ -> CartUi.showCartPage(cardLayout, mainPanel));
        profileButton.addActionListener(_ -> showProfilePage(cardLayout, mainPanel));

        return bottomNav;
    }


    public static void field(JLabel jLabel, JPanel panel, JTextField textField, int x, int y, int width1, int height1, int x1,
                             int y1, int width2, int height2) {
        jLabel.setBounds(x, y, width1, height1);
        jLabel.setForeground(Color.WHITE);
        panel.add(jLabel);
        textField.setBounds(x1, y1, width2, height2);
        panel.add(textField);
    }


    public static void main(String[] args) {
        new MainFrame();

    }
}