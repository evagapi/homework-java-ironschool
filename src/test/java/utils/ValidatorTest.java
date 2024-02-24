package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @ParameterizedTest
    @DisplayName("Should validate correct emails")
    @ValueSource(strings = {"Calle 13", "Ronda 1 de març 100", "Gran via de les c.c 1000", "Plaça Lesseps 10"})
    void isAddressValid_when_correct_address(String address) {
        assertTrue(Validator.isAddressValid(address));
    }

    @ParameterizedTest
    @DisplayName("Should validate correct emails")
    @ValueSource(strings = {"13 Calle", "_Ronda 1", "!Gran via de les c.c 1000", "?Plaça Lesseps 10"})
    void isAddressValid_when_wrong_address(String address) {
        assertFalse(Validator.isAddressValid(address));
    }

    @ParameterizedTest
    @DisplayName("Should validate correct emails")
    @ValueSource(strings = {"Ivan David", "Agustín", "Dembéle", "Jossa", "D"})
    void isNameValid_when_correct_name(String name) {
        assertTrue(Validator.isNameValid(name));
    }

    @ParameterizedTest
    @DisplayName("Should validate correct emails")
    @ValueSource(strings = {"1Ivan David", "_Agustín", "-Dembéle", "Jossa!", "D´"})
    void isNameValid_when_wrong_name(String name) {
        assertFalse(Validator.isNameValid(name));
    }

    @ParameterizedTest
    @DisplayName("Should validate correct emails")
    @ValueSource(strings = {"any@hotmail.com", "any123@hotmail.com", "any@hotmail1.com"
            , "any@1hotmail.com", "any@1hotmail1.com", "1any@hotmail.com"})
    void isEmailValid_when_correct_email(String email) {
        assertTrue(Validator.isEmailValid(email));
    }

    @ParameterizedTest
    @DisplayName("Should not validate wrong emails")
    @ValueSource(strings = {"any@hotmail.", "any@hotmail", "anyhotmail.com", "any@.com"})
    void isEmailValid_when_wrong_email(String email) {
        assertFalse(Validator.isEmailValid(email));
    }


}