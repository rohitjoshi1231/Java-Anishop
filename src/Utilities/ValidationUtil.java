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


    public boolean validateRegister(String emailId, String password, String name, char gender, int age, String phoneNumber) {
        return validateName(name) && validateEmailId(emailId) && validateAge(age) && validatePassword(password) && validateGender(gender) && validatePhoneNumber(phoneNumber);
    }


    public boolean validateStockAndPrice(int productStock, double productPrice) {
        // Regex for productStock: Positive integers
        String stockRegex = "^[1-9]\\d*$";
        // Regex for productPrice: Positive numbers with up to 2 decimal places
        String priceRegex = "^\\d+(\\.\\d{1,2})?$";

        // Convert productStock and productPrice to Strings for regex matching
        String stockString = String.valueOf(productStock);
        String priceString = String.valueOf(productPrice);

        // Validate using regex
        return stockString.matches(stockRegex) && priceString.matches(priceRegex);
    }


}
