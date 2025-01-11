package UI_Layer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Test {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;

    public Test() {
        prepareGUI();
    }

//    public static void main(String[] args) {

    /// /        Test swingControlDemo = new Test();
    /// /        swingControlDemo.showScrollPaneDemo();
    /// /    }

    public void prepareGUI() {
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        JLabel statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    void showScrollPaneDemo() {
        headerLabel.setText("Control in action: ScrollPane");
        JTextArea outputTextArea = new JTextArea("", 5, 20);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        controlPanel.add(scrollPane);
        mainFrame.setVisible(true);
    }
}