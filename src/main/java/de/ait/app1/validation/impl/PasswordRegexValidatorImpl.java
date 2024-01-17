package de.ait.app1.validation.impl;

import de.ait.app1.validation.PasswordValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRegexValidatorImpl implements PasswordValidator {

    private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    @Override
    public void validate(String password) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Password is too easy! ");
        }

    }
}
