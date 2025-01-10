package Utilities.Constants;

public class SqlQueries {
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
<<<<<<< HEAD
    public static final String INSERT_STRING = "INSERT INTO userRegister (EmailId, Password, Name, Gender, Age, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)" ;
    public static final String INSERT_STRING1 = "INSERT INTO userLogin (UserId, EmailId, Password, PhoneNumber) VALUES (?, ?, ?, ?, ?)";   
=======
    public static final String INSERT_USER_REGISTER = "INSERT INTO userRegister (EmailId, Password, Name, Gender, Age, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PRODUCT = "INSERT INTO products (ProductName, ProductDescription, ProductStock, ProductPrice) VALUES (?, ?, ?, ?)";
>>>>>>> c1f1b9f871ffde838b01e8332eee930b4078f1c1
}
