package UI_Layer;

import Service_layer.ProductService;

public class ProductDisplay {
    public static void main(String[] args) {
        ProductService product = new ProductService();
        product.addProduct("Trojan T-shirt", "Trojan T-shirt ultimate mate black", 99, 499.00);
    }

}
