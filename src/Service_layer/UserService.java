package Service_layer;
import DAO.UserDAO;

public class UserService {
    private final UserDAO UserDAO = new UserDAO();

    public void registerUser(String username, String password) {
        UserDAO.addUser(username, password);
    }

}

