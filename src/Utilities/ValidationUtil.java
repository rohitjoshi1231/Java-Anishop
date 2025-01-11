package Utilities;

import java.sql.ResultSet;

import Utilities.Constants.ErrorMessage;

public class ValidationUtil {
    public boolean validateName(String name) {

        if (name == null || !name.matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME);
        } else {
            return true;
        }
    }

    public boolean validateEmailId(String emailId) {
        if (emailId == null || !emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMAIL);
        } else {
            return true;
        }
    }

    public boolean validatePassword(String password) {
        if (password == null || !password.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PASSWORD);
        } else {
            return true;
        }
    }

    public boolean validateGender(char gender) {
        if (gender != 'M' && gender != 'F' && gender != 'O') {
            throw new IllegalArgumentException(ErrorMessage.INVALID_GENDER);
        } else {
            return true;
        }
    }

    public boolean validateAge(int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AGE);
        } else {
            return true;
        }
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PHONE_NUMBER);
        } else {
            return true;
        }
    }

    public boolean validateQuantity(int Quantity) {
        return Quantity > 0;
    }


    public boolean validateRegister(
            String emailId,
            String password,
            String name,
            char gender,
            int age,
            String phoneNumber
    ) {
        if (validateName(name) && validateEmailId(emailId) && validateAge(age) && validatePassword(password)
                && validateGender(gender) && validatePhoneNumber(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateStockAndPrice(int stock, double price) {
        // Validation rules:
        // Stock must be greater than or equal to 0
        // Price must be greater than 0
        if (stock < 0) {
            System.out.println("Invalid stock: must be 0 or greater.");
            return false;
        }
        if (price <= 0) {
            System.out.println("Invalid price: must be greater than 0.");
            return false;
        }
        return true; // Valid stock and price
    }


}
