package DAO;

import java.sql.*;

import Utilities.Constants.ErrorMessages;
import Utilities.DBConnection;
import Utilities.Constants.SqlQueries;

public class UserDAO {

    public void registerUser(
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber) {

        try (Connection conn = DBConnection.connect()) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_STRING);

            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, String.valueOf(gender));
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, phoneNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println(ErrorMessages.ERROR_WHILE_REGISTER);
        }
    }
}
