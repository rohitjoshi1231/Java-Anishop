package UI_Layer;

import Service_layer.UserService;

public class UserMenu {

    public static void main(String[] args) {
        System.out.println("Welcome to the User Menu");
        UserService service = new UserService();
        // service.registerUser("rohitjoshi@124", "R@ohit1234444", "rohit", 'M', 21, "0394829288");
<<<<<<< HEAD
        service.loginUser();


=======
        service.loginUser("rohitjoshi@124", "rohit1234");
>>>>>>> 68c82e3af2475b14d78767500c8cfc849e41e11c
    }
}
