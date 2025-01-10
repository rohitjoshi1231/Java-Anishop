package Service_layer;

import DAO.ProductDAO;

public class ProductService {

    public int addProduct(String productName, String productDescription, int productStock, double productPrice) {

        // Business Logic goes here

        return ProductDAO.addProduct(productName, productDescription, productStock, productPrice);

    }
}
