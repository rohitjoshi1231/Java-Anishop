package Service_layer;

import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.UserDAO;
import Utilities.ValidationUtil;

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

    public void loginUser() {
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
            while (res1.next()) {
                String emailId = res1.getString("EmailId");
                String password = res1.getString("Password");
                System.out.println("EmailId: " + emailId + ", Password: " + password);
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
