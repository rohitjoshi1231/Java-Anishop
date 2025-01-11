package UI_Layer;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends Ui {

    public static void main(String[] args) {
        JFrame f = new JFrame("User Registration Form");
        f.setVisible(true);
        System.out.println("Welcome to the User Menu");

        JFrame frame = new JFrame("Swing Example");
        mainFrame(frame);

        // Create and set labels
        JLabel label = new JLabel("Welcome to Ani-Shop :)");
        welcomeText(label, frame, JLabel.TOP, JLabel.CENTER);

        JLabel label2 = new JLabel("Enter your UserName :");
        welcomeText(label2, frame, JLabel.TOP, JLabel.CENTER);
    }
}

class Ui {
    public static void mainFrame(JFrame frame) {
        frame.setSize(480, 700); // Width and height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Vertical stacking of components

        // No vertical glue, we want the labels at the top
        frame.setVisible(true);
    }

    public static void welcomeText(JLabel label, JFrame frame, int Valignment, int Halignment) {
        label.setHorizontalAlignment(Halignment); // Horizontally center
        label.setVerticalAlignment(Valignment); // Align to top vertically
        frame.add(label);
    }
}
