package de.ait.app1.validation.impl;

import de.ait.app1.validation.EmailValidator;

public class EmailNotEmptyValidatorImpl implements EmailValidator {
    @Override
    public void validate(String email) {
        if (email == null || email == "" || email.equals(" ")) {
            throw new IllegalArgumentException("Email can't be empty");
        }

    }
}
