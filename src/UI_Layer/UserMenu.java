package UI_Layer;

import javax.swing.*;
import Service_layer.UserService;
import java.awt.*;

public class UserMenu {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {

        // Create the frame and set up main panel with CardLayout
        frame = new JFrame("User Registration Form");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add User Registration UI panel
        JPanel userRegistrationPanel = new UserRegistrationUi(cardLayout, mainPanel).createUserRegistrationPanel();
        mainPanel.add(userRegistrationPanel, "User Registration");

        // Create and add Login UI panel
        JPanel loginPanel = new LoginUi(cardLayout, mainPanel).createLoginPanel();
        mainPanel.add(loginPanel, "Login");

        // Set up frame
        frame.setSize(480, 700); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}

class UserRegistrationUi {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public UserRegistrationUi(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }

    public JPanel createUserRegistrationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for absolute positioning
        panel.setBackground(Color.BLACK);

        // Create and set the welcome label
        JLabel label = new JLabel("Welcome to Ani-Shop :)");
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
        // Add a user label
        JLabel userLabel = new JLabel("Enter Username:");
        userLabel.setBounds(50, 80, 190, 25); // Position and size
        panel.add(userLabel);
        userLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a text field for the username
        JTextField userText = new JTextField(20);
        userText.setBounds(200, 80, 200, 25); // Position and size
        panel.add(userText);

        // Add an email label
        JLabel emailLabel = new JLabel("Enter Email ID:");
        emailLabel.setBounds(50, 120, 150, 25); // Position and size
        panel.add(emailLabel);
        emailLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a text field for the email
        JTextField emailText = new JTextField(20);
        emailText.setBounds(200, 120, 200, 25); // Position and size
        panel.add(emailText);

        // Add a phone number label
        JLabel phoneNumberLabel = new JLabel("Enter Phone Number:");
        phoneNumberLabel.setBounds(50, 160, 150, 25); // Position and size
        panel.add(phoneNumberLabel);
        phoneNumberLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a text field for the phone number
        JTextField phoneNumberText = new JTextField(20);
        phoneNumberText.setBounds(200, 160, 200, 25); // Position and size
        panel.add(phoneNumberText);

        // Add a gender label
        JLabel genderLabel = new JLabel("Choose Gender:");
        genderLabel.setBounds(50, 200, 150, 25); // Position and size
        panel.add(genderLabel);
        genderLabel.setForeground(Color.WHITE); // Set text color to white

        // Add radio buttons for gender selection
        JRadioButton maleButton = new JRadioButton("M");
        JRadioButton femaleButton = new JRadioButton("F");
        JRadioButton otherButton = new JRadioButton("O");
        maleButton.setBounds(200, 200, 70, 25); // Position and size
        femaleButton.setBounds(280, 200, 80, 25); // Position and size
        otherButton.setBounds(370, 200, 70, 25); // Position and size

        // Group the radio buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        panel.add(maleButton);
        panel.add(femaleButton);
        panel.add(otherButton);

        // Add an age label
        JLabel userAge = new JLabel("Enter Your Age:");
        userAge.setBounds(50, 240, 240, 25); // Position and size
        panel.add(userAge);
        userAge.setForeground(Color.WHITE); // Set text color to white

        // Add a text field for the age
        JTextField userAgeText = new JTextField(20);
        userAgeText.setBounds(200, 240, 200, 25); // Position and size
        panel.add(userAgeText);

        // Add a password label
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(50, 280, 150, 25); // Position and size
        panel.add(passwordLabel);
        passwordLabel.setForeground(Color.WHITE); // Set text color to white

        // Add a password field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 280, 200, 25); // Position and size
        panel.add(passwordText);

        // Add a register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 320, 100, 25); // Position and size
        panel.add(registerButton);

        // Set the text color of the button to white
        registerButton.setForeground(Color.BLACK); // Set text color to black

        UserService userService = new UserService();
        // Add action listener to switch to login UI when register button is clicked
        registerButton.addActionListener(e -> {
            // Switch to Login UI
            String username = userText.getText();
            String email = emailText.getText();
            String phoneNumber = phoneNumberText.getText();
            String age = userAgeText.getText();
            String password = new String(passwordText.getPassword());
            char gender = ' '; // Default value

            if (maleButton.isSelected()) {
                gender = 'M'; // Male
            } else if (femaleButton.isSelected()) {
                gender = 'F'; // Female
            } else if (otherButton.isSelected()) {
                gender = 'O'; // Other
            }

            try {
                // Call the service layer to register the user
                userService.registerUser(email, password, username, gender, Integer.parseInt(age), phoneNumber);

                // If registration is successful, show success message
                JOptionPane.showMessageDialog(panel, "Registration successful.. :) ");

                // Switch to Login UI after successful registration
                cardLayout.show(mainPanel, "Login");
            } catch (Exception ex) {
                // In case of any error during the registration process, show error message
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add a clickable text for switching to the Login page
        JLabel loginRedirectLabel = new JLabel("<html>Already have an account? <a href=''>Login here.</a></html>");
        loginRedirectLabel.setBounds(150, 360, 250, 25); // Position and size
        loginRedirectLabel.setForeground(Color.WHITE); // Set the color for the clickable text
        loginRedirectLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        panel.add(loginRedirectLabel);

        // Add a mouse listener to the clickable label to switch to login page
        loginRedirectLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(mainPanel, "Login"); // Switch to Login UI when clicked
            }
        });
    }
}

class LoginUi {
    private CardLayout cardLayout;
    private JPanel mainPanel;

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
