package Utilities.Constants;

public class SqlQueries {
    public static final String SELECT_ALL_USERS = "SELECT * FROM userRegister";
    public static final String INSERT_USER_REGISTER = "INSERT INTO userRegister (EmailId, Password, Name, Gender, Age, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_STRING1 = "INSERT INTO userLogin (UserId, EmailId, Password, PhoneNumber) VALUES (?, ?, ?, ?, ?)";

}
