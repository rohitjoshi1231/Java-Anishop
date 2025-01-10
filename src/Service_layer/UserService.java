package Service_layer;

import DAO.UserDAO;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public void registerUser(     
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber) {
        userDAO.registerUser(emailId, password, name, gender, age, phoneNumber);
        
        
    }
}
