package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartDAO {

    public static int addCart(int ProductId, int Quantity, double PriceAtAdd) {

        try (Connection conn = DBConnection.connect()) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_CART);
            preparedStatement.setInt(1, ProductId);
            preparedStatement.setInt(2, Quantity);
            preparedStatement.setDouble(3, PriceAtAdd);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_ADDING_PRODUCT_IN_CART);
        }
        return 0;
    }

}
