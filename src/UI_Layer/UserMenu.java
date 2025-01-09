package UI_Layer;

import Service_layer.UserService;

public class UserMenu {

    public static void main(String[] args) {
        System.out.println("Welcome to the User Menu");
        UserService service = new UserService();
        service.registerUser( "rohitjoshi@124", "rohit1234", "rohit", 'M', 21, "12392748292");
    }
}
