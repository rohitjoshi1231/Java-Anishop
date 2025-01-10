package Service_layer;

import DAO.UserDAO;
import Utilities.Constants.ErrorMessage;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public void registerUser(     
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber){

        // Validate email ID
        if (emailId == null || !emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMAIL);
        }

        // Validate password (at least 8 characters, 1 uppercase, 1 digit)
        else if (password == null || !password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_PASSWORD);
        }

        // Validate name (non-empty, alphabets only)
        else if (name == null || !name.matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME);
        }

        // Validate gender (allowed values: 'M' or 'F')
        else if (gender != 'M' && gender != 'F' && gender != 'O') {
            throw new IllegalArgumentException(ErrorMessage.INVALID_GENDER);
        }

        // Validate age (must be a positive number and reasonable)
        else if (age <= 0 || age > 120) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AGE);
        }

        // Validate phone number (10 digits)
        else if (phoneNumber == null || !phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PHONE_NUMBER);
        }

        else {
            System.out.println("Please Enter correct details !");
        }
        
        userDAO.registerUser(emailId, password, name, gender, age, phoneNumber);
        
        
    }
}
