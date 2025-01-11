package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePageDAO {
    private final static Connection conn = DBConnection.connect();

    public ResultSet showProducts() {
        ResultSet res1 = null;
        try (conn) {
            assert conn != null;
            String userDataQuery = SqlQueries.SELECT_ALL_PRODUCTS;
            Statement productDAta = conn.createStatement();
            res1 = productDAta.executeQuery(userDataQuery);


            return res1;


        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_REGISTER);
        }
        return res1;
    }
}
