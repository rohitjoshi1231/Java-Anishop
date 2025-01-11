package DAO;

import Utilities.Constants.ErrorMessage;
import Utilities.Constants.SqlQueries;
import Utilities.DBConnection;

import java.sql.*;

public class UserDAO {

    private final Connection conn = DBConnection.connect();

    public void registerUser(
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber) {

        try (conn) {

            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQueries.INSERT_USER_REGISTER);

            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, String.valueOf(gender));
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, phoneNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_REGISTER);
        }
    }

    public ResultSet loginUser() {
        ResultSet res1 = null;
        try {
            String userDataQuery = SqlQueries.SELECT_ALL_USERS;
            assert conn != null;
            Statement userData = conn.createStatement();
            // Execute the query and get the result set
            res1 = userData.executeQuery(userDataQuery);
        } catch (SQLException e) {
            System.out.println(ErrorMessage.ERROR_WHILE_REGISTER);
        }
        return res1; // Return the result set (can be null if an error occurred)
    }
}
