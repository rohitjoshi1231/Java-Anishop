package UI_Layer;

import Service_layer.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static UI_Layer.MainFrame.createBottomNav;

public class ProfileUi {

    static UserService service = new UserService();

    public static void showProfilePage(CardLayout cardLayout, JPanel mainPanel) {
        // Main panel for profile page
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(new Color(30, 30, 30)); // Dark background

        // Container for profile content (user image, details, and logout button)
        JPanel profileContainer = new JPanel();
        profileContainer.setLayout(new BoxLayout(profileContainer, BoxLayout.PAGE_AXIS));
        profileContainer.setBackground(new Color(30, 30, 30)); // Dark background
        profileContainer.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the content

        // Add round user image
        JLabel userImageLabel = new JLabel();
        ImageIcon userImageIcon = new ImageIcon("C:\\Users\\HP\\Desktop\\Java-Anishop\\src\\Utilities\\user.png"); // Set the path to your user image
        Image img = userImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image to 100x100
        userImageIcon = new ImageIcon(img);

        // Set the image and align it to center
        userImageLabel.setIcon(userImageIcon);
        userImageLabel.setPreferredSize(new Dimension(150, 150)); // Size of the image
        userImageLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Horizontal centering
        userImageLabel.setVerticalAlignment(SwingConstants.CENTER);    // Vertical centering
        userImageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3)); // Border for the image

        // Add image to profile container
        profileContainer.add(userImageLabel);
        profileContainer.add(Box.createRigidArea(new Dimension(0, 20))); // Space between image and content

        // User data labels (name, email, gender, phone, etc.)
        String email = readEmail(); // Example email for testing
        List<UserService.UserData> users = service.fetchUserData(email); // Fetching data from service layer
        for (UserService.UserData user : users) {
            JLabel nameLabel = new JLabel("<html><strong>&nbsp;&nbsp;" + "Name: " + user.name() + "</strong></html>");
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(Color.WHITE);

            JLabel emailLabel = new JLabel("<html><strong>&nbsp;&nbsp;" + "Email: " + user.emailId() + "</strong></html>");
            emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            emailLabel.setForeground(Color.LIGHT_GRAY);

            JLabel genderLabel = new JLabel("<html><strong>&nbsp;&nbsp;" + "Gender: " + user.gender() + "</strong></html>");
            genderLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            genderLabel.setForeground(Color.WHITE);

            JLabel phoneLabel = new JLabel("<html><strong>&nbsp;&nbsp;" + "Phone: " + user.phoneNumber() + "</strong></html>");
            phoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            phoneLabel.setForeground(Color.WHITE);

            // Add labels to profile container
            profileContainer.add(nameLabel);
            profileContainer.add(emailLabel);
            profileContainer.add(genderLabel);
            profileContainer.add(phoneLabel);
            profileContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Space between labels
        }

        // Scroll pane to make content scrollable
        JScrollPane scrollPane = new JScrollPane(profileContainer);
        profilePanel.add(scrollPane, BorderLayout.CENTER);

        // Create and add logout button at the bottom
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(255, 0, 0)); // Red background for logout
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(150, 40)); // Set preferred size for the button
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

        // Add functionality to logout button (example, you can modify it based on your requirement)
        logoutButton.addActionListener(e -> {
            // Handle logout logic here
            JOptionPane.showMessageDialog(profilePanel, "Logging out...");
            cardLayout.show(mainPanel, "Login");
        });

        // Add the logout button to the bottom (south) of the profile panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 30)); // Set background color to match
        bottomPanel.add(logoutButton, BorderLayout.CENTER); // Center button in bottom panel

        // Add Bottom Navigation (if any)
        JPanel bottomNav = createBottomNav(cardLayout, mainPanel);
        profilePanel.add(bottomNav, BorderLayout.NORTH);

        // Add the bottom panel with the logout button
        profilePanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add profile panel to main panel and show
        mainPanel.add(profilePanel, "Profile");
        cardLayout.show(mainPanel, "Profile");
    }


    public static String readEmail() {
        String email = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("source.txt"))) {
            email = reader.readLine();  // Read the email from source.txt
            System.out.println("Email read from source.txt: " + email);
        } catch (IOException e) {
            System.out.println("e" + e);
        }
        return email;
    }
}
