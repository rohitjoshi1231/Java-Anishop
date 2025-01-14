package UI_Layer;

import javax.swing.*;
import java.awt.*;

public class UserMenu {
    public static void main(String[] args) {

        // Create the frame and set up main panel with CardLayout
        JFrame frame = new JFrame("User Registration Form");
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Create and add User Registration UI panel
        JPanel userRegistrationPanel = new UserRegistrationUi(cardLayout, mainPanel).createUserRegistrationPanel();
        mainPanel.add(userRegistrationPanel, "User Registration");

        // Create and add Login UI panel
        JPanel loginPanel = new LoginUi(cardLayout, mainPanel).createLoginPanel();
        mainPanel.add(loginPanel, "Login");

//        Hello

        // Set up frame
        frame.setSize(480, 700); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}