package Service_layer;

import DAO.HomePageDAO;

import java.sql.ResultSet;

public class HomePageService {
    private static final HomePageDAO homePageDAO = new HomePageDAO();


    public static ResultSet showProducts() {

        return homePageDAO.showProducts();
    }
}