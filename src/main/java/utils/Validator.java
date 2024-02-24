package utils;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isAddressValid(String address) {
        return patternMatches(address, "^(?![0-9])[\\p{L}0-9 .,'-]+$");
    }

    public static boolean isNameValid(String name) {
        return patternMatches(name, "^(?!-)[\\p{L} .'-]+$");
    }

    public static boolean isEmailValid(String email) {
        return patternMatches(email, "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public static boolean patternMatches(String inputString, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(inputString)
                .matches();
    }
}
