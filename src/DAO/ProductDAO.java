package DAO;

import Utilities.Constants.ErrorMessages;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    public void addProduct(String productName, String productDescription, int productStock, double productPrice) {

        try (Connection conn = DBConnection.connect()) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_PRODUCT);


        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_WHILE_REGISTER);
        }
    }

}
