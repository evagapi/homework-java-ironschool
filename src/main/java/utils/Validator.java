package utils;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isAddressValid(String address) {
        return patternMatches(address, "^(?![0-9])[\\p{L}0-9 .,'-]+$");
    }

    public static boolean isNameValid(String name) {
        return validate(name, "^(?!-)[\\p{L} .'-]+$", String.format("Invalid name: %s", name));
    }

    public static boolean isNumberValid(String number, int maxNumber) {
        return validate(number, "^(?:[1-9]|" + maxNumber + ")$", String.format("The valid range is from 1 to %s", number));
    }

    public static boolean isEmailValid(String email) {
        return patternMatches(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    private static boolean validate(String name, String pattern, String errorMessage) {
        boolean isValidName = patternMatches(name, pattern);
        if (!isValidName) {
            System.err.println(errorMessage);
        }
        return isValidName;
    }

    private static boolean patternMatches(String inputString, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(inputString)
                .matches();
    }
}
