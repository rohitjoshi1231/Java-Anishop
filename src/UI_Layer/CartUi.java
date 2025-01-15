package UI_Layer;

import Service_layer.CartService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static UI_Layer.MainFrame.createProductPanel;


public class CartUi {
    public static void showCartPage(CardLayout cardLayout, JPanel mainPanel) {
        if (mainPanel == null || cardLayout == null) {
            throw new IllegalArgumentException("mainPanel and cardLayout must not be null");
        }

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(new Color(30, 30, 30)); // Dark background

        JScrollPane scrollPane = new JScrollPane();
        JPanel cartContentPanel = new JPanel();
        cartContentPanel.setLayout(new BoxLayout(cartContentPanel, BoxLayout.Y_AXIS));
        cartContentPanel.setBackground(new Color(30, 30, 30)); // Dark background

        CartService service = new CartService();
        List<CartService.CartProduct> cartItems = service.displayCartItems();

        for (CartService.CartProduct cartProduct : cartItems) {
            JPanel cartProductPanel = createProductPanel(
                    "Name: " + cartProduct.getProductName(),
                    "Description: " + cartProduct.getProductDescription(),
                    "Quantity: " + cartProduct.getQuantity(),
                    "Price: $" + cartProduct.getPriceAtAdd());
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

        JPanel bottomNav = MainFrame.createBottomNav(cardLayout, mainPanel);
        cartPanel.add(bottomNav, BorderLayout.NORTH);

        mainPanel.add(cartPanel, "Cart");

        // Revalidate and repaint to ensure the updated content is displayed
        cartPanel.revalidate();
        cartPanel.repaint();

        // Show the cart panel in the card layout
        cardLayout.show(mainPanel, "Cart");
    }
}
