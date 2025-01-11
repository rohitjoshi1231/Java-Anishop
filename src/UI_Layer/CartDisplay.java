package UI_Layer;
import Service_layer.CartService;


public class CartDisplay {
    public static void main(String[] args) {
        System.out.println("Welcome to the User Cart");
        CartService service = new CartService();
        // service.addCart(1);
        service.displayCartItems();

    }

}

