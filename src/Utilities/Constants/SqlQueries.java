package Utilities.Constants;

public class SqlQueries {
    public static final String SELECT_ALL_USERS = "SELECT * FROM userregister";
    public static final String INSERT_USER_REGISTER = "INSERT INTO userRegister (EmailId, Password, Name, Gender, Age, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_USER_LOGIN = "INSERT INTO userLogin (UserId, EmailId, Password, PhoneNumber) VALUES (?, ?, ?, ?)";
    public static final String INSERT_PRODUCT = "INSERT INTO products (ProductName, ProductDescription, ProductStock, ProductPrice) VALUES (?, ?, ?, ?)";
    public static final String INSERT_CART = "INSERT INTO cart (ProductId, Quantity, PriceAtAdd ) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    public static final String SELECT_PRODUCT_WITH_ID = "SELECT * FROM products where productid=?";
    public static final String SHOW_CART_PRODUCTS = "SELECT * FROM cart";
}

