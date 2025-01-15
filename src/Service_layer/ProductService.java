package Service_layer;

import DAO.ProductDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.ValidationUtil;

import java.sql.ResultSet;

public class ProductService {
    ValidationUtil validation = new ValidationUtil();
    ProductDAO productDAO = new ProductDAO(); // Updated to fetch products from DAO



    public void addProduct(String productName, String productDescription, int productStock, double productPrice) {
        boolean isProductValid = validation.validateName(productName) &&
                validation.validateName(productDescription) &&
                validation.validateStockAndPrice(productStock, productPrice);

        if (isProductValid) {
            productDAO.addProduct(productName, productDescription, productStock, productPrice);
        } else {
            System.out.println(ErrorMessage.INVALID_PRODUCT);
        }
    }

    // Fetch all products from the database
    public ResultSet getAllProducts() {
        return productDAO.productDetails();
    }
}
