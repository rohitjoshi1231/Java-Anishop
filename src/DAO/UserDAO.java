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

    public ResultSet loginUser(String emailId) {
        ResultSet res1 = null;
        try {
            // Updated query to fetch a user based on the emailId
            String userDataQuery = "SELECT * FROM userregister WHERE EmailId = ?";

            assert conn != null;
            PreparedStatement userData = conn.prepareStatement(userDataQuery);

            // Set the emailId parameter in the query
            userData.setString(1, emailId);

            // Execute the query and get the result set
            res1 = userData.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error while fetching user data: " + e.getMessage());
        }
        return res1; // Return the result set (can be null if an error occurred)
    }


    public ResultSet fetchUser(String emailId) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection conn = DBConnection.connect(); // Assuming you have a method to get a DB connection

        try {
            String query = SqlQueries.SHOW_USER_DETAILS;  // Query from your SQL file
            assert conn != null;
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, emailId);  // Safely setting the emailId parameter
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error while fetching user: " + e.getMessage());
        }

        // Return the ResultSet; don't close it here!
        return resultSet;
    }

}
