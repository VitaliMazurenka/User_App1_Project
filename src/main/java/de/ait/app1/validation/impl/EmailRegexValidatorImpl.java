package de.ait.app1.validation.impl;

import de.ait.app1.validation.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegexValidatorImpl implements EmailValidator {

    private static final String REGEX = "^(?=.{1,256}$)[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9]" +
            "(?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z]{2,}$";
    @Override
    public void validate(String email) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            throw new IllegalArgumentException("Wrong email format! ");
        }

    }
}
