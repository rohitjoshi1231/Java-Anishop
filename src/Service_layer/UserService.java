package Service_layer;

import DAO.UserDAO;
import Utilities.Constants.ErrorMessage;
import Utilities.ValidationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

<<<<<<< HEAD
import DAO.UserDAO;
import Utilities.ValidationUtil;
import Utilities.Constants.ErrorMessage;

=======
>>>>>>> a2519c7dec2874deac67c76f0c4e8fab28096535
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

    // public UserService(String emailId, String password, String name, char gender, int age, String phoneNumber) {
    //     this.emailId = emailId;
    //     this.password = password;
    //     this.name = name;
    //     this.gender = gender;
    //     this.age = age;
    //     this.phoneNumber = phoneNumber;
    // }

    public void loginUser(String emailId, String password) {
        UserDAO userDAO = new UserDAO();
        ResultSet res1 = null; // result set ki value
        try {
            // Fetch user details
            res1 = userDAO.loginUser();
            if (res1 == null) {
                System.out.println("No data found or an error occurred while fetching user details.");
                return;
            }
            // Iterate through the result set
            // while (res1.next()) {
            //     String emailId = res1.getString("EmailId");
            //     String password = res1.getString("Password");
            //     System.out.println("EmailId: " + emailId + ", Password: " + password);
            //     if (emailId.equals(emailId)) {
            //         System.out.println("Match found for: " + emailId);
            //     }

            // }

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

}
