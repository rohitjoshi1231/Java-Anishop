package UI_Layer;

import Service_layer.HomePageService;

import java.sql.ResultSet;

public class HomePage {
    public static void main(String[] args) {
        System.out.println("Welcome user");
        ResultSet data = HomePageService.showProducts();
        try {
            while (data.next()) {
                assert false;
                if (!data.next()) break;
                int productId = data.getInt("ProductId");
                int productName = data.getInt("ProductName");
                int productDescription = data.getInt("ProductDescription");
                int productStock = data.getInt("ProductStock");
                int productPrice = data.getInt("ProductPrice");

                System.out.println("ProductID: " + productId + ", ProductName: " + productName + ", ProductDescription: " + productDescription + ", ProductStock: " + productStock + ", ProductPrice: " + productPrice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
