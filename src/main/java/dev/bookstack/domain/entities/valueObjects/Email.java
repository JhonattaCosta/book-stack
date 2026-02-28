package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.EmailInvalidException;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?!\\.)(?!.*\\.{2})[a-zA-Z0-9._%+-]+(?<!\\.)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    public Email(String value) {
        if(value == null || value.isBlank()){
            throw new EmailInvalidException("Email cannot be null or empty!","EMAIL_NOT_NULL_OR_EMPTY");
        }
        value = value.trim().toLowerCase();

        if(!EMAIL_PATTERN.matcher(value).matches()){
            throw new EmailInvalidException("Email is invalid, please try again.","EMAIL_INVALID");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
