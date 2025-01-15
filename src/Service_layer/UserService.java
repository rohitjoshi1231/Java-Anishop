package Service_layer;

import DAO.UserDAO;
import Utilities.ValidationUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final ValidationUtil validation = new ValidationUtil();
    private final UserDAO userDAO = new UserDAO();

    public static String hashString(String input) {
        try {
            // Create a MessageDigest instance for MD5 hashing
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the input string bytes
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert the byte array to a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // Convert each byte to a 2-digit hexadecimal string
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();  // Return the hash as a hex string
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void registerUser(
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber) {

        if (validation.validateRegister(emailId, password, name, gender, age, phoneNumber)) {
            userDAO.registerUser(emailId, hashString(password), name, gender, age, phoneNumber);
        } else {
            System.out.println("Please Enter correct details !");
        }
    }

    public boolean loginUser(String emailId, String password) {
        boolean found = false;
        ResultSet res1 = null;
        try {
            found = validation.validateEmailId(emailId);
            // Fetch user details based on the provided emailId
            res1 = userDAO.loginUser(emailId); // Assume this method fetches user based on the email
            if (res1 == null || !res1.next()) {
                throw new Exception("No user found with the provided email.");
            }

            String fetchedPassword = res1.getString("Password");

            // Validate the fetched password
            if (fetchedPassword.equals(password)) {
                found = true;
            } else {
                found = false;
                throw new Exception("Incorrect password.");
            }

        } catch (SQLException e) {
            System.out.println("Error while processing user details: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());  // Log the error message
        } finally {
            if (res1 != null) {
                try {
                    res1.close();
                } catch (SQLException e) {
                    System.out.println("Error while closing the ResultSet: " + e.getMessage());
                }
            }
        }

        return found;
    }


    public List<UserData> fetchUserData(String emailId) {
        List<UserData> userList = new ArrayList<>();
        ResultSet resultSet = userDAO.fetchUser(emailId);

        try {
            while (resultSet != null && resultSet.next()) {
                // Create and populate UserData object
                UserData user = new UserData(
                        resultSet.getString("id"),
                        resultSet.getString("emailid"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("phoneNumber")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while processing user data: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();  // Close ResultSet after processing
                }
            } catch (SQLException e) {
                System.out.println("Error while closing ResultSet: " + e.getMessage());
            }
        }

        return userList;
    }

    // UserData class to hold the user details
    public static class UserData {
        private String id;
        private String emailId;
        private String name;
        private String gender;
        private int age;
        private String phoneNumber;

        public UserData(String id, String emailId, String name, String gender, int age, String phoneNumber) {
            this.id = id;
            this.emailId = emailId;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
        }

        // Getter methods
        public String id() {
            return id;
        }

        public String emailId() {
            return emailId;
        }

        public String name() {
            return name;
        }

        public String gender() {
            return gender;
        }

        public int age() {
            return age;
        }

        public String phoneNumber() {
            return phoneNumber;
        }
    }


//     public void loginUser(String emailId, String password) throws Exception {
//         ResultSet res1 = null;
//         try {
//             // Fetch user details
//             res1 = userDAO.loginUser();
//             if (res1 == null) {
//                 throw new Exception("No data found or an error occurred while fetching user details.");
//             }

//             String fetchedEmailId = "";
//             String fetchedPassword = "";

//             while (res1.next()) {
//                 fetchedEmailId = res1.getString("EmailId");
//                 fetchedPassword = res1.getString("Password");
//             }

//             if (fetchedEmailId.equals(emailId) && fetchedPassword.equals(password)) {
//                 System.out.println("Match found for: " + fetchedEmailId);
//             } else {
//                 throw new Exception("Invalid email or password.");
//             }

//         } catch (SQLException e) {
//             throw new Exception("Error while processing user details: " + e.getMessage());
//         } finally {
//             if (res1 != null) {
//                 try {
//                     res1.close();
//                 } catch (SQLException e) {
//                     System.out.println("Error while closing the ResultSet: " + e.getMessage());
//                 }
//             }
//         }
//     }
}
