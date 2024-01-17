package de.ait.app1.validation.impl;

import de.ait.app1.validation.PasswordValidator;

public class PasswordNotEmptyValidatorImpl implements PasswordValidator {
    @Override
    public void validate(String password) {
        if (password == null || password == "" || password.equals(" ")) {
            throw new IllegalArgumentException("Password can't be empty");
        }
    }
}
