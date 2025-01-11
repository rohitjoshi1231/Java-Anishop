package UI_Layer;

import Service_layer.HomePageService;
import Utilities.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePage {
    public static void main(String[] args) {
        System.out.println("Welcome user");
        ResultSet data = HomePageService.showProducts();
        DBConnection.connect(); // Consider revising this if it's unrelated to this workflow

        try {
            while (data != null && data.next()) {
                int productId = data.getInt("ProductId");
                String productName = data.getString("ProductName");
                String productDescription = data.getString("ProductDescription");
                int productStock = data.getInt("ProductStock");
                double productPrice = data.getDouble("ProductPrice");

                System.out.println("ProductID: " + productId + ", ProductName: " + productName +
                        ", ProductDescription: " + productDescription + ", ProductStock: " + productStock +
                        ", ProductPrice: " + productPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (data != null) {
                    data.getStatement().close();
                    data.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
