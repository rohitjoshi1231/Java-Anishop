package UI_Layer;

import Service_layer.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

import static Service_layer.UserService.hashString;
import static UI_Layer.MainFrame.field;

public class LoginUi{

    public static JPanel createLoginPanel(CardLayout cardLayout, JPanel mainPanel) {
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
        loginButton.addActionListener(_ -> {
            String emailId = emailText.getText().trim(); // Use trim() to remove leading/trailing whitespaces
            String password = new String(passwordText.getPassword()).trim(); // Trim to handle spaces

            // Check if either email or password is empty
            if (emailId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution if fields are empty
            }

            try {
                // Call the service layer to authenticate the user

                boolean is_loggedIn = userService.loginUser(emailId, hashString(password));
                if (is_loggedIn){
                    sendEmail(emailId);
                    JOptionPane.showMessageDialog(panel, "Login successful! :) ");
                    cardLayout.show(mainPanel, "Home");
                }else {
                    JOptionPane.showMessageDialog(panel, "Login Failed! :( ");
                }

            } catch (Exception ex) {
                // Show error message if login fails
                JOptionPane.showMessageDialog(panel, "Login Failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void sendEmail(String email){

        try (FileWriter writer = new FileWriter("source.txt")) {
            writer.write(email);  // Write email to source.txt
            System.out.println("Email written to source.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}