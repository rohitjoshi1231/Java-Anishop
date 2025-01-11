package UI_Layer;

import Service_layer.UserService;

public class UserMenu {

    public static void main(String[] args) {
        System.out.println("Welcome to the User Menu");
        UserService service = new UserService();
        // service.registerUser("rohitjoshi@124", "R@ohit1234444", "rohit", 'M', 21, "0394829288");

        service.loginUser("rohitjoshi@124", "rohit1234");
    }
}
