package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.IsbnInvalidException;

import java.util.regex.Pattern;

public class Isbn {

    private final String  value;

    private static final Pattern ISBN_13_PATTERN = Pattern.compile("^(978|979)85\\d{8}$");

    public Isbn(String value) {
        if(value == null || value.isBlank()){
            throw new IsbnInvalidException("Isbn cannot be null or empty!", "ISBN_NULL_OR_EMPTY");
        }

        if (!ISBN_13_PATTERN.matcher(value).matches()) {
            throw new IsbnInvalidException("ISBN must contain exactly 13 digits and start with 978 or 979", "ISBN_INVALID_FORMAT");
        }

        validateChecksum(value);
        this.value = value;
    }

    private void validateChecksum(String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        int actualLastDigit = Character.getNumericValue(isbn.charAt(12));

        if (checkDigit != actualLastDigit) {
            throw new IsbnInvalidException("Isbn Invalid!", "ISBN_INVALID_CHECK_DIGIT");
        }
    }

    public String getValue() {
        return value;
    }
}
