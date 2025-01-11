package Service_layer;

import DAO.ProductDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.ValidationUtil;

public class ProductService {
    ValidationUtil validation = new ValidationUtil();
    UserService userService = new UserService();

    public void addProduct(String productName, String productDescription, int productStock, double productPrice) {
        boolean isProductValid = validation.validateName(productName) && validation.validateName(productDescription) && validation.validateStockAndPrice(productStock, productPrice);


//        userService.loginUser("admin@anishop.com", "admin");

        if (isProductValid) {
            ProductDAO.addProduct(productName, productDescription, productStock, productPrice);
        } else {
            System.out.println(ErrorMessage.INVALID_PRODUCT);
        }
    }

}
