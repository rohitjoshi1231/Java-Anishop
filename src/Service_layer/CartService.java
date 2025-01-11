package Service_layer;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.UserDAO;
import Utilities.Constants.ErrorMessage;

public class CartService {
    public void addCart(int number) {
        UserDAO userDAO = new UserDAO();
        ResultSet res1 = null; // result set ki value
          try {
            // Fetch user details
            res1 = userDAO.loginUser();
            if (res1 == null) {
                System.out.println("No data found or an error occurred while fetching user details.");
                return;
            }

            String fetchedEmailId = "" ;
            String fetchedPassword = "";
                     
            while (res1.next()) {
            fetchedEmailId = res1.getString("EmailId");
            fetchedPassword = res1.getString("Password");
            }

            if (fetchedEmailId.equals(emailId) && fetchedPassword.equals(password)) {
                System.out.println("Match found for: " + fetchedEmailId);
            } else {
                System.out.println(ErrorMessage.ERROR_WHILE_LOGIN);
            }

        } catch (SQLException e) {
            System.out.println("Error while processing user details: " + e.getMessage());
        


        

    }
    
}
