package UI_Layer;

import Service_layer.UserService;

import javax.swing.*;
import java.awt.*;

class LoginUi {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public LoginUi(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }

    public JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for absolute positioning
        panel.setBackground(Color.BLACK);

        // Create and set the welcome label
        JLabel label = new JLabel("Welcome to Login :)");
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        label.setBounds(0, 20, 480, 50); // Set position and size
        label.setForeground(Color.WHITE); // Set text color to white
        panel.add(label);

        // Add components to the panel
        placeComponents(panel);

        return panel;
    }

    public void placeComponents(JPanel panel) {
        // Add a username label
        JLabel emailLabel = new JLabel("Enter Email ID:");
        emailLabel.setBounds(50, 90, 150, 25); // Position and size
        panel.add(emailLabel);
        emailLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a text field for the email
        JTextField emailText = new JTextField(20);
        emailText.setBounds(200, 90, 200, 25); // Position and size
        panel.add(emailText);

        // Add a password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 190, 25); // Position and size
        panel.add(passwordLabel);
        passwordLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a password field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 120, 200, 25); // Position and size
        panel.add(passwordText);

        // Add a login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 160, 100, 25); // Position and size
        panel.add(loginButton);

        // Set the text color of the button to white
        loginButton.setForeground(Color.BLACK); // Set text color to black

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
            } catch (Exception ex) {
                // Show error message if login fails
                JOptionPane.showMessageDialog(panel, "Login Failed: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add a clickable label to switch to Registration page
        JLabel registerRedirectLabel = new JLabel("<html>Don't have an account? <a href=''>Register here.</a></html>");
        registerRedirectLabel.setBounds(150, 200, 250, 25); // Position and size
        registerRedirectLabel.setForeground(Color.WHITE); // Set color to white
        registerRedirectLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        panel.add(registerRedirectLabel);

        // Add a mouse listener to switch to Registration page
        registerRedirectLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(mainPanel, "User Registration"); // Switch to Registration UI
            }
        });
    }
}