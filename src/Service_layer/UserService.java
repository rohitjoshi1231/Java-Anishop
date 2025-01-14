package Service_layer;

import DAO.UserDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.ValidationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private final ValidationUtil validation = new ValidationUtil();
    private final UserDAO userDAO = new UserDAO();

    public void registerUser(
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber) {

        if (validation.validateRegister(emailId, password, name, gender, age, phoneNumber)) {
            userDAO.registerUser(emailId, password, name, gender, age, phoneNumber);
        } else {
            System.out.println("Please Enter correct details !");
        }
    }

    public void loginUser(String emailId, String password) {
        ResultSet res1 = null;
        try {
            // Fetch user details
            res1 = userDAO.loginUser();
            if (res1 == null) {
                System.out.println("No data found or an error occurred while fetching user details.");
                return;
            }

            String fetchedEmailId = "";
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
        } finally {
            if (res1 != null) {
                try {
                    res1.close();
                } catch (SQLException e) {
                    System.out.println("Error while closing the ResultSet: " + e.getMessage());
                }
            }
        }
    }

        
    
//     public void loginUser(String emailId, String password) throws Exception {
//         ResultSet res1 = null;
//         try {
//             // Fetch user details
//             res1 = userDAO.loginUser();
//             if (res1 == null) {
//                 throw new Exception("No data found or an error occurred while fetching user details.");
//             }

//             String fetchedEmailId = "";
//             String fetchedPassword = "";

//             while (res1.next()) {
//                 fetchedEmailId = res1.getString("EmailId");
//                 fetchedPassword = res1.getString("Password");
//             }

//             if (fetchedEmailId.equals(emailId) && fetchedPassword.equals(password)) {
//                 System.out.println("Match found for: " + fetchedEmailId);
//             } else {
//                 throw new Exception("Invalid email or password.");
//             }

//         } catch (SQLException e) {
//             throw new Exception("Error while processing user details: " + e.getMessage());
//         } finally {
//             if (res1 != null) {
//                 try {
//                     res1.close();
//                 } catch (SQLException e) {
//                     System.out.println("Error while closing the ResultSet: " + e.getMessage());
//                 }
//             }
//         }
//     }
 }
