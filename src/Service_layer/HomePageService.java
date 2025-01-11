package Service_layer;

import DAO.HomePageDAO;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class HomePageService {
    private static final HomePageDAO homePageDAO = new HomePageDAO();


    public static ResultSet showProducts() {

        return homePageDAO.showProducts();
    }
}