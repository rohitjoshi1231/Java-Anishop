package Service_layer;

import DAO.HomePageDAO;

import java.sql.ResultSet;
<<<<<<< HEAD

=======
>>>>>>> a2519c7dec2874deac67c76f0c4e8fab28096535

public class HomePageService {
    private static final HomePageDAO homePageDAO = new HomePageDAO();


    public static ResultSet showProducts() {

        return homePageDAO.showProducts();
    }
}