package DAO;

import Utilities.Constants.ErrorMessages;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    public static int addProduct(String productName, String productDescription, int productStock, double productPrice) {

        try (Connection conn = DBConnection.connect()) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productDescription);
            preparedStatement.setInt(3, productStock);
            preparedStatement.setDouble(4, productPrice);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_WHILE_REGISTER);
        }
        return 0;
    }

}
