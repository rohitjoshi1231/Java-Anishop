package UI_Layer;


import Service_layer.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static UI_Layer.MainFrame.field;

public class UserRegistrationUi {
    private static final UserService userService = new UserService();

    public static JPanel createRegisterPanel(CardLayout cardLayout, JPanel mainPanel) {
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
            if (name.isEmpty() || password.isEmpty() || age.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill the required details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
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



    public static void radioButtonTemplate(JRadioButton radioButton, JPanel panel, int x, int y, int width, int height) {
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(new Color(30, 30, 30));
        radioButton.setForeground(Color.WHITE);
        panel.add(radioButton);

    }
}